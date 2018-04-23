package io.vitess.service.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class WorkFlowEngineUtil implements ApplicationContextAware, InitializingBean {
    private static WorkFlowEngineUtil instance;
    private WorkFlowEngine workFlowEngine;

    private static Map<String, WeakReference<ApplicationContext>> contextMap_ = new HashMap<String, WeakReference<ApplicationContext>>();

    private String currentKey_;

    private ApplicationContext currentContext_;

    private WorkFlowEngineUtil() {

    }

    public static WorkFlowEngineUtil getInstance() {
        if (null == instance) {
            instance = new WorkFlowEngineUtil();
        }
        return instance;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (currentKey_ == null) {
            throw new BeanInitializationException("Property 'contextKey' is required.");
        }
        contextMap_.put(currentKey_, new WeakReference<ApplicationContext>(currentContext_));
        currentKey_ = null;
        currentContext_ = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        currentContext_ = applicationContext;
    }
}
