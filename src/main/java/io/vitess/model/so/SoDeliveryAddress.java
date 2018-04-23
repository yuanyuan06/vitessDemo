package io.vitess.model.so;

import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 接受外部提供的订单发货地址
 * @author fanht
 *
 */
//@TableName("t_td_so_delivery_address")
public class SoDeliveryAddress extends SuperEntity{

    private static final long serialVersionUID = 6490463075316045036L;
    
	/** 是否中文地址 */
	private Boolean isChinese;
	
	/** 平台订单号 */
	private String tid;
	
    /** 国家 */
    private String country;
    
    /** 省 */
    private String province;
    
    /** 市 */
    private String city;
    
    /** 区 */
    private String district;
    
    /** 地址 */
    private String address;
    
    /** 邮编 */
    private String zipcode;
    
    /** 联系人 */
    private String contactPerson;
    
    /** 收货人 */
    private String receiver;
    
    /** 收货人电话 */
    private String receiverPhone;
    
    /** 收货人手机 */
    private String receiverMobile;
    
    //店铺ID
    private Long shopId;

	//处理状态：0 未处理，1 已处理 -1 跳过处理
    private Integer processStatus = 0;

    private Date createTime = new Date();

    private Date processTime;

    private String errorMessage;
    
    //不用持久化的
    private Integer currentNodeNo;
    private Integer suspendReasonType;
    private Long soId;

}
