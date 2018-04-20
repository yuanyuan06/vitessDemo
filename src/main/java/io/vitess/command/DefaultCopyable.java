package io.vitess.command;

public class DefaultCopyable implements CopyableInterface {

	@Override
	public boolean isCopyable(String propertyName, Object propertyValue, Class clazz) {
		return true;
	}

}
