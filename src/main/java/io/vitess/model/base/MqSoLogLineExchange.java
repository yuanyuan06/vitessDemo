package io.vitess.model.base;

import io.vitess.enums.MqSoLogStatus;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  目前创单失败一般有2种原因，一种是店铺配置问题，这个店铺重新配置即可；第二种是订单的SKU错误，无法和系统中数据匹配上。第二种需要通过OMS页面，下载上传excel表对SKU进行更正。
 *	本类用于上传下载的数据类型定义。
 */
public class MqSoLogLineExchange implements Serializable {

	private static final long serialVersionUID = -7237249977581128390L;

	/**t_mq_so_log.code**/
    private String code;
    
    /**t_mq_so_log.status**/
    private int status;
    
    /**t_mq_so_log.error_msg**/
    private String errorMsg;
    
    /**t_mq_so_line_log.id**/
    private String tomsLineId;
    
    /**t_mq_so_line_log.platform_line_id**/
    private String platformLineId;
    
    /**t_mq_so_line_log.platform_sku_name**/
    private String platformSkuName;
    
    /**t_mq_so_line_log.qty**/
    private Integer lineQty;
    
    /**t_mq_so_line_log.total_actual**/
    private BigDecimal lineAmount;
    
    /**t_mq_so_line_log.extention_code**/
    private String extentionCode;
    
    private String newExtentionCode;
    
    /**上传后结果服务处理，显示结果的字段 */
    private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getTomsLineId() {
		return tomsLineId;
	}

	public void setTomsLineId(String tomsLineId) {
		this.tomsLineId = tomsLineId;
	}

	public String getPlatformLineId() {
		return platformLineId;
	}

	public void setPlatformLineId(String platformLineId) {
		this.platformLineId = platformLineId;
	}

	public String getPlatformSkuName() {
		return platformSkuName;
	}

	public void setPlatformSkuName(String platformSkuName) {
		this.platformSkuName = platformSkuName;
	}

	public Integer getLineQty() {
		return lineQty;
	}

	public void setLineQty(Integer lineQty) {
		this.lineQty = lineQty;
	}

	public BigDecimal getLineAmount() {
		return lineAmount;
	}

	public void setLineAmount(BigDecimal lineAmount) {
		this.lineAmount = lineAmount;
	}

	public String getExtentionCode() {
		return extentionCode;
	}

	public void setExtentionCode(String extentionCode) {
		this.extentionCode = extentionCode;
	}

	public String getNewExtentionCode() {
		return newExtentionCode;
	}

	public void setNewExtentionCode(String newExtentionCode) {
		this.newExtentionCode = newExtentionCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
   public String getStatusName() {
        return MqSoLogStatus.valueOf(this.status).getName();
    }
   
   /**
    * 
    * @param statusName
    * 上传上来的理论上都是转换失败的，所以这里写死了。
    * 即使有其他类型的，也没有关系，这个字段只是为了上传后，再返回excel给用户看上传的结果。
    * 
    */
   public void setStatusName(String statusName) {
	   this.status = 5;
   }

public String getDesc() {
	return desc;
}

public void setDesc(String desc) {
	this.desc = desc;
}

}
