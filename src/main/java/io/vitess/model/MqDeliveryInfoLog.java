package io.vitess.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.constants.Constants;

import java.util.Date;

@TableName("t_mq_delivery_info_log")
public class MqDeliveryInfoLog extends SuperEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -7082656645969758992L;


    @TableField("VERSION")
    private Date version;

    /**
     * 收货人
     */
    @TableField("RECEIVER")
    private String receiver;

    /**
     * 数量
     */
    @TableField("CONTACT_PERSON")
    private String contactPerson;

    /**
     * 国家
     */
    @TableField("COUNTRY")
    private String country;

    /**
     * 省
     */
    @TableField("PROVINCE")
    private String province;

    /**
     * 市
     */
    @TableField("CITY")
    private String city;

    /**
     * 区
     */
    @TableField("DISTRICT")
    private String district;

    /**
     * 地址
     */
    @TableField("ADDRESS")
    private String address;

    /**
     * 邮编
     */
    @TableField("ZIPCODE")
    private String zipCode;

    /**
     * 收货人电话
     */
    @TableField("RECEIVER_PHONE")
    private String receiverPhone;

    /**
     * 收货人手机
     */
    @TableField("RECEIVER_MOBILE")
    private String receiverMobile;

    /**
     * 会员名
     */
    @Deprecated
    @TableField("USER_NAME")
    private String userName;

    /**
     * 会员邮箱
     */
    @Deprecated
    @TableField("MEMBRE_EMAIL")
    private String memberEmail;

    /**
     * 物流商编码
     */
    @TableField("LP_CODE")
    private String lpCode;

    @TableField("given_name")
    private String givenName;

    @TableField("family_name")
    private String familyName;
    
    /**
     * 运送方式(快递附加服务)
     * 1:普通
     * 4:空运
     * 6:陆运
     * 7:特惠
     */
    @TableField("TRANS_SERVICE_TYPE")
    private Integer transServiceType = Constants.TRANS_SERVICE_TYPE_NORMAL;
    
    /**
     * 快递时间限制（快递附加服务）
     * 1：普通
     * 5：当日
     * 6：次日
     */
    @TableField("TRANS_TIME_TYPE")
    private Integer transTimeType = Constants.TRANS_TIME_TYPE_NORMAL;
    
    //店铺ID
    @TableField("SHOP_ID")
    private Long shopId;

    /**
     * 
     */
    @TableField("SO_LOG_ID")
    private Long soLog;

}
