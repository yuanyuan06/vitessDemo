package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.WhInvStatusType;
import lombok.Data;

/**
 * 仓储系统库存状态表  值域手动初始化
 * @author fanht
 *
 */
@Data
@TableName("t_ma_wh_inv_status")
public class InventoryStatus extends SuperEntity {
    private static final long serialVersionUID = -8320158502586521463L;

	@TableField("CODE")
	private String code;

	@TableField("NAME")
	private String name;

	@TableField("loxia.dao.support.GenericEnumUserType")
	private WhInvStatusType type;

}
