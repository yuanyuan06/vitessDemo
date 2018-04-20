package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.*;
import io.vitess.model.mq.CompanyShop;
import lombok.Data;

import java.util.Date;

/**
 * 促销活动
 * 
 * @author zhouzheng.deng
 * 
 */
@Data
@TableName("t_ma_promotion_activity")
public class PromotionActivity extends SuperEntity {
    private static final long serialVersionUID = 429275636137710582L;

    @TableField("VERSION")
    private int version;

    @TableField("CODE")
    private String code;

    @TableField("STATUS")
    private PromotionStatus status;

    @TableField("TYPE")
    private PromotionType type;

    @TableField("IS_CAN_REPEATE")
    private Boolean isCanRepeate = Boolean.FALSE;

    @TableField("PRODUCT_SCOPE")
    private ProductScope productScope;

    @TableField("INCLUDE_LABEL")
    private String includeLabel;

    @TableField("EXCLUDE_LABEL")
    private String excludeLabel;

    @TableField("IS_EXCELUDE_COMBO_SKU")
    private Boolean isExceludeComboSku = Boolean.FALSE;

    @TableField("NAME")
    private String name;

    @TableField("DESCRIPTION")
    private String description;

    @TableField("START_TIME")
    private Date startTime;

    @TableField("END_TIME")
    private Date endTime;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("CREATOR")
    private Long creator;

    @TableField("SHOP_ID")
    private CompanyShop companyShop;

    @TableField("PRODUCT_QTY_LEVEL")
    private Integer productQtyLevel;

    @TableField("APPLY_MODEL")
    private PromotionApplyModel applyModel;

    @TableField("PROMOTION_PRODUCT_TYPE")
    private PromotionProductType promotionProductType;
    
}
