package io.vitess.service.so;

import io.vitess.common.ErrorCode;
import io.vitess.constants.PlatformSouceContant;
import io.vitess.dao.base.CompanyShopDao;
import io.vitess.dao.mq.MqSoLogDao;
import io.vitess.enums.MqSoLogStatus;
import io.vitess.enums.PlatformType;
import io.vitess.enums.SalesOrderType;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.mq.MqSoLog;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("platformSoManagerProxy")
public class PlatformSoManagerProxyImpl extends BaseManagerImpl implements PlatformSoManagerProxy {

    private static final long serialVersionUID = 2213885134142701455L;

    @Autowired
    private MqSoLogDao mqSoLogDao;

    @Autowired
    private CompanyShopDao companyShopDao;

    @Autowired
    private PlatformSoManager platformSoManager;
    
    @Value("${parametric.create.so.thread.count}")
    private int ThreadCount;

    /**
     * 淘宝创单
     */
    @Override
    public void createTaobaoSo() {
        //店铺的创单方式 1：独立线程；0：公共线程；fanht
        //List<CompanyShopCommand> shopIdsList = companyShopDao.findAllShopList();
    	// 查询店铺为开启状态,并采用公共线程创单的店铺
        List<CompanyShop> shopIdsList = companyShopDao.findShopListGeneralOrder();
        
        // 获取订单来源列表
        Map<String, String> map = PlatformSouceContant.loadPlatformSouceData();
        // 淘宝平台类型为1
        int platformType = PlatformType.TAOBAO_PLATFORM.getValue();
        
        for (int i = 0; i < shopIdsList.size(); i++) {
            Long shopId = shopIdsList.get(i).getId();
            //List<Long> mqSoLogIds = mqSoLogDao.findByShopAndStatusAndPlatformType(platformType, shopId, 2, new SingleColumnRowMapper<Long>());
//            t_mq_so_log 获取 当前店铺, 平台为淘宝, 状态为等待转换, 七天内 的订单数据id			MQ_SO_STATUS_WAITING(2, "等待转换")
            List<Long> mqSoLogIds = mqSoLogDao.findMqSoForCreateSo(platformType, shopId, 2, getCreateTime());
            for (int j = 0; j < mqSoLogIds.size(); j++) {
                Long mqSoLogId = mqSoLogIds.get(j);
                try {
                    platformSoManager.createTbSoFromMqSoLog(mqSoLogId, SalesOrderType.PLATFORM_ONLINE_TB, map,shopId);
                } catch(BusinessException be) {
                	String errorMsg = super.getMessage(ErrorCode.BUSINESS_EXCEPTION + be.getErrorCode(), be.getArgs());
                    if (StringUtils.isBlank(errorMsg) || StringUtils.contains(errorMsg, "business_exception_")) {
                    	errorMsg = be.getMessage();
                    } 
    				
//    				MqSoLog mqSoLog = mqSoLogDao.getByPrimaryKey(mqSoLogId);
//    				// 将相对应的mqSoLog状态置成5,错误信息
//    				mqSoLog.setStatus(MqSoLogStatus.MQ_SO_STATUS_ERROR);
//    				mqSoLog.setErrorMsg(errorMsg);
//    				mqSoLogDao.save(mqSoLog);
    				
    				//优化代码fanht
    				mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);

                }catch (Exception e) {
                    mqCreateSoErrorCount(mqSoLogId, shopId, e.getMessage());
                    log.error("---------createTaobaoSo Error, mqSoLogId:" + mqSoLogId + "------------");
                    log.error("",e);
                }
            }
        }
    }

    /**
     * 记录错误次数,不允许超过3次
     * @param mqSoLogId
     */
    private void mqCreateSoErrorCount(Long mqSoLogId, Long shopId, String msg) {
        MqSoLog mqSoLog = mqSoLogDao.findMqSoLogByIdShopId(mqSoLogId, shopId);
        Integer initCount = new Integer(1);
        Integer errorCount = mqSoLog.getErrorCount() == null ? initCount : initCount + mqSoLog.getErrorCount();
        int orderStatus = mqSoLog.getStatus();
        if(errorCount >= 3){
        	orderStatus = MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue();
        	msg = mqSoLog.getErrorMsg() + "(创单错误次数超过3次)";
        }
        
        mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, orderStatus, msg, errorCount);
    }

    private Date getCreateTime(){

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-7);
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());

        return date;
    }

}
