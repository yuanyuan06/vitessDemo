package io.vitess.service.so;

import com.alibaba.fastjson.JSON;
import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SalesOrderLineCommand;
import io.vitess.common.ErrorCode;
import io.vitess.common.NumberUtils;
import io.vitess.constants.Constants;
import io.vitess.dao.base.ShoOutLetInfoDao;
import io.vitess.dao.so.*;
import io.vitess.enums.OrderLineType;
import io.vitess.model.base.PackageSkuNum;
import io.vitess.model.base.SkuWarehouseRel;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.so.*;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.BusinessException;
import io.vitess.service.common.PackageSkuNumManager;
import io.vitess.service.common.SkuManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

/**
 * 拆单
 * @author hailiang.jiang
 * @date 2014年7月21日
 * @description SalesOrderSplitManagerImpl
 */
@Service("salesOrderSplitManager")
@Transactional
public class SalesOrderSplitManagerImpl extends BaseManagerImpl implements SalesOrderSplitManager {

	private static final long serialVersionUID = -7644418434343247226L;

	@Autowired
	private SalesOrderLineDao salesOrderLineDao;
	@Autowired
	private SkuManager skuManager;

	@Autowired
	private PackageSkuNumManager packageSkuNumManager;

	@Autowired
	ShoOutLetInfoDao shoOutLetInfoDao;


	/**
	 * 验证组合商品
	 *
	 * @methodName com.jumbo.manager.sales.SalesOrderSplitManagerImpl.validateComboSkuSplit
	 * @author hailiang.jiang
	 * @date 2014年11月27日 下午3:32:24
	 * @param salesOrderLineList
	 * @param soLineIdList
	 * @throws BusinessException
	 */
	@SuppressWarnings("unused")
	private void validateComboSkuSplit(List<SalesOrderLine> salesOrderLineList, List<Long> soLineIdList, Long shopId) throws BusinessException {
		Map<Long, String> comboSoLineMap = new HashMap<Long, String>();//商品明细行（key：订单行ID， value：组合商品标识[PlatformOrderLineCode + "_" +  ComboSkuExtCodeOrgin]）
		Map<String, List<Long>> dataMap = new HashMap<String, List<Long>>(); //key: comboSoLineMap的value，value：订单行ID集合(属于同一组组合商品)

		//包装组合商品数据
		for (SalesOrderLine soLine : salesOrderLineList) {
			if (soLine.getIsComboSku() != null && soLine.getIsComboSku()) {
				String mapKey = soLine.getPlatformOrderLineCode() + "_" + (StringUtils.hasText(soLine.getComboSkuExtCodeOrgin()) ? soLine.getComboSkuExtCodeOrgin() : "");
				if (!dataMap.containsKey(mapKey)) {
					dataMap.put(mapKey, new ArrayList<Long>());
				}
				dataMap.get(mapKey).add(soLine.getId());
				comboSoLineMap.put(soLine.getId(), mapKey);
			}
		}

		// 订单中不包含组合商品
		if (comboSoLineMap.isEmpty()) {
			return;
		}

		//当前提交的请求组合商品行ID
		List<Long> currentRequestComboSkuLineList = new ArrayList<Long>();
		for (Long soLineId : soLineIdList) {
			if (comboSoLineMap.containsKey(soLineId)) {
				currentRequestComboSkuLineList.add(soLineId);
			}
		}

		//当前提交请求的数据中无组合商品
		if (currentRequestComboSkuLineList.isEmpty()) {//无组合商品
			return;
		}

		Map<String, String> errorMsgMap = new HashMap<String, String>();
		for (Long crcSkuLineId : currentRequestComboSkuLineList) {
			String key = comboSoLineMap.get(crcSkuLineId);
			if (StringUtils.hasText(key)) {
				List<Long> comboLineIdList = dataMap.get(key);//当前商品所在的组合商品集合
				if (comboLineIdList != null && !CollectionUtils.isSubCollection(comboLineIdList, currentRequestComboSkuLineList)) {
					processComboSkuErrorMsg(key, errorMsgMap, comboLineIdList, shopId);
				}
			}
		}

		if (errorMsgMap.size() > 0) {
			StringBuilder msgSb = new StringBuilder();
			for (String errorMsg : errorMsgMap.values()) {
				if (msgSb.length() > 0) {
					msgSb.append("<br>");
				}
				msgSb.append(errorMsg);
			}
			throw new BusinessException(ErrorCode.CUSTOMIZATION_TIP, new Object[] {msgSb.toString()});
		}
	}


