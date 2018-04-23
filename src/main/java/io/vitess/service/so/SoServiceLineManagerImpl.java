package io.vitess.service.so;

import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SoServiceLineCommand;
import io.vitess.dao.so.SoServiceLineDao;
import io.vitess.enums.SoServiceType;
import io.vitess.model.base.Transportator;
import io.vitess.model.mq.CompanyShop;
import io.vitess.model.mq.MqSoLog;
import io.vitess.model.mq.MqSoServiceLineLog;
import io.vitess.model.so.ServiceInstallCompany;
import io.vitess.model.so.SoServiceLine;
import io.vitess.service.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单服务信息
 * 
 * @classname com.jumbo.manager.sales.SoServiceLineManagerImpl
 * @author hailiang.jiang
 * @date 2015年3月4日 下午5:47:01
 * @version: v1.0.0
 * @see
 */
@Service("soServiceLineManager")
@Transactional(propagation= Propagation.REQUIRED)
public class SoServiceLineManagerImpl extends BaseManagerImpl implements SoServiceLineManager {

	private static final long serialVersionUID = -5170972775706450529L;
	
	@Autowired
	private SoServiceLineDao soServiceLineDao;
	@Autowired
	private MqSoServiceLineLogManager mqSoServiceLineLogManager;
	
	@Override
	public SoServiceLine getByPrimaryKey(Long pkId) {
		return soServiceLineDao.findById(pkId);
	}

	@Override
	public List<SoServiceLine> findSoServiceLineList(Long salesOrderId) {
		return this.soServiceLineDao.findSoServiceLineList(salesOrderId);
	}
	
	@Override
	public List<SoServiceLineCommand> findSoServiceLineListBySoAndServiceLine(Long salesOrderId, List<Long> soServiceLineIdList, Long shopId) {
		return this.soServiceLineDao.findSoServiceLineListBySoAndServiceLine(salesOrderId, soServiceLineIdList, shopId);
	}
	
	@Override
	public void applySoServiceInfo(CompanyShop shop, MqSoLog soLog, SalesOrderCommand soCmd) {
		Boolean isParseOrderServiceInfo = shop.getIsParseOrderServiceInfo() == null ? Boolean.FALSE : shop.getIsParseOrderServiceInfo();
		if (isParseOrderServiceInfo) {
			List<MqSoServiceLineLog> mqSoServiceLineLogList = mqSoServiceLineLogManager.findMqSoServiceLineLogList(soLog.getId(),shop.getId());
			List<SoServiceLineCommand> soServiceLineCmdList = new ArrayList<SoServiceLineCommand>();
			for (MqSoServiceLineLog mssll : mqSoServiceLineLogList) {
				SoServiceLineCommand sslCmd = new SoServiceLineCommand();
				sslCmd.setPayment(mssll.getPayment());
				sslCmd.setPlatformLineId(mssll.getPlatformLineId());
				sslCmd.setQty(mssll.getQty());
				sslCmd.setServiceId(mssll.getServiceId());
				sslCmd.setTitle(mssll.getTitle());
				sslCmd.setTmserSpuCode(mssll.getTmserSpuCode());
				sslCmd.setTotalActual(mssll.getTotalActual());
				sslCmd.setUnitPrice(mssll.getUnitPrice());
				sslCmd.setShopId(shop.getId());
				soServiceLineCmdList.add(sslCmd);
			}
			soCmd.setSoServiceLineCmdList(soServiceLineCmdList);
		}
	}
	
	public static void applySoServiceProvider(SoServiceLine ssl, ServiceInstallCompany sic) {
		if (SoServiceType.HOME_DELIVERY_SERVICE.equals(ssl.getServiceType())) {
			ssl.setProviderCode(Transportator.ZTKY_CODE);
			ssl.setProviderName(Transportator.ZTKY_NAME);
		}
		if (sic != null && SoServiceType.HOME_INSTALL_SERVICE.equals(ssl.getServiceType())) {
			ssl.setProviderCode(sic.getCompanyCode());
			ssl.setProviderName(sic.getCompanyName());
		}
	}
	
	@Override
	public void countSoServiceLineMoney(SalesOrderCommand newSalesOrder, List<SoServiceLineCommand> soServiceLineCmdList) {
		if (soServiceLineCmdList == null || soServiceLineCmdList.isEmpty()) {
			newSalesOrder.setInstallFee(BigDecimal.ZERO);
			newSalesOrder.setHomeDeliveryFee(BigDecimal.ZERO);
			return;
		}
		
		BigDecimal sumHomeDeliveryFee = BigDecimal.ZERO;
		BigDecimal sumInstallFee = BigDecimal.ZERO;
		for (SoServiceLineCommand sslCmd : soServiceLineCmdList) {
			if (sslCmd.getServiceTypeInt() != null && sslCmd.getServiceTypeInt().intValue() == SoServiceType.HOME_INSTALL_SERVICE.getValue()) {
				sumInstallFee = sumInstallFee.add(sslCmd.getTotalActual());
			} else if (sslCmd.getServiceTypeInt() != null && sslCmd.getServiceTypeInt().intValue() == SoServiceType.HOME_DELIVERY_SERVICE.getValue()) {
				sumHomeDeliveryFee = sumHomeDeliveryFee.add(sslCmd.getTotalActual());
			}
		}
		newSalesOrder.setInstallFee(sumInstallFee);
		newSalesOrder.setHomeDeliveryFee(sumHomeDeliveryFee);
	}
}
