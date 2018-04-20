package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.PaymentType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("t_td_sales_order_payment_info")
public class SalesOrderPaymentInfo extends SuperEntity{

	private static final long serialVersionUID = -6383761322403504203L;

	@TableField("PAYMENT_TYPE")
	private PaymentType paymentType;

	@TableField("PAYMENT_BANK")
	private String paymentBank;

	@TableField("PAY_AMOUNT")
	private BigDecimal payAmount;

	@TableField("PAY_NO")
	private String payNo;

	@TableField("PAYMENT_TIME")
	private Date paymentTime;

	@TableField("REMARK")
	private String remark;

	@TableField("PAYMENT_ACCOUNT")
	private String paymentAccount;

	@TableField("SALES_ORDER_ID")
	private SalesOrder salesOrder;

	@TableField("IS_COMPLETED")
	private Integer isCompleted;

	@TableField("shopId")
	private Long shopId;
}
