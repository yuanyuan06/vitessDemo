package io.vitess.model.base;

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
//@TableName("t_ma_combo_sku_line")
public class ComboSkuDetail extends SuperEntity {
    private static final long serialVersionUID = -1502820921164351802L;

    private int version;

    private Integer quantity;

    private BigDecimal unitPrice;

    private String remark;

    private Sku invSku;

    private ComboSku comboSku;

    private Long shopId;
}
