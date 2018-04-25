package io.vitess.service.so;

import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SalesOrderLineCommand;
import io.vitess.command.WmsConstants;
import io.vitess.common.ErrorCode;
import io.vitess.constants.Constants;
import io.vitess.constants.SysWmsStatus;
import io.vitess.dao.base.ShoOutLetInfoDao;
import io.vitess.dao.base.SkuDao;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.base.ShoOutLetInfo;
import io.vitess.model.base.Sku;
import io.vitess.model.so.SalesOrder;
import io.vitess.model.so.SalesOrderLine;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * BY Store 拆单
 * @author zhiyong.shi
 *
 */
@Service("salesOrderStoreSplitManager")
public class SalesOrderStoreSplitManagerImpl extends BaseManagerImpl implements SalesOrderStoreSplitManager {

	private static final long serialVersionUID = -3195602524317398894L;

	@Autowired
	private ShoOutLetInfoDao shoOutLetInfoDao;
	@Autowired
	private  SkuDao skuDao;

	@Override
	public List<SalesOrderCommand> splitOrderByStore(CompanyShop shop, SalesOrderCommand srcSoCmd) {
		//拆单后的多单集合
		List<SalesOrderCommand> resultList = new ArrayList<SalesOrderCommand>();
		List<SalesOrderLineCommand> salesOrderLineList = srcSoCmd.getSoLineCommandList();
		//定义一个map集合用于分组
		Map<String, List<SalesOrderLineCommand>> mapList = new HashMap<String, List<SalesOrderLineCommand>>();
		 //遍历集合以targetCode 为键，以SalesOrderLineCommand为值保存到mapList中
		  for (Iterator<SalesOrderLineCommand> it = salesOrderLineList.iterator(); it.hasNext();){
			  SalesOrderLineCommand command  = (SalesOrderLineCommand) it.next();
			  //全渠道订单如果是赠品则放入EC仓中
			  if(command.getOrderLineType() != 1){
				  command.setTargetCode(DEFAULT_WH);
			  }else{
				  if("WAREHOUSE".equals(command.getTargetType())){
					  command.setTargetCode(DEFAULT_WH);
				  }
			  }


			  //如果在这个map中包含有相同的键，这创建一个集合将其存起来
			   if (mapList.containsKey(command.getTargetCode()))
			   {
			    List<SalesOrderLineCommand> syn = mapList.get(command.getTargetCode());
			    syn.add(command);
			    //如果没有包含相同的键，在创建一个集合保存数据
			   } else {
			    List<SalesOrderLineCommand> syns = new ArrayList<SalesOrderLineCommand>();
				    syns.add(command);
				    mapList.put(command.getTargetCode(), syns);
			   }
		  }
		  int i = 0;
		  //遍历map集合
		  for (Map.Entry<String, List<SalesOrderLineCommand>> m : mapList.entrySet()){
			   //获取所有的值
			   List<SalesOrderLineCommand> salesLines = m.getValue();
			   SalesOrderLineCommand com = salesLines.get(0);
			   SalesOrderCommand salesOrderCommand = new SalesOrderCommand();
			   BeanUtils.copyProperties(srcSoCmd, salesOrderCommand, new String[] {});
			   i++;
			   //运费只收一单的
			   salesOrderCommand.setTransFee((i == 1) ? salesOrderCommand.getTransFee() : BigDecimal.ZERO);
			   countSalesOrderMoney(salesOrderCommand, salesLines);
			   //如果拆单记录这单拆单的方式
			   salesOrderCommand.setSubOrderSource(mapList.size() > 1 ? null : Constants.AUTO);
			   if("STORE".equals(com.getTargetType())){
				   ShoOutLetInfo sho =  shoOutLetInfoDao.queryStore(com.getTargetCode(), com.getShopId());
				   if(sho!=null){
					   salesOrderCommand.setTargetCode(sho.getStoreCode());
					 //add by chenping 20170814 begin 保存原始的门店或者ec仓code
					   salesOrderCommand.setOriginalTargetCode(sho.getStoreCode());
					 //add by chenping 20170814 end 保存原始的门店或者ec仓code
					   salesOrderCommand.setIsDirectWmsOrder(SysWmsStatus.SHOPDOG);
					   //xin.feng 开启路由的shopDog订单需要走指定仓逻辑
					   if (shop.getIsOrderRoute()) {
						   salesOrderCommand.setIsAssignWh(Boolean.TRUE);
						   salesOrderCommand.setWarehouseCode(WmsConstants.SHOP_DOG_WHCODE);
					   }
				   }else{
					     throw new BusinessException(ErrorCode.SO_QUERY_STORE_ERROR);
				   }
			   }else{
				   salesOrderCommand.setTargetCode(com.getTargetCode());
				 //add by chenping 20170814 begin 保存原始的门店或者ec仓code
				   salesOrderCommand.setOriginalTargetCode(com.getTargetCode());
				 //add by chenping 20170814 end 保存原始的门店或者ec仓code
				   salesOrderCommand.setIsDirectWmsOrder(SysWmsStatus.BZWMS);
			   }
			   salesOrderCommand.setIsOmnichannelParam(Boolean.TRUE);
			   //新增星盘派单号
			   salesOrderCommand.setAllocationCode(com.getAllocationCode());
			   salesOrderCommand.setSoLineCommandList(salesLines);
	            resultList.add(salesOrderCommand);
		  }
		return resultList;
	}
	
