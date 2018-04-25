package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import lombok.Data;

@Data
//@TableName("t_td_order_member")
public class OrderMember extends SuperEntity {

    private static final long serialVersionUID = 2593506430728481494L;
    
    /** 销售订单ID，关联SALES_ORDER_ID */
    private Long salesOrder;
    
    /** 平台会员标识 */
    private String platformMemberCode;
    
    /** 真实姓名 */
    private String realName;
    
    /** EMAIL */
    private String email;
    
    /** 手机号码 */
    private String mobile;
    
    /**电话 */
    private String phone;
    
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
    
    //店铺ID
    private Long shopId;

}
