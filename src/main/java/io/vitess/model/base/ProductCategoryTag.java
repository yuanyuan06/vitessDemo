package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import io.vitess.enums.CommonStatus;
import lombok.Data;

import java.util.Date;

/**
 * 商品分类标签
 * 
 * @author zhouzheng.deng
 * 
 */
@Data
//@TableName("t_ma_product_category_tag")
public class ProductCategoryTag extends SuperEntity {
    private static final long serialVersionUID = 5672592635475560898L;

    private int version;

    private String code;

    private String name;

    private CommonStatus status;

    private String description;

    private Date createTime;

    private Long creator;

    private CompanyShop companyShop;

}
