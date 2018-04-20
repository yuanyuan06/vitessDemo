package io.vitess.common;

import java.io.Serializable;

public class ShopWhCommand implements Serializable {

	private static final long serialVersionUID = 5625951640012363898L;

    /** 仓库编码 */
    private String whCode;
    
    /** 是否默认发货仓库 */
    private Boolean isDefault;

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}
