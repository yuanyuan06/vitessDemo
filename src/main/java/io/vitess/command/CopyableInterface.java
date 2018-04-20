package io.vitess.command;

public interface CopyableInterface {

	boolean isCopyable(String propertyName, Object propertyValue, Class clazz);
}
