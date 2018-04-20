package io.vitess.common;

import io.vitess.command.CopyableInterface;
import io.vitess.command.DefaultCopyable;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public abstract class PropertyUtil {
    @SuppressWarnings("rawtypes")
	public static void copyProperties(Object fromBean, Object toBean, CopyableInterface copyable) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class clazz = fromBean.getClass();
        PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(clazz);
        for (PropertyDescriptor property : properties) {
            String propertyName = property.getName();
            if (propertyName.equals("class") || property.getReadMethod() == null) {
                continue;
            }

            Object propertyValue = PropertyUtils.getProperty(fromBean, propertyName);
            if (PropertyUtils.getPropertyDescriptor(toBean, propertyName) != null && copyable.isCopyable(propertyName, propertyValue, clazz)) {
                PropertyUtils.setProperty(toBean, propertyName, propertyValue);
            }
        }
    }

    public static void copyProperties(Object fromBean, Object toBean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        copyProperties(fromBean, toBean, new DefaultCopyable());
    }

}
