package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_td_platform_promotion")
public class PlatformPromotion extends SuperEntity {

    private static final long serialVersionUID = -5822931172364105726L;
    
    private Long id;
    
    /** 销售订单ID，关联SALES_ORDER_ID */
    @TableField("SALES_ORDER_ID")
    private SalesOrder salesOrder;
    
    /** 销售订单明细ID，关联SALES_ORDER_LINE_ID */
    @TableField("SALES_ORDER_LINE_ID")
    private SalesOrderLine salesOrderLine;
    
    /**
     * 平台订单号
     */
    @TableField("PLATFORM_ORDER_CODE")
    private String platformOrderCode;

    /**
     * 平台订单行ID
     */
    @TableField("PLATFORM_LINE_ID")
    private Long platformLineId;
    
    /**
     * 优惠信息的名称
     */
    @TableField("PROMOTION_NAME")
    private String promotionName;
    
    /**
     * 优惠金额（免运费、限时打折时为空）,单位：元
     */
    @TableField("DISCOUNT_FEE")
    private BigDecimal discountFee;
    
    /**
     * 满就送商品时，所送商品的名称
     */
    @TableField("GIFT_ITEM_NAME")
    private String giftItemName;
    
    /**
     * 赠品的宝贝id
     */
    @TableField("GIFT_ITEM_ID")
    private String giftItemId;
    
    /**
     * 满就送礼物的礼物数量
     */
    @TableField("GIFT_ITEM_NUM")
    private Integer giftItemNum;
    
    /**
     * 优惠活动的描述
     */
    @TableField("DESCRIPTION")
    private String description;
    
    /**
     * 优惠id，(由营销工具id、优惠活动id和优惠详情id组成，结构为：营销工具id-优惠活动id_优惠详情id，如mjs-123024_211143）
     */
    @TableField("PROMOTION_ID")
    private String promotionId;
    
    /**
     * 促销范围 
     * 1:整单金额促销 2:单行金额促销 3：整单平摊
     */
    @TableField("SCOPE_TYPE")
    private Integer scopeType;
    
    //店铺ID
    @TableField("SHOP_ID")
    private Long shopId;

}
