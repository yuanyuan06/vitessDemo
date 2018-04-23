package io.vitess.model.base;

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
public class PromotionActivity extends SuperEntity {
    private static final long serialVersionUID = 429275636137710582L;

    private int version;

    private String code;

    private PromotionStatus status;

    private PromotionType type;

    private Boolean isCanRepeate = Boolean.FALSE;

    private ProductScope productScope;

    private String includeLabel;

    private String excludeLabel;

    private Boolean isExceludeComboSku = Boolean.FALSE;

    private String name;

    private String description;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Long creator;

    private CompanyShop companyShop;

    private Integer productQtyLevel;

    private PromotionApplyModel applyModel;

    private PromotionProductType promotionProductType;
    
}
