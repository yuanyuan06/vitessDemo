package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.constants.Constants;

@TableName("t_td_so_delivery_info")
public class SoDeliveryInfo extends SuperEntity {

    private static final long serialVersionUID = 6490463075316045036L;
    @TableField("CONTACT_PERSON")
    private String contactPerson;

    @TableField("TRANS_EXP_CODE")
    private String transExpCode;

    @TableField("TRANS_NAME")
    private String transName;

    @TableField("TRANS_NUMBER")
    private String transNumber;

    @TableField("ACTUAL_TRANS_EXP_CODE")
    private String actualTransExpCode;

    @TableField("ACTUAL_TRANS_NUMBER")
    private String actualTransNumber;

    @TableField("ACTUAL_TRANS_NAME")
    private String actualTransName;

    @TableField("TRANS_SERVICE_TYPE")
    private Integer transServiceType = Constants.TRANS_SERVICE_TYPE_NORMAL;

    @TableField("COUNTRY_EN")
    private String countryEn;

    @TableField("PROVINCE_EN")
    private String provinceEn;

    @TableField("CITY_EN")
    private String cityEn;

    @TableField("DISTRICT_EN")
    private String districtEn;

    @TableField("ADDRESS_EN")
    private String addressEn;

    @TableField("RECEIVER_EN")
    private String receiverEn;

    @TableField("ZIPCODE_EN")
    private String zipcodeEn;

    @TableField("REMARK_EN")
    private String remarkEn;

    @TableField("STORE_CODE")
    private String storeCode;

    @TableField("STORE_NAME")
    private String storeName;

    @TableField("SHOP_ID")
    private Long shopId;

    @TableField("TRANS_BIG_WORD")
    private String transBigWord;

    @TableField("PACKAGE_CENTER_CODE")
    private String packageCenterCode;
	
}