	private void processComboSkuErrorMsg(String key, Map<String, String> errorMsgMap, List<Long> comboLineIdList, Long shopId) {
		if (errorMsgMap.containsKey(key)) {
			return;
		}

		StringBuilder errorSb = new StringBuilder();
		String comboSkuCode = "";
		int count = 0;
		for (Long comboLineId : comboLineIdList) {
			SalesOrderLine soLine = this.salesOrderLineDao.getSalesOrderLineByIdShopId(comboLineId, shopId);
			if (count == 0) {
				comboSkuCode = soLine.getComboSkuExtCodeOrgin();
			}
			if (soLine != null) {
				if (errorSb.length() > 0) {
					errorSb.append(",");
				}
				errorSb.append(soLine.getExtentionCode());
			}
			count ++;
		}

		errorMsgMap.put(key, "商品[" + errorSb.toString() + "]属于组合商品[组合编码为：" + comboSkuCode + "]，不能进行拆分");
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

	private SalesOrderLineCommand getOldSoLineByNewSoLine(SalesOrderLine sol, List<SalesOrderLineCommand> soLineCommandList) {
		SalesOrderLineCommand resultSolCmd = null;

		for (SalesOrderLineCommand solCmd : soLineCommandList) {
			if (sol.getPlatformOrderLineCode().equals(solCmd.getPlatformOrderLineCode()) && sol.getExtentionCode().equals(solCmd.getExtentionCode()) && sol.getUnitPriceAfterDiscount().doubleValue() == solCmd.getUnitPriceAfterDiscount().doubleValue()) {
				resultSolCmd = solCmd;
				break;
			}
		}

		return resultSolCmd;
	}


	@Override
	public List<SalesOrderCommand> splitTradeByShopSkuWh(SalesOrderCommand srcSoCmd, Map<String, List<SalesOrderLineCommand>> whSoMap) {
		List<SalesOrderCommand> resultList = new ArrayList<SalesOrderCommand>();

		int i = 0;
		for (Entry<String, List<SalesOrderLineCommand>> entry : whSoMap.entrySet()) {
			SalesOrderCommand soCmd = new SalesOrderCommand();
			BeanUtils.copyProperties(srcSoCmd, soCmd, new String[] {});
			soCmd.setWarehouseCode(entry.getKey());

			i++;
			soCmd.setTransFee((i == 1) ? srcSoCmd.getTransFee() : BigDecimal.ZERO);

			List<SalesOrderLineCommand> solCmdListInMap = entry.getValue();
			countSalesOrderMoney(soCmd, solCmdListInMap);

			soCmd.setSubOrderSource(whSoMap.size() == 1 ? null : Constants.AUTO);
			soCmd.setSoLineCommandList(solCmdListInMap);
			resultList.add(soCmd);
		}

		return resultList;
	}


	@Override
	public List<SalesOrderCommand> splitTradeBySkuNum(CompanyShop shop, SalesOrderCommand srcSoCmd) {
		List<SalesOrderCommand> resultList = new ArrayList<SalesOrderCommand>();

		List<List<SalesOrderLineCommand>> allSoLineList = splitSoLineBySkuNum(shop, srcSoCmd.getSoLineCommandList());

		int i = 0;
		for (List<SalesOrderLineCommand> soLineList : allSoLineList) {
			SalesOrderCommand soCmd = new SalesOrderCommand();
			BeanUtils.copyProperties(srcSoCmd, soCmd, new String[] {});

			i++;
			soCmd.setTransFee((i == 1) ? srcSoCmd.getTransFee() : BigDecimal.ZERO);

			countSalesOrderMoney(soCmd, soLineList);

			soCmd.setSubOrderSource(allSoLineList.size() == 1 ? null : Constants.AUTO);
			soCmd.setSoLineCommandList(soLineList);
			resultList.add(soCmd);
		}

		return resultList;
	}


	private List<List<SalesOrderLineCommand>> splitSoLineBySkuNum(CompanyShop shop, List<SalesOrderLineCommand> soLineCmdList) {
		List<SalesOrderLineCommand> noNeedSinglePackageSoLineList = new ArrayList<SalesOrderLineCommand>();//不需要单独打包发货的包裹
		List<List<SalesOrderLineCommand>> allSoLineList = new ArrayList<List<SalesOrderLineCommand>>();//第一次List表示拆分订单后的数量，第二层List表示每个包裹包含的商品明细
		for (SalesOrderLineCommand soLineCmd : soLineCmdList) {
			String extentionCode = soLineCmd.getSku().getExtensionCode1();
			PackageSkuNum packageSkuNum = packageSkuNumManager.findPackageSkuNum(shop, extentionCode);
			//查询数量
			if (packageSkuNum == null || packageSkuNum.getQty() <= 0) {
				noNeedSinglePackageSoLineList.add(soLineCmd);
				continue;
			}

			List<SalesOrderLineCommand> currentPackageSoLineList = new ArrayList<SalesOrderLineCommand>();

			//计算包裹数量
			int packageNum = (soLineCmd.getQuantity() / packageSkuNum.getQty()) + ((soLineCmd.getQuantity() % packageSkuNum.getQty()) > 0 ? 1 : 0);
			for (int i = 0; i < packageNum; i++) {
				SalesOrderLineCommand solCmd = new SalesOrderLineCommand();
				BeanUtils.copyProperties(soLineCmd, solCmd, new String[] {});
				if (i == packageNum - 1) {
					solCmd.setQuantity(soLineCmd.getQuantity() - i * packageSkuNum.getQty());
				} else {
					solCmd.setQuantity(packageSkuNum.getQty());
				}
				currentPackageSoLineList.add(solCmd);
			}

			countSoLineMoney(soLineCmd, currentPackageSoLineList);
			for (SalesOrderLineCommand soLCmd : currentPackageSoLineList) {
				List<SalesOrderLineCommand> tempList = new ArrayList<SalesOrderLineCommand>();
				tempList.add(soLCmd);
				allSoLineList.add(tempList);
			}
		}

		if (noNeedSinglePackageSoLineList.size() > 0) {
			allSoLineList.add(0, noNeedSinglePackageSoLineList);
		}

		return allSoLineList;
	}


	@Override
	public List<SalesOrderCommand> splitTradeByO2oSalesServiceType(CompanyShop shop, SalesOrderCommand srcSoCmd) {
		List<SalesOrderCommand> resultList = new ArrayList<SalesOrderCommand>();

		List<List<SalesOrderLineCommand>> allSoLineList = splitSoLineByO2oSalesServiceType(shop, srcSoCmd.getSoLineCommandList());

		int i = 0;
		for (List<SalesOrderLineCommand> soLineList : allSoLineList) {
			SalesOrderCommand soCmd = new SalesOrderCommand();
			BeanUtils.copyProperties(srcSoCmd, soCmd, new String[] {});

			i++;
			soCmd.setTransFee((i == 1) ? srcSoCmd.getTransFee() : BigDecimal.ZERO);

			countSalesOrderMoney(soCmd, soLineList);

			soCmd.setSubOrderSource(allSoLineList.size() == 1 ? null : Constants.AUTO);
			soCmd.setSoLineCommandList(soLineList);
			resultList.add(soCmd);
		}
		return resultList;
	}


	@Override
	public void fillSpiltSmsParam(SalesOrder persistSo, List<String> platformOrderCodeNs, List<String> soLineSkuList, Long shopId) {
		platformOrderCodeNs.add(persistSo.getPlatformOrderCodeN());
		List<SalesOrderLine> persistSoLines = salesOrderLineDao.querySalesOrderLineBySoIdShopId(persistSo.getId(), shopId);
		for (SalesOrderLine persistSoLine : persistSoLines) {
			soLineSkuList.add("[" + persistSoLine.getSkuName() + "*" + persistSoLine.getQuantity() + "]");
		}
	}



	private List<List<SalesOrderLineCommand>> splitSoLineByO2oSalesServiceType(CompanyShop shop, List<SalesOrderLineCommand> soLineCmdList) {
		List<SalesOrderLineCommand> noSpecialSkuSoLineList = new ArrayList<SalesOrderLineCommand>();//非特殊商品
		List<SalesOrderLineCommand> specialSkuSoLineList = new ArrayList<SalesOrderLineCommand>();//特殊商品
		List<SalesOrderLineCommand> giftSoLineList = new ArrayList<SalesOrderLineCommand>();//赠品

		List<List<SalesOrderLineCommand>> allSoLineList = new ArrayList<List<SalesOrderLineCommand>>();//第一次List表示拆分订单后的数量，第二层List表示每个包裹包含的商品明细
		for (SalesOrderLineCommand soLineCmd : soLineCmdList) {
			if (soLineCmd.getOrderLineType() != null && OrderLineType.isNotTmallPlatformGift(soLineCmd.getOrderLineType().getValue())) {//赠品
				giftSoLineList.add(soLineCmd);
				continue;
			}

			if (soLineCmd.getIsPostSalesServiceSku() != null && soLineCmd.getIsPostSalesServiceSku()) {//O2O有服务商品
				specialSkuSoLineList.add(soLineCmd);
				continue;
			} else {
				noSpecialSkuSoLineList.add(soLineCmd); //O2O无服务商品
			}
		}

		if (specialSkuSoLineList.size() > 0) {//如果有特殊商品，则【赠品+特殊商品】为一整单
			specialSkuSoLineList.addAll(giftSoLineList);
			allSoLineList.add(specialSkuSoLineList);
		} else {
			noSpecialSkuSoLineList.addAll(giftSoLineList);
		}
		if (noSpecialSkuSoLineList.size() > 0) {
			allSoLineList.add(noSpecialSkuSoLineList);
		}
		return allSoLineList;
	}


	private void countSoLineMoney(SalesOrderLineCommand oldSoLineCmd, List<SalesOrderLineCommand> currentPackageSoLineList) {
		for (int i = 0; i < currentPackageSoLineList.size(); i++) {
			SalesOrderLineCommand solCmd = currentPackageSoLineList.get(i);
			/** 数量 quantity*/
			BigDecimal currentQtyBigDecimal = new BigDecimal(solCmd.getQuantity());
			/** 吊牌价 */
			solCmd.setListPrice(oldSoLineCmd.getListPrice());
			/** 销售单价(折前单价) */
			solCmd.setUnitPrice(oldSoLineCmd.getUnitPrice());
			/** 销售金额总计=销售单价x数量 */
			solCmd.setTotalAmount(NumberUtils.multiply(solCmd.getUnitPrice(), new BigDecimal(solCmd.getQuantity())));
			/** 折扣后单价=折扣后金额总计/数量，保留两位小数 */
			solCmd.setUnitPriceAfterDiscount(oldSoLineCmd.getUnitPriceAfterDiscount());
			/** 折扣后金额总计=销售金额总计-平摊后行折扣金额 */
			solCmd.setTotalAmountAfterDiscount(NumberUtils.multiply(solCmd.getUnitPriceAfterDiscount(), currentQtyBigDecimal));
			/** 开票单价=开票金额总计/数量，保留两位小数 */
			solCmd.setInvoiceUnitPrice(oldSoLineCmd.getInvoiceUnitPrice());
			/** 开票金额总计=折扣后金额总计-虚拟货币支付金额 */
			solCmd.setInvoiceTotalAmount(NumberUtils.multiply(solCmd.getInvoiceUnitPrice(), currentQtyBigDecimal));
			/** 平摊后行折扣金额=行折扣金额+订单平摊到行的折扣金额 */
			solCmd.setTotalDiscount(solCmd.getTotalAmount().subtract(solCmd.getTotalAmountAfterDiscount()));
			/** 行折扣金额 */
			solCmd.setLineDiscount(BigDecimal.ZERO);//目前改行的值都设为0，因为tmall是没有接进来的
			/** 虚拟货币支付金额(如积分抵扣),由整单平摊过来 */
			solCmd.setVirtualAmount(solCmd.getTotalAmountAfterDiscount().subtract(solCmd.getInvoiceTotalAmount()));
			/** 总共使用积分 */
			solCmd.setTotalPointUsed(NumberUtils.multiply(solCmd.getVirtualAmount(), new BigDecimal(100)).setScale(0));
		}
	}

	@Override
	public List<SalesOrderCommand> splitOrderDesignatedWhBySku(CompanyShop shop, SalesOrderCommand srcSoCmd) {
		List<SalesOrderCommand> resultList = new ArrayList<SalesOrderCommand>();
		List<SalesOrderLineCommand> salesOrderLineList = srcSoCmd.getSoLineCommandList();
		Set<String> skuCodes = new HashSet<String>();
		for(SalesOrderLineCommand line: salesOrderLineList){
			skuCodes.add(line.getSku().getCode());
		}
		// 获取该订单下所有的sku
		String skuCodeStr = rmJsonBrackets(JSON.toJSONString(skuCodes));
		List<SkuWarehouseRel> whLines = skuManager.findSkuWarehouseRel(shop.getId(), skuCodeStr);
		Map<String, List<SalesOrderLineCommand>> whMap = new HashMap<String, List<SalesOrderLineCommand>>();
		// 指定仓的sku
		List<String> rmSkuList = new ArrayList<String>();
		for(SkuWarehouseRel line: whLines){
			for(String sku: skuCodes){
				if(sku.equals(line.getSkuCode())){
					// 移除订单行中需要指定的sku, 保留sku即为rebuy sku
					rmSkuList.add(sku);
				}
			}
			List<SalesOrderLineCommand> soLines = new ArrayList<SalesOrderLineCommand>();
			for(SalesOrderLineCommand saleLine: salesOrderLineList){
				if(line.getSkuCode().equals(saleLine.getSku().getCode())){
					soLines.add(saleLine);
				}
			}
			if(whMap.get(line.getWhCode()) == null){
				whMap.put(line.getWhCode(), soLines);
			}else{
				whMap.get(line.getWhCode()).addAll(soLines);
			}
		}
		skuCodes.removeAll(rmSkuList);
		/*
		 *	需要拆单并指定sku的场景:
		 *			1.订单中不含rebuy 商品的订单
		 *			2.订单中含rebuy 并指定sku 种类 多余 一类
		 */
		if(skuCodes.size() == 0){													// 不含rebuy (可能需要拆单, 取决于指定仓的数量)
			int i = 0;
			for(Entry<String, List<SalesOrderLineCommand>> entry: whMap.entrySet()){
				SalesOrderCommand saleCommand = new SalesOrderCommand();
				BeanUtils.copyProperties(srcSoCmd, saleCommand, new String[]{"id"});
				saleCommand.setIsAssignWh(true);
				saleCommand.setWarehouseCode(entry.getKey());
				saleCommand.setSoLineCommandList(entry.getValue());
				i++;
				saleCommand.setTransFee((i == 1) ? srcSoCmd.getTransFee() : BigDecimal.ZERO);

				countSalesOrderMoney(saleCommand, saleCommand.getSoLineCommandList());

				saleCommand.setSubOrderSource(whMap.size() == 1 ? null : Constants.AUTO);
				resultList.add(saleCommand);
			}
		}else if(skuCodes.size() > 0 && whMap.size() == 1){						// 含rebuy 指定仓库的sku只有一类 (无需拆单)
			srcSoCmd.setIsAssignWh(true);
			srcSoCmd.setWarehouseCode(whLines.get(0).getWhCode());
			resultList.add(srcSoCmd);
		}else{																		// 不需要指定的     含rebuy 并且 指定仓库为0 或指定仓库大于1(不需要拆单&指定仓)
			// skuCodes.size() > 0 && (whMap.size() == 0 || whMap.size() > 1)
			resultList.add(srcSoCmd);
		}
		return resultList;
	}
	public static String rmJsonBrackets(String jsonStr){
		return jsonStr.replaceAll("^\\[(.*)\\]$", "$1");
	}
}