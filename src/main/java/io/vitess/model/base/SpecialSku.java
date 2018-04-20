package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("t_ma_special_sku")
public class SpecialSku extends SuperEntity {
    private static final long serialVersionUID = -6665876585239104076L;

	@TableField("VERSION")
	private int version;

	@TableField("SHOP_ID")
	private Long shopId;

	@TableField("SKU_CODE")
	private String skuCode;

	@TableField("EXT_CODE")
	private String extCode;

	@TableField("SKU_TYPE")
	private SpecialSkuType skuType;

	@TableField("REMARK")
	private String remark;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("CREATE_USER")
	private String createUser;

}
