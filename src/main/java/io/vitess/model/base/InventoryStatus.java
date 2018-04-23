package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import io.vitess.enums.WhInvStatusType;
import lombok.Data;

/**
 * 仓储系统库存状态表  值域手动初始化
 * @author fanht
 *
 */
@Data
//@TableName("t_ma_wh_inv_status")
public class InventoryStatus extends SuperEntity {
    private static final long serialVersionUID = -8320158502586521463L;

	private String code;

	private String name;

	private WhInvStatusType type;

}
