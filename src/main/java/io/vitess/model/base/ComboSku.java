package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.model.mq.CompanyShop;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组合商品
 * 
 * @author zhouzheng.deng
 * 
 */
@Data
@TableName("t_ma_combo_sku")
public class ComboSku extends SuperEntity {
    private static final long serialVersionUID = 6451767353501050808L;

    @TableField("VERSION")
    private Date version;

    @TableField("CODE")
    private String code;

    @TableField("NAME")
    private String name;

    @TableField("DESCRIPTION")
    private String description;

    @TableField("STATUS")
    private Integer status;
//    private CommonStatus status;

    @TableField("TOTAL_PRICE")
    private BigDecimal totalPrice;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("SHOP_ID")
    private CompanyShop companyShop;

    @TableField("CREATOR")
    private Long creator;

    @TableField("IS_PACKAGE")
    private boolean isPackage;
	
	
}
