package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 组合商明细
 * 
 * @author zhouzheng.deng
 * 
 */
@Data
@TableName("t_ma_combo_sku_line")
public class ComboSkuDetail extends SuperEntity {
    private static final long serialVersionUID = -1502820921164351802L;

    @TableField("VERSION")
    private int version;

    @TableField("QUANTITY")
    private Integer quantity;

    @TableField("PRICE")
    private BigDecimal unitPrice;

    @TableField("REMARK")
    private String remark;

    @TableField("INV_SKU_ID")
    private Sku invSku;

    @TableField("COMBO_SKU_ID")
    private ComboSku comboSku;

    @TableField("SHOP_ID")
    private Long shopId;
}
