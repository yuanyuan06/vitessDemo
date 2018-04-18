package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 接受外部提供的订单发货地址
 * @author fanht
 *
 */
@TableName("t_td_so_delivery_address")
public class SoDeliveryAddress extends SuperEntity{

    private static final long serialVersionUID = 6490463075316045036L;
    
	/** 是否中文地址 */
	private Boolean isChinese;
	
	/** 平台订单号 */
	@TableField("TID")
	private String tid;
	
    /** 国家 */
    @TableField("COUNTRY")
    private String country;
    
    /** 省 */
    @TableField("PROVINCE")
    private String province;
    
    /** 市 */
    @TableField("CITY")
    private String city;
    
    /** 区 */
    @TableField("DISTRICT")
    private String district;
    
    /** 地址 */
    @TableField("ADDRESS")
    private String address;
    
    /** 邮编 */
    @TableField("ZIPCODE")
    private String zipcode;
    
    /** 联系人 */
    private String contactPerson;
    
    /** 收货人 */
    @TableField("RECEIVER")
    private String receiver;
    
    /** 收货人电话 */
    @TableField("RECEIVER_PHONE")
    private String receiverPhone;
    
    /** 收货人手机 */
    @TableField("RECEIVER_MOBILE")
    private String receiverMobile;
    
    //店铺ID
    @TableField("SHOP_ID")
    private Long shopId;

	//处理状态：0 未处理，1 已处理 -1 跳过处理
    @TableField("PROCESS_STATUS")
    private Integer processStatus = 0;

    @TableField("CREATE_TIME")
    private Date createTime = new Date();

    @TableField("PROCESS_TIME")
    private Date processTime;

    @TableField("ERROR_MESSAGE")
    private String errorMessage;
    
    //不用持久化的
    private Integer currentNodeNo;
    private Integer suspendReasonType;
    private Long soId;

}
