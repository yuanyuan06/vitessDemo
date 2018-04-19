package io.vitess.command;

import io.vitess.model.so.SoLineSn;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kuan.liu
 * @date 2015年7月1日
 */

public class SoLineSnCommand extends SoLineSn {

    private static final long serialVersionUID = 3010636330721360226L;
    
    /**
     * Sn状态
     */
    private String statusStr;
    /**
     * 宝尊订单号
     */
    private String erpOrderCode;
    /**
     * 子单号
     */
    private String platformOrderCodeN;
    /**
     * 平台订单号
     */
    private String platformOrderCode;
    /**
     * 商品编码
     */
    private String skuCode;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 购买数量
     */
    private Integer quantity;
    
    /**
     * 查询参数
     * @return
     */
    public Map<String, Object> getParamMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.hasText(this.getPlatformOrderCode())) {
            map.put("platformOrderCode", this.getPlatformOrderCode());
        }
        if (StringUtils.hasText(this.getPlatformOrderCodeN())) {
            map.put("platformOrderCodeN", this.getPlatformOrderCodeN());
        }
        if (this.getSoLineId() != null) {
            map.put("solineId", this.getSoLineId());
        }
              
        return map;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getErpOrderCode() {
        return erpOrderCode;
    }

    public void setErpOrderCode(String erpOrderCode) {
        this.erpOrderCode = erpOrderCode;
    }

    public String getPlatformOrderCodeN() {
        return platformOrderCodeN;
    }

    public void setPlatformOrderCodeN(String platformOrderCodeN) {
        this.platformOrderCodeN = platformOrderCodeN;
    }

    public String getPlatformOrderCode() {
        return platformOrderCode;
    }

    public void setPlatformOrderCode(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
