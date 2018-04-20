package io.vitess.command;

import io.vitess.model.base.TransReginTemplate;

import java.util.List;


public class TransReginTemplateCommand extends TransReginTemplate {

    /**
     * 
     */
    private static final long serialVersionUID = -8112598318701485388L;
    /**
     * 相关物流服务商名称
     */
    private String transportatorName;
    
    /**
     * 店铺ID
     */
    private Long shopId;
    
    /**
     * 店铺名称
     */
    private String shopName;
    
    private Integer transRegionApplyType;
    
    private List<Long> transRegionTemplateList;
    
    /**
     * 相关物流唯一编码
     */
    private String expCode;

    public String getTransportatorName() {
        return transportatorName;
    }

    public void setTransportatorName(String transportatorName) {
        this.transportatorName = transportatorName;
    }

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getTransRegionApplyType() {
		return transRegionApplyType;
	}

	public void setTransRegionApplyType(Integer transRegionApplyType) {
		this.transRegionApplyType = transRegionApplyType;
	}

	public List<Long> getTransRegionTemplateList() {
		return transRegionTemplateList;
	}

	public void setTransRegionTemplateList(List<Long> transRegionTemplateList) {
		this.transRegionTemplateList = transRegionTemplateList;
	}

    public String getExpCode() {
        return expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }

}
