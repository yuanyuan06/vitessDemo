package io.vitess.model.so;



import io.vitess.common.SuperEntity;

import java.util.Date;

//@TableName("t_so_modify_log")
public class SoModifyLog extends SuperEntity {
	private static final long serialVersionUID = -3957366275486503394L;


	private String orderCode;


	private String content;


	private Date createTime = new Date();


	private Long shopId;
    
}
