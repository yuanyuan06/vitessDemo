package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.util.Date;

@TableName("t_so_modify_log")
public class SoModifyLog extends SuperEntity {
	private static final long serialVersionUID = -3957366275486503394L;

	@TableField("ORDER_CODE")
	private String orderCode;

	@TableField("CONTENT")
	private String content;

	@TableField("CREATE_TIME")
	private Date createTime = new Date();

	@TableField("SHOP_ID")
	private Long shopId;
    
}
