package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

/**
 * 销售出库时，商品类型为N合一时，出库SN对应的子SN
 * @author fanht
 *
 */
@TableName("t_td_so_line_sn_child")
public class SoLineSnChild extends SuperEntity {
	
	private static final long serialVersionUID = 1829711040114822775L;
	@TableField("SO_ID")
	private Long soId;

	@TableField("SO_LINE_ID")
	private Long soLineId;

	@TableField("SN")
	private String sn;

	@TableField("PARENT_SN")
	private String parentSn;

	@TableField("SO_LINE_SN_ID")
	private Long soLineSnId;

	@TableField("STATUS")
	private Integer status = 0;

	@TableField("SHOP_ID")
	private Long shopId;

}