	private void countSalesOrderMoney(SalesOrder soCmd, List<? extends SalesOrderLine> newLineCommandList) {
		BigDecimal soAmountBeforeDiscount = BigDecimal.ZERO;
		BigDecimal soAmountAfterDiscount = BigDecimal.ZERO;
		BigDecimal soFinanceTotalAmount = BigDecimal.ZERO;
		BigDecimal soHeadDiscount = BigDecimal.ZERO;
		BigDecimal slTotalPointUsed = BigDecimal.ZERO;
		BigDecimal slLineDiscount = BigDecimal.ZERO;
		BigDecimal slTotalDiscount = BigDecimal.ZERO;
		BigDecimal slVirtualAmount = BigDecimal.ZERO;
		BigDecimal orderTaxFee = BigDecimal.ZERO;
		
		for (SalesOrderLine solCmd : newLineCommandList) {
			soAmountBeforeDiscount = soAmountBeforeDiscount.add(solCmd.getTotalAmount());
			soAmountAfterDiscount = soAmountAfterDiscount.add(solCmd.getTotalAmountAfterDiscount());
			soFinanceTotalAmount = soFinanceTotalAmount.add(solCmd.getInvoiceTotalAmount());
			slLineDiscount = slLineDiscount.add(solCmd.getLineDiscount());
			slTotalDiscount = slTotalDiscount.add(solCmd.getTotalDiscount());
			slTotalPointUsed = slTotalPointUsed.add(solCmd.getTotalPointUsed());
			slVirtualAmount = slVirtualAmount.add(solCmd.getVirtualAmount());
			BigDecimal subOrderTaxFee = solCmd.getSubOrderTaxFee() != null ? solCmd.getSubOrderTaxFee() : BigDecimal.ZERO;
			orderTaxFee = orderTaxFee.add(subOrderTaxFee);
		}
		soHeadDiscount = slTotalDiscount.subtract(slLineDiscount);
		
		soCmd.setAmountBeforeDiscount(soAmountBeforeDiscount);
		soCmd.setAmountAfterDiscount(soAmountAfterDiscount);
		soCmd.setFinanceTotalAmount(soFinanceTotalAmount);
		soCmd.setHeadDiscount(soHeadDiscount);
		soCmd.setLinesDiscount(slLineDiscount);//订单行折扣
		soCmd.setDiscountTotal(slTotalDiscount);//订单整单折扣=订单头折扣+订单行折扣
		soCmd.setPaymentDiscount(BigDecimal.ZERO);//tmall没有该金额
		soCmd.setTotalOuterPoint(slTotalPointUsed);
		soCmd.setUsePoint(slTotalPointUsed);
		soCmd.setTotalInnerPoint(BigDecimal.ZERO);
		soCmd.setVirtualAmount(slVirtualAmount);
		soCmd.setOrderTaxFee(orderTaxFee);
	}

	@Override
	public List<SalesOrderCommand> splitOrderByShippingMethods(CompanyShop shop, SalesOrderCommand srcSoCmd) {
		//拆单后的多单集合
		List<SalesOrderCommand> resultList = new ArrayList<SalesOrderCommand>();
		List<SalesOrderLineCommand> salesOrderLineList = srcSoCmd.getSoLineCommandList();
		//定义一个map集合用于分组
		Map<String, List<SalesOrderLineCommand>> mapList = new HashMap<String, List<SalesOrderLineCommand>>();
		 //遍历集合以targetCode 为键，以SalesOrderLineCommand为值保存到mapList中
		  for (Iterator<SalesOrderLineCommand> it = salesOrderLineList.iterator(); it.hasNext();){
			  SalesOrderLineCommand command  = (SalesOrderLineCommand) it.next();
			  Sku sku = skuDao.findById(command.getSku());
			  //如果在这个map中包含有相同的键，这创建一个集合将其存起来
			   if (mapList.containsKey(sku.getShippingMethods()))
			   {
			    List<SalesOrderLineCommand> syn = mapList.get(sku.getShippingMethods());
			    syn.add(command);
			    //如果没有包含相同的键，在创建一个集合保存数据
			   } else {
			    List<SalesOrderLineCommand> syns = new ArrayList<SalesOrderLineCommand>();
				    syns.add(command);
				    mapList.put(sku.getShippingMethods(), syns);
			   }
		  }
		  int i = 0;
		  //遍历map集合
		  for (Map.Entry<String, List<SalesOrderLineCommand>> m : mapList.entrySet()){
			   //获取所有的值
			   List<SalesOrderLineCommand> salesLines = m.getValue();
			   SalesOrderCommand salesOrderCommand = new SalesOrderCommand();
			   BeanUtils.copyProperties(srcSoCmd, salesOrderCommand, new String[] {});
			   i++;
			   //运费只收一单的
			   salesOrderCommand.setTransFee((i == 1) ? salesOrderCommand.getTransFee() : BigDecimal.ZERO);
			   countSalesOrderMoney(salesOrderCommand, salesLines);
			   //如果拆单记录这单拆单的方式
			   salesOrderCommand.setSubOrderSource(mapList.size() > 1 ? null : Constants.AUTO);
			   salesOrderCommand.setSoLineCommandList(salesLines);
	            resultList.add(salesOrderCommand);
		  }
		return resultList;

	}
}