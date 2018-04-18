package io.vitess.enums;

import org.springframework.util.StringUtils;

/**
 * 订单服务类型
 * 
 * @classname com.jumbo.model.sales.enums.SoServiceType
 * @author hailiang.jiang
 * @date 2015年3月6日 上午10:20:43
 * @version: v1.0.0
 * @see
 */
public enum SoServiceType {
	CUSTOMERS_FROM_CARRYING(1, "自提"),
	HOME_DELIVERY_SERVICE(2, "送货上门"),
	HOME_INSTALL_SERVICE(3, "上门安装");
	
	private int value;
	private String name;
	
	private SoServiceType(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public String getName() {
        return name;
    }

    public int getValue(){
		return value;
	}
    
    /**
     * serviceType表示查询所有的支持服务类型的服务商。
	 *		家装干线服务     11
	 *		家装干支服务     12
	 *		家装干支装服务   13
	 *		卫浴大件干线     14
	 *		卫浴大件干支     15
	 *		卫浴大件安装     16
	 *		地板干线         17
	 *		地板干支         18
	 *		地板安装         19
	 *		灯具安装         20
	 *		卫浴小件安装     21
	 *		
	 *
	 *  （注：同一个服务商针对不同类型的serviceType是具有不同的tpCode的）
	 *		干支：送货上门
	 *		干线：自提
	 *		安装：上门安装
     * @methodName com.jumbo.model.sales.enums.SoServiceType.getServiceType
     * @author hailiang.jiang
     * @date 2015年3月6日 上午10:25:40
     * @version: v1.0.0
     */
    public static SoServiceType getServiceType(String typeStr) {
    	if (!StringUtils.hasText(typeStr)) {
    		return null;
    	}
    	
    	if (typeStr.indexOf("干支装") != -1) {//家装干支装服务 暂时不支持该服务
    		return null;
    	}
    	
    	if (typeStr.indexOf("干线") != -1) {
			return CUSTOMERS_FROM_CARRYING;
		}
    	
    	if (typeStr.indexOf("干支") != -1) {
    		return HOME_DELIVERY_SERVICE;
    	}
    	
		if ( typeStr.indexOf("安装") != -1) {
			return HOME_INSTALL_SERVICE;
		}
    	
    	return null;
    }

	public static SoServiceType valueOf(int value){
		switch (value) {
		case 1:
			return CUSTOMERS_FROM_CARRYING;
		case 2:
			return HOME_DELIVERY_SERVICE;
		case 3:
			return HOME_INSTALL_SERVICE;
		default:
			return null;
		}
	}
}
