package io.vitess.enums;

/**
 * 任务处理状态
 * 
 * @className com.jumbo.model.TaskProcessStatus
 * @author hailiang.jiang
 * @date 2014年12月18日 下午3:25:10
 */
public enum TaskProcessStatus {
	CREATED(0), // 新建
	PROCESS_WAIT(2), // 等待处理
	PROCESS_SUCCESS(10), // 处理成功
	PROCESS_FAILED(99); // 处理失败

	private int value;

	private TaskProcessStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static TaskProcessStatus valueOf(int value) {
		switch (value) {
		case 0:
			return CREATED;
		case 2:
			return PROCESS_WAIT;
		case 10:
			return PROCESS_SUCCESS;
		case 99:
			return PROCESS_FAILED;
		default:
			throw new IllegalArgumentException();
		}
	}
}
