package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;

/**
 * 过单pac、过仓都不再使用该类（逐渐其他地方也不再推荐使用）
 * @author fanht
 *
 */
@Entity
@Table(name = "t_so_taobao_send")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
public class SoTaoBaoSend extends BaseModel {

	private static final long serialVersionUID = 4258703437459065240L;

	private Long id;
	/**
	 * 平台订单号(主交易ID)
	 */
	private String platformOrderCode;
	
	private Long shopId;
	
   /**
     * 全渠道门店编号（若派到的是门店，则此处为门店code；若派到的是电商仓，则此处为电商仓的名字）
     */
    private String targetCode;
    /**
     * 派到的是门店(STORE)还是电商仓(WAREHOUSE)。
     */
    private String targetType;
    
	/** tmalloms处理状态（0：未处理, 10: 处理成功，5：处理失败） **/
	private Integer processStatus = 0;
	
	/** tmalloms处理数据结果 **/
	private String processResult;
	
	private Long salesOrderId;
	/**
	 * 记录在途和接单类型  1代表门店发货 3转派
	 */
	private int type;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "PLATFORM_ORDER_CODE")
	public String getPlatformOrderCode() {
		return platformOrderCode;
	}
	public void setPlatformOrderCode(String platformOrderCode) {
		this.platformOrderCode = platformOrderCode;
	}
	@Column(name = "SHOP_ID")
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	@Column(name = "TARGET_CODE")
	public String getTargetCode() {
		return targetCode;
	}
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	@Column(name = "TARGET_TYPE")
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	@Column(name = "PROCESS_STATUS")
	public Integer getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}
	@Column(name = "PROCESS_RESULT")
	public String getProcessResult() {
		return processResult;
	}
	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}
	@Column(name = "SALES_ORDER_ID")
	public Long getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(Long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	@Column(name = "TYPE")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
   
    
}
