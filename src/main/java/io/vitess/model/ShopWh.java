package io.vitess.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YSH4807
 * @date 2018/4/11 11:19
 */
@Data
public class ShopWh implements Serializable {

    private Long id;
    //columns START
    /** createTime */
    private java.util.Date createTime;
    /** lastModifyTime */
    private java.util.Date lastModifyTime;
    /** version */
    private Integer version;
    /** whCode */
    private String whCode;
    /** whName */
    private String whName;
    /** shopId */
    private Long shopId;
    /** isDefault */
    private Integer isDefault;
    /** 是否平台默认发货仓库, 0:否，1:是 */
    private Integer isPlatformDefault;
    /** 是否默认的退货仓库，0:否，1:是 */
    private Integer isRtnDefault;
    /** deliveryAddressDetail */
    private String deliveryAddressDetail;
    /** consignName */
    private String consignName;
    /** consignPhone */
    private String consignPhone;
    /** province */
    private String province;
    /** city */
    private String city;
    /** area */
    private String area;
    /** town */
    private String town;
    /** 仓储系统标示 */
    private String systemName;
    /** 仓库系统CODE */
    private String systemCode;
    /** 依次过仓优先级 */
    private Integer oneByOneToWhLevel;
    /** 仓库是否支持预包装 */
    private Integer isPrePackage;
    /** 是否校验快递 */
    private Integer isTrackingNo;
    /** 地址 */
    private String address;
    /** 联系人 */
    private String receiver;
    /** 联系电话 */
    private String receiverPhone;
    /** 区 */
    private String district;
    /** 手机 */
    private String mobile;
    /** 邮编 */
    private String zipCode;
    /** 是否调用快递服务 */
    private Integer isInvokeDeliveryService;
    //columns END
}
