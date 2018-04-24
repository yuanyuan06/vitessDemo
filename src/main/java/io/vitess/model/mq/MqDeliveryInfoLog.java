package io.vitess.model.mq;

import io.vitess.common.SuperEntity;
import io.vitess.constants.Constants;
import lombok.Data;

import java.util.Date;

@Data
//@TableName("t_mq_delivery_info_log")
public class MqDeliveryInfoLog extends SuperEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -7082656645969758992L;


    private Date version;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 数量
     */
    private String contactPerson;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人手机
     */
    private String receiverMobile;

    /**
     * 会员名
     */
    @Deprecated
    private String userName;

    /**
     * 会员邮箱
     */
    @Deprecated
    private String memberEmail;

    /**
     * 物流商编码
     */
    private String lpCode;

    private String givenName;

    private String familyName;
    
    /**
     * 运送方式(快递附加服务)
     * 1:普通
     * 4:空运
     * 6:陆运
     * 7:特惠
     */
    private Integer transServiceType = Constants.TRANS_SERVICE_TYPE_NORMAL;
    
    /**
     * 快递时间限制（快递附加服务）
     * 1：普通
     * 5：当日
     * 6：次日
     */
    private Integer transTimeType = Constants.TRANS_TIME_TYPE_NORMAL;
    
    //店铺ID
    private Long shopId;

    /**
     * 
     */
    private Long soLog;


    // 没啥卵用的字段
    private String remark;

    private String town;


}
