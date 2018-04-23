package io.vitess.service.common;

import io.vitess.common.User;
import io.vitess.exception.WorkFlowException;
import io.vitess.model.base.WorkTask;
import io.vitess.model.so.SalesOrder;
import io.vitess.service.BusinessException;

import java.util.List;

public interface SalesOrderTaskFactory extends TaskFactory<SalesOrder>{

	public Boolean needSuspend(WorkTask task, Integer nodeNo) throws WorkFlowException;
	
	public Boolean isAutoToWh(WorkTask task, Integer nodeNo) throws WorkFlowException;
	
	public Boolean needSplitInvoice(WorkTask task, Integer nodeNo) throws WorkFlowException;

	/**
	 * 验证当前节点（这个算法已不推荐使用fanht，请使用带shopId的）
	 * 
	 * @param slipCode
	 * @param currentNodeNo
	 * @param errorCode
	 * @param errorMsgParam
	 * @return
	 * @throws BusinessException
	 */
	WorkTask validateCurrentNo(String slipCode, Integer currentNodeNo, Integer errorCode, Object[] errorMsgParam) throws BusinessException;
	
	/**
	 * 验证当前节点
	 * @param slipCode
	 * @param shopId
	 * @param currentNodeNo
	 * @param errorCode
	 * @param errorMsgParam
	 * @return
	 * @throws BusinessException
	 */
	WorkTask validateCurrentNo(String slipCode, Long shopId, Integer currentNodeNo, Integer errorCode, Object[] errorMsgParam) throws BusinessException;
	
	/**
	 * 验证当前节点(异常不会滚)（这个算法已不推荐使用fanht，请使用带shopId的）
	 * @param slipCode
	 * @param currentNodeNo
	 * @param errorCode
	 * @param errorMsgParam
	 * @return
	 * @throws BusinessException
	 */
	WorkTask validateCurrentNoForNoBack(String slipCode, Integer currentNodeNo, Integer errorCode, Object[] errorMsgParam) throws BusinessException;
	
	/**
	 * 
	 * @Description:  新增没有shopId的时候验证节点
	 * @author zhiyong.shi
	 * 2017年6月13日
	 */
	WorkTask validateCurrentNoForNoBack(String slipCode, Integer currentNodeNo, Integer errorCode, Object[] errorMsgParam, Integer currentNodeNo2, Integer currentNodeNo3) throws BusinessException;

	/**
	 * 验证当前节点(不回滚)
	 * @param slipCode
	 * @param shopId
	 * @param currentNodeNo
	 * @param errorCode
	 * @param errorMsgParam
	 * @return
	 * @throws BusinessException
	 */
	WorkTask validateCurrentNoForNoBack(String slipCode, Long shopId, Integer currentNodeNo, Integer errorCode, Object[] errorMsgParam, Integer currentNodeNo2, Integer currentNodeNo3) throws BusinessException;
	
	/**
	 * 
	 * @author hailiang.jiang
	 * @date 2015年10月26日 下午5:15:10
	 * @param task
	 * @param currentNodeNos
	 * @param errorMsg
	 * @return
	 * @throws BusinessException
	 */
	void validateWorkFlowNode(WorkTask task, List<Integer> currentNodeNos, String errorMsg) throws BusinessException;

	
	@Deprecated
	WorkTask modifySoTime(WorkTask task, Integer nodeNo, User user) throws WorkFlowException;
    
    WorkTask abandonToPacs(WorkTask task, Integer nodeNo, User user) throws WorkFlowException;
    
    WorkTask saveSoToEdw(WorkTask task, Integer nodeNo, User user) throws WorkFlowException;
    
	@Deprecated
	WorkTask soCreateToNextNode(WorkTask task, Integer nodeNo, User user) throws WorkFlowException;
    
//	/**
//	 * 基于店铺是否支持分批发货走不同分支
//	 * 
//	 * @param task
//	 * @param nodeNo
//	 * @return
//	 * @throws WorkFlowException
//	 */
//	@Deprecated
//	String checkForDelivery(WorkTask task, Integer nodeNo) throws WorkFlowException;
    
}
