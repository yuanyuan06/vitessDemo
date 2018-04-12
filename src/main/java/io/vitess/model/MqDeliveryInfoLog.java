package io.vitess.model;

import io.vitess.constants.Constants;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YSH4807
 * @date 2018/4/11 11:20
 */
@Data
public class MqDeliveryInfoLog implements Serializable {



    private Long id;

    //columns START
    /** version */
    private java.util.Date version;
    /** receiver */
    private String receiver;
    /** contactPerson */
    private String contactPerson;
    /** country */
    private String country;
    /** province */
    private String province;
    /** city */
    private String city;
    /** district */
    private String district;
    /** address */
    private String address;
    /** zipcode */
    private String zipcode;
    /** receiverPhone */
    private String receiverPhone;
    /** receiverMobile */
    private String receiverMobile;
    /** userName */
    private String userName;
    /** membreEmail */
    private String membreEmail;
    /** lpCode */
    private String lpCode;
    /** soLogId */
    private Long soLogId;
    /** givenName */
    private String givenName;
    /** familyName */
    private String familyName;
    /** transServiceType */
    private Integer transServiceType;
    /** transTimeType */
    private Integer transTimeType;
    /** remark */
    private String remark;
    /** town */
    private String town;
    /** 店铺id */
    private Long shopId;

}
