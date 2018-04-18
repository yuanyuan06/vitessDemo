package io.vitess.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import io.vitess.common.SuperEntity;

import java.util.Date;

@TableName("t_mq_platform_member_log")
public class MqPlatformMemberLog extends SuperEntity {

    private static final long serialVersionUID = 2978802053164027969L;

    @Version
    private Date version;

    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 会员帐号
     */
    @TableField("LOGIN_NAME")
    private String loginName;

    /**
     * 会员真实姓名
     */
    @TableField("REAL_NAME")
    private String realName;

    /**
     * 性别
     */
    @TableField("GENDER")
    private String gender;

    /**
     * 出生日期
     */
    @TableField("BIRTHDAY")
    private String birthday;

    /**
     * 联系电话
     */
    @TableField("TELEPHONE")
    private String telephone;

    /**
     * 联系手机
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 会员邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 所在国家
     */
    @TableField("COUNTRY")
    private String country;

    /**
     * 所在省
     */
    @TableField("PROVINCE")
    private String province;

    /**
     * 所在市
     */
    @TableField("CITY")
    private String city;
    
    /**
     * 区
     */
    @TableField("DISTRICT")
    private String district;

    /**
     * 邮编
     */
    @TableField("ZIP_CODE")
    private String zipCode;

    /**
     * 详细地址
     */
    @TableField("ADDRESS")
    private String address;

    /**
     * vip会员卡号
     */
    @TableField("VIP_CODE")
    private String vipCode;
    
    //店铺ID
    @TableField("SHOP_ID")
    private Long shopId;

}
