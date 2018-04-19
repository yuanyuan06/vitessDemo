package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 销售出库时，出库SN
 * @author fanht
 *
 */

@Data
@TableName("t_td_so_line_sn")
public class SoLineSn extends SuperEntity {
	private static final long serialVersionUID = -8937396455362773350L;

	@TableField("SO_ID")
	private Long soId;

	@TableField("SO_LINE_ID")
	private Long soLineId;

	@TableField("SN")
	private String sn;

	@TableField("STATUS")
	private Integer status = 0;

	@TableField("SHOP_ID")
	private Long shopId;

	@TableField("IS_HAVE_SUB")
	private Integer isHaveSub = 0;


}
