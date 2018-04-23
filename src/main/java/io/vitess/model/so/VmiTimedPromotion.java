package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import io.vitess.model.base.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
//@TableName("t_ma_vmi_timed_promotion")
public class VmiTimedPromotion extends SuperEntity {

    private static final long serialVersionUID = 4601006153535998415L;

    private int version;

    private String code;

    private String proType;

    private String description;

    private BigDecimal proAmount;

    private BigDecimal proAmount2;

    private Integer status;

    private Date startTime;

    private Date endTime;

    private Integer giftQuota;

    private Product product;

    private Product masterProduct;
    
}
