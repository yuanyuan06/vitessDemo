package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.model.mq.CompanyShop;
import lombok.Data;

import java.util.Date;

/**
 * 商品分类标签
 * 
 * @author zhouzheng.deng
 * 
 */
@Data
@TableName("t_ma_product_category_tag")
public class ProductCategoryTag extends SuperEntity {
    private static final long serialVersionUID = 5672592635475560898L;

    @TableField("VERSION")
    private int version;

    @TableField("CODE")
    private String code;

    @TableField("NAME")
    private String name;

    @TableField("loxia.dao.support.GenericEnumUserType")
    private CommonStatus status;

    @TableField("DESCRIPTION")
    private String description;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("CREATOR")
    private Long creator;

    @TableField("COMPANY_SHOP_ID")
    private CompanyShop companyShop;

}
