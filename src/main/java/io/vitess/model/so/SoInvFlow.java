package io.vitess.model.so;



import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 库存流水
 * @date 2015年8月28日 上午10:58:15
 */
//@TableName("t_td_so_inv_flow")
public class SoInvFlow extends SuperEntity {

	private static final long serialVersionUID = 1L;

	private Long soId;


	private String skuCode;


	private Long qty;


	private String btachCode;


	private String barchNo;


	private String invStatus;


	private String whCode;


	private Date transactionTime;


	private String invOwner;


	private Date inputTime;


	private Integer dataType;


	private Boolean marketAbility;


	private Long shopId;

}
