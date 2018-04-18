package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

@Data
@TableName("t_td_order_member")
public class OrderMember extends SuperEntity {

    private static final long serialVersionUID = 2593506430728481494L;
    
    /** 销售订单ID，关联SALES_ORDER_ID */
    @TableField("SALES_ORDER_ID")
    private SalesOrder salesOrder;
    
    /** 平台会员标识 */
    @TableField("PLATFORM_MEMBER_CODE")
    private String platformMemberCode;
    
    /** 真实姓名 */
    @TableField("REAL_NAME")
    private String realName;
    
    /** EMAIL */
    @TableField("EMAIL")
    private String email;
    
    /** 手机号码 */
    @TableField("MOBILE")
    private String mobile;
    
    /**电话 */
    @TableField("PHONE")
    private String phone;
    
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
    
    //店铺ID
    @TableField("SHOP_ID")
    private Long shopId;

}
