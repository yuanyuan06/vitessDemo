package io.vitess.model.so;

import com.jumbo.model.BaseModel;
import com.jumbo.model.sales.enums.PaymentType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "t_td_sales_order_payment_info")
public class SalesOrderPaymentInfo extends BaseModel{

	private static final long serialVersionUID = -6383761322403504203L;

	private Long id;

	/** 销售订单ID，关联SALES_ORDER_ID */
	private SalesOrder salesOrder;

	/** 支付方式:从SCM处获得列表 */
	private PaymentType paymentType;

	/** 支付相关银行：在线支付时的追加信息，可能为空 */
	private String paymentBank;

	/** 支付金额 */
	private BigDecimal payAmount;

	/** 支付流水，让财务获取到对应支付记录的交易流水号 */
	private String payNo;

	/** 付款时间 */
	private Date paymentTime;

	/** 是否整单支付完成，当整单都全部支付完成时，告知整单支付完成的一个标记 */
	private Integer isCompleted;

	/** 备注 */
	private String remark;
	
	/** 主支付帐号 */
	private String paymentAccount;
	
    //店铺ID
    private Long shopId;

	public SalesOrderPaymentInfo(){}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALES_ORDER_ID")
	public SalesOrder getSalesOrder(){
		return this.salesOrder;
	}

	@Column(name = "PAYMENT_TYPE", columnDefinition = "integer")
	@Type(type = "loxia.dao.support.GenericEnumUserType", parameters = { @Parameter(name = "enumClass", value = "com.jumbo.model.sales.enums.PaymentType") })
	public PaymentType getPaymentType(){
		return this.paymentType;
	}

	@Column(name = "PAYMENT_BANK", length = 64)
	public String getPaymentBank(){
		return this.paymentBank;
	}

	@Column(name = "PAY_AMOUNT")
	public BigDecimal getPayAmount(){
		return this.payAmount;
	}

	@Column(name = "PAY_NO", length = 64)
	public String getPayNo(){
		return this.payNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAYMENT_TIME")
	public Date getPaymentTime(){
		return this.paymentTime;
	}

	@Column(name = "IS_COMPLETED")
	public Integer getIsCompleted(){
		return this.isCompleted;
	}

	@Column(name = "REMARK", length = 256)
	public String getRemark(){
		return this.remark;
	}
	
	@Column(name = "PAYMENT_ACCOUNT", length = 64)
	public String getPaymentAccount(){
		return paymentAccount;
	}

	public void setId(Long id){
		this.id = id;
	}

	public void setSalesOrder(SalesOrder salesOrder){
		this.salesOrder = salesOrder;
	}

	public void setPaymentType(PaymentType paymentType){
		this.paymentType = paymentType;
	}

	public void setPaymentBank(String paymentBank){
		this.paymentBank = paymentBank;
	}

	public void setPayAmount(BigDecimal payAmount){
		this.payAmount = payAmount;
	}

	public void setPayNo(String payNo){
		this.payNo = payNo;
	}

	public void setPaymentTime(Date paymentTime){
		this.paymentTime = paymentTime;
	}

	public void setIsCompleted(Integer isCompleted){
		this.isCompleted = isCompleted;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public void setPaymentAccount(String paymentAccount){
		this.paymentAccount = paymentAccount;
	}
	
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}
