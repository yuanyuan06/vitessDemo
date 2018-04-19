/**
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Jumbomart. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jumbo.
 * 
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 * 
 */

package io.vitess.service;

import io.vitess.common.ErrorCode;
import io.vitess.exception.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.Locale;

public abstract class BaseManagerImpl implements BaseManager {
    private static final long serialVersionUID = 13056181500802085L;

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    protected ApplicationContext applicationContext;
    
    public BusinessException workFlowExceptionConverter(WorkFlowException e) {
        BusinessException be = null;
        if (e instanceof InvalidWorkTaskActorException) {
            be = new BusinessException(ErrorCode.INVALID_WORK_TASK_ACTOR);
        } else if (e instanceof SlipTypeNotMatchException) {
            be = new BusinessException(ErrorCode.SLIP_TYPE_NOT_MATCH);
        } else if (e instanceof TransitionFlowFailException) {
            be = new BusinessException(ErrorCode.TRANSITION_FLOW_FAIL);
        } else if (e instanceof TransitionNotExistsException) {
            be = new BusinessException(ErrorCode.TRANSITION_NOT_EXISTS);
        } else if (e instanceof WorkFlowDefinitionErrorException) {
            be = new BusinessException(ErrorCode.WORK_FLOW_DEFINITION_ERROR);
        } else if (e instanceof WorkFlowNodeNotExistsException) {
            be = new BusinessException(ErrorCode.WORK_FLOW_NODE_NOT_EXISTS);
        } else if (e instanceof WorkFlowNotExistsException) {
            be = new BusinessException(ErrorCode.WORK_FLOW_NOT_EXISTS);
        }else if  (e  instanceof  WorkTaskException){
            WorkTaskException taskException = (WorkTaskException)e;
            be = workTaskExceptionConverter(taskException);
        }else {
            be = new BusinessException("Not define Exception");
        }
        return be;
    }

    public BusinessException workFlowActionExceptionConverter(WorkFlowActionException e) {
        BusinessException be = null;
        if (e instanceof ForkTransitionNotExistsException) {
            ForkTransitionNotExistsException f = (ForkTransitionNotExistsException) e;
            be = new BusinessException(ErrorCode.FORK_TRANSITION_NOT_EXISTS, new Object[] {f.getWorkFlowCode(), f.getTaskNo(), f.getFromNode(), f.getCurrentFromNode()});
        } else {
            be = new BusinessException("Not define Exception");
        }
        return be;
    }

    public BusinessException workTaskExceptionConverter(WorkTaskException e) {
        BusinessException be = null;
        if (e instanceof TaskNotExistsException) {
            TaskNotExistsException t = (TaskNotExistsException) e;
            be = new BusinessException(ErrorCode.WORK_FLOW_TASK_NOT_EXISTS, new Object[] {t.getWorkFlowCode()});
        } else if (e instanceof WrongCurrentTaskNodeException) {
            WrongCurrentTaskNodeException t = (WrongCurrentTaskNodeException) e;
            be = new BusinessException(ErrorCode.TASK_CURRENT_NODE_ERROR, new Object[] {t.getWorkFlowCode(), t.getTaskNo(), t.getFromNode(), t.getCurrentFromNode()});
        } else {
            be = new BusinessException("Not define Exception");
        }
        return be;
    }
    
    protected String getMessage(String key, Object... args) {
        String message = null;
        try {
        	message = applicationContext.getMessage(key, args, Locale.SIMPLIFIED_CHINESE);
        } catch(Exception e) {
        	log.error("--------------- message code not existed: " + key + " ----------------");
        }
        return message == null ? "" : message;
    }
    
    protected String getBusinessExceptionMessage(BusinessException be) {
    	String errorMsg = "";
    	if (ErrorCode.ERROR_NOT_SPECIFIED == be.getErrorCode()) {
    		Throwable throwable = be.getCause();
    		if (throwable instanceof BusinessException) {
    			BusinessException ecpt = (BusinessException) throwable;
    			errorMsg = errorMsg + " " + getBusinessExceptionMessage(ecpt);
    		} else if (throwable != null) {
    			errorMsg = throwable.getMessage();
    		}
    	}
    	
    	errorMsg = errorMsg + " " + getMessage(ErrorCode.BUSINESS_EXCEPTION + be.getErrorCode(), be.getArgs());
        if (StringUtils.isBlank(errorMsg) || StringUtils.contains(errorMsg, "business_exception_")) {
        	errorMsg = be.getMessage();
        }
        return errorMsg;
    }
}
