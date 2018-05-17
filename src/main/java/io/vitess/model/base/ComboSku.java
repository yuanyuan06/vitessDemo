package io.vitess.model.base;

import io.vitess.common.SuperEntity;
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
//@TableName("t_ma_combo_sku")
public class ComboSku extends SuperEntity {
    private static final long serialVersionUID = 6451767353501050808L;

    private Date version = new Date();

    private String code;

    private String name;

    private String description;

    private Integer status;
//    private CommonStatus status;

    private BigDecimal totalPrice;

    private Date createTime;

    private CompanyShop companyShop;

    private Long creator;

    private boolean isPackage;
	
	
}
