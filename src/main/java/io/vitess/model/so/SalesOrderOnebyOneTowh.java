package io.vitess.model.so;

import com.jumbo.model.BaseModel;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 依次过仓未处理数据
 * @author zhiyong.shi
 * 2017年6月8日
 */
@Entity
@Table(name = "t_td_sales_one_by_one_to_wh")
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION, dynamicUpdate=true)
public class SalesOrderOnebyOneTowh extends BaseModel {


	private static final long serialVersionUID = -4999972147115543016L;
	
	/** pk **/
	private Long id;
	
	/** tmalloms处理状态（0：未处理, 10: 处理成功，5：处理失败） **/
	private Integer processStatus = 0;
	
	/** tmalloms处理数据结果 **/
	private String processResult;
	
	  //店铺ID
    private Long shopId;
    
    private Date createTime;
    //订单ID
    private Long soId;
    
	/** 仓库编码 */
	private String warehouseCode;
	
	private Date updateTime;
	
    
	@Column(name = "WAREHOUSE_CODE")
	public String getWarehouseCode(){
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode){
		this.warehouseCode = warehouseCode;
	}
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	@Column(name="PROCESS_STATUS")
	public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}
	@Column(name="PROCESS_RESULT")
	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="SO_ID")
	public Long getSoId() {
		return soId;
	}
	public void setSoId(Long soId) {
		this.soId = soId;
	}
	@Column(name="UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
