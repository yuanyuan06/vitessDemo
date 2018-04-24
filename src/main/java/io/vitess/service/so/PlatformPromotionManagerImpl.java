package io.vitess.service.so;

import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SalesOrderLineCommand;
import io.vitess.dao.mq.MqSoPromotionLogDao;
import io.vitess.dao.so.PlatformPromotionDao;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.mq.MqSoLog;
import io.vitess.model.mq.MqSoPromotionLog;
import io.vitess.model.so.PlatformPromotion;
import io.vitess.model.so.SalesOrder;
import io.vitess.model.so.SalesOrderLine;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("platformPromotionManager")
public class PlatformPromotionManagerImpl implements PlatformPromotionManager{

	private static final long serialVersionUID = 6239751408050608495L;
	
	protected static final Logger log = LoggerFactory.getLogger(PlatformPromotionManagerImpl.class);
	
	@Autowired
	private MqSoPromotionLogDao mqSoPromotionLogDao;
	@Autowired
	private PlatformPromotionDao platformPromotionDao;
	
	@Override
	public void constructSoPromotionInfo(CompanyShop shop, MqSoLog soLog, SalesOrderCommand soCmd) {
		List<MqSoPromotionLog> mqSoPromotionLogs = mqSoPromotionLogDao.findBySoLogIdAndType(soLog.getId(), MqSoPromotionLog.SO_PLATFORM_PROMOTION_SCOPE_TYPE_ORDER,shop.getId());
		List<PlatformPromotion> platformPromotions = new ArrayList<PlatformPromotion>();
		addPlatformPromotions(mqSoPromotionLogs, platformPromotions);
		
		//获取原始行促销fanht
		List<MqSoPromotionLog> mqSoLinesPromotionLogs = mqSoPromotionLogDao.findBySoLogIdAndType(soLog.getId(), MqSoPromotionLog.SO_PLATFORM_PROMOTION_SCOPE_TYPE_LINE,shop.getId());
		
		List<SalesOrderLineCommand> soLineCommandList = soCmd.getSoLineCommandList();
		for (SalesOrderLineCommand soLineCmd : soLineCommandList) {
			
//			Long platformLineId = 0L;
//			//检查字符串是否是数字fanht
//			if (StringUtils.hasLength(soLineCmd.getPlatformOrderLineCode())&&NumberUtils.isNumber(soLineCmd.getPlatformOrderLineCode())) {
//				platformLineId= Long.parseLong(soLineCmd.getPlatformOrderLineCode());
//			}
//			List<MqSoPromotionLog> mqSoPromotionLineLogs = mqSoPromotionLogDao.findBySoLogIdAndPlatfromLineId(soLog.getId(), platformLineId);

			//新的行促销判断算法fanht
			List<MqSoPromotionLog> mqSoPromotionLineLogs = new ArrayList<MqSoPromotionLog>();
			for(MqSoPromotionLog bean : mqSoLinesPromotionLogs){
				if(NumberUtils.isNumber(soLineCmd.getPlatformOrderLineCode())&&soLineCmd.getPlatformOrderLineCode().equals(String.valueOf(bean.getPlatformLineId()))){
					mqSoPromotionLineLogs.add(bean);
				}
			}
			
			List<PlatformPromotion> linePlatformPromotions = new ArrayList<PlatformPromotion>();
			addPlatformPromotions(mqSoPromotionLineLogs, linePlatformPromotions);
			soLineCmd.setPlatformPromotionList(linePlatformPromotions);
		}
		
		soCmd.setSoPlatformPromotionList(platformPromotions);
	}

	private void addPlatformPromotions(List<MqSoPromotionLog> mqSoPromotionLogs, List<PlatformPromotion> platformPromotions) {
		if (mqSoPromotionLogs == null || mqSoPromotionLogs.isEmpty()) {
			return;
		}
		
		for (MqSoPromotionLog mqSoPromotionLog : mqSoPromotionLogs) {
			PlatformPromotion pp = new PlatformPromotion();
			try {
				BeanUtils.copyProperties(mqSoPromotionLog, pp, new String[] {"id"});
			} catch (Exception e) {
				throw new RuntimeException("copy property from socommand to so error.");
			}
			platformPromotions.add(pp);
		}
	}
    
	@Override
	@Transactional(propagation= Propagation.SUPPORTS)
	public void genratorPlatformLinePromotion(List<PlatformPromotion> linePlatformPromotions, SalesOrderLineCommand solCmd, SalesOrderLine sl, SalesOrder newSo) {
		if (solCmd.getPlatformPromotionList() == null || solCmd.getPlatformPromotionList().isEmpty()) {
			return;
		}
		
		List<PlatformPromotion> platformPromotionList = solCmd.getPlatformPromotionList();
		for (PlatformPromotion platformPromotion : platformPromotionList) {
			platformPromotion.setSalesOrderLine(sl);
			platformPromotion.setSalesOrder(newSo);
		}
		linePlatformPromotions.addAll(solCmd.getPlatformPromotionList());
	}
	
	@Override
	@Transactional(propagation= Propagation.SUPPORTS)
	public void savePlatformPromotions(SalesOrder s, SalesOrderCommand soCmd, List<PlatformPromotion> linePlatformPromotions) {
		savePlatformPromotions(s, soCmd);
		saveLinePlatformPromotions(linePlatformPromotions, s);
	}

	private void savePlatformPromotions(SalesOrder so, SalesOrderCommand soCmd) {
		List<PlatformPromotion> ppList = soCmd.getSoPlatformPromotionList();
		if ( ppList== null || ppList.isEmpty()) {
			return;
		}
		
		for (PlatformPromotion platformPromotion : ppList) {
			PlatformPromotion promotion = new PlatformPromotion();
			BeanUtils.copyProperties(platformPromotion, promotion, new String[] {"id", "salesOrder"});
			promotion.setSalesOrder(so);
			promotion.setPlatformOrderCode(so.getPlatformOrderCode());
			platformPromotionDao.insert(promotion);
		}
		
	}

	private void saveLinePlatformPromotions(List<PlatformPromotion> linePlatformPromotions, SalesOrder s) {
		if (linePlatformPromotions == null || linePlatformPromotions.isEmpty()) {
			return;
		}
		
		for (PlatformPromotion platformPromotion : linePlatformPromotions) {
			platformPromotion.setPlatformOrderCode(s.getPlatformOrderCode());
			platformPromotionDao.insert(platformPromotion);
	    }
	}

}
	