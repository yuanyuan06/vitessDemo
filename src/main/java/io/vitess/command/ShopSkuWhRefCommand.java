package io.vitess.command;

import io.vitess.common.DateUtil;
import io.vitess.model.base.ShopSkuWhRef;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class ShopSkuWhRefCommand extends ShopSkuWhRef {

	private static final long serialVersionUID = -2449752358668144935L;
	
	/** 是否应用的是店铺默认的仓库 **/
	private boolean isApplyShopDefaultWh = false;
	
	public boolean isApplyShopDefaultWh() {
		return isApplyShopDefaultWh;
	}

	public void setApplyShopDefaultWh(boolean isApplyShopDefaultWh) {
		this.isApplyShopDefaultWh = isApplyShopDefaultWh;
	}
	
	private String showResult;
    private String importDescribe;
    private String effectiveTimeBeginStr;
    private String effectiveTimeEndStr;
    private String role;
	
	
    
    /**
     * 查询参数
     * @return
     */
    public Map<String, Object> getParamMap() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.hasText(this.getExtCode())) {
            paramMap.put("extCode", this.getExtCode());
        }
        if (StringUtils.hasText(this.getEffectiveTimeBeginStr())) {
            paramMap.put("createTimeBegin", DateUtil.parse(this.getEffectiveTimeBeginStr()));
        }
        if (StringUtils.hasText(this.getEffectiveTimeEndStr())) {
            paramMap.put("createTimeEnd", DateUtil.parse(this.getEffectiveTimeEndStr()));
        }
        if (StringUtils.hasText(this.getCreateUserNo())) {
            paramMap.put("createUserNo", this.getCreateUserNo());
        }
        if (StringUtils.hasText(this.getWhCode())) {
            paramMap.put("whCode", this.getWhCode());
        }
        
        return paramMap;
    }
    
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getShowResult() {
		return showResult;
	}

	public void setShowResult(String showResult) {
		this.showResult = showResult;
	}

	public String getImportDescribe() {
		return importDescribe;
	}

	public void setImportDescribe(String importDescribe) {
		this.importDescribe = importDescribe;
	}

	public String getEffectiveTimeBeginStr() {
		return effectiveTimeBeginStr;
	}

	public void setEffectiveTimeBeginStr(String effectiveTimeBeginStr) {
		this.effectiveTimeBeginStr = effectiveTimeBeginStr;
	}

	public String getEffectiveTimeEndStr() {
		return effectiveTimeEndStr;
	}

	public void setEffectiveTimeEndStr(String effectiveTimeEndStr) {
		this.effectiveTimeEndStr = effectiveTimeEndStr;
	}
    
	

}
