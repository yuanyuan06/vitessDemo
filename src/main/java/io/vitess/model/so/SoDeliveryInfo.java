package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import io.vitess.constants.Constants;
import io.vitess.enums.TransTimeType;
import lombok.Data;

@Data
//@TableName("t_td_so_delivery_info")
public class SoDeliveryInfo extends SuperEntity {

    private static final long serialVersionUID = 6490463075316045036L;
    private SalesOrder salesOrder;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String zipcode;

    private String contactPerson;

    private String receiver;

    private String receiverPhone;

    private String receiverMobile;

    private String memberEmail;

    private String transExpCode;

    private String transName;

    private String transNumber;

    private String actualTransExpCode;

    private String actualTransNumber;

    private String actualTransName;

    private Integer transServiceType = Constants.TRANS_SERVICE_TYPE_NORMAL;

    private TransTimeType transTimeType;

    private String remark;

    private String countryEn;

    private String provinceEn;

    private String cityEn;

    private String districtEn;

    private String addressEn;

    private String receiverEn;

    private String zipcodeEn;

    private String remarkEn;

    private String storeCode;

    private String storeName;

    private Long shopId;

    private String transBigWord;

    private String packageCenterCode;
	
}
