package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
//@TableName("t_td_sales_order_payment_info")
public class SalesOrderPaymentInfo extends SuperEntity{

	private static final long serialVersionUID = -6383761322403504203L;

//	private PaymentType paymentType;
	private Integer paymentType;

	private String paymentBank;

	private BigDecimal payAmount;

	private String payNo;

	private Date paymentTime;

	private String remark;

	private String paymentAccount;

	private SalesOrder salesOrder;

	private Integer isCompleted;

	private Long shopId;
}
