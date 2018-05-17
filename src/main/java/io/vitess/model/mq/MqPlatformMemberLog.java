package io.vitess.model.mq;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

@Data
//@TableName("t_mq_platform_member_log")
public class MqPlatformMemberLog extends SuperEntity {

    private static final long serialVersionUID = 2978802053164027969L;

    private Date version = new Date();

    private Date createTime;

    /**
     * 会员帐号
     */
    private String loginName;

    /**
     * 会员真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 联系手机
     */
    private String mobile;

    /**
     * 会员邮箱
     */
    private String email;

    /**
     * 所在国家
     */
    private String country;

    /**
     * 所在省
     */
    private String province;

    /**
     * 所在市
     */
    private String city;
    
    /**
     * 区
     */
    private String district;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 详细地址
     */
    private String address;

    /**
     * vip会员卡号
     */
    private String vipCode;
    
    //店铺ID
    private Long shopId;

    // ---------------------------------------- 没什么卵用的字段 ---------------------------------------------------
    private String receiver;

    private String contactPerson;

    private String receiverPhone;

    private String receiverMobile;

    private String userName;

    private String memberEmail;

    private String lpCode;

    private String soLog;

    private String givenName;

    private String familyName;

    private Integer transServiceType;

    private Integer transTimeType;

    private String remark;

    private String town;
}
