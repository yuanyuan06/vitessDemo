package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import io.vitess.enums.SpecialSkuType;
import lombok.Data;

import java.util.Date;

/**
 * 特殊商品列表
 * @fanht
 * 
 */
@Data
//@TableName("t_ma_special_sku")
public class SpecialSku extends SuperEntity {
    private static final long serialVersionUID = -6665876585239104076L;

	private int version;

	private Long shopId;

	private String skuCode;

	private String extCode;

	private SpecialSkuType skuType;

	private String remark;

	private Date createTime;

	private String createUser;

}
