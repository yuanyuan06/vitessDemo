package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 物流配送区域模板明细
 * 
 * @description: TransReginTemplateDetail
 * @author: jianghailiang
 * @date: 2013年10月25日
 */
@Data
@TableName("t_ma_trans_regin_tpl_dt")
public class TransReginTemplateDetail extends SuperEntity {
	private static final long serialVersionUID = -6223056164881171028L;

	@TableField("VERSION")
	private int version;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("PROVINCE")
	private String province;

	@TableField("CITY")
	private String city;

	@TableField("DISTRICT")
	private String district;

	@TableField("REACHABLE_AREA")
	private String reachableArea;

	@TableField("UN_REACHABLE_AREA")
	private String unReachableArea;

	@TableField("TRAN_REGIN_TEMP_ID")
	private TransReginTemplate transReginTemplate;
}
