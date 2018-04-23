package io.vitess.model.base;

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
//@TableName("t_ma_trans_regin_tpl_dt")
public class TransReginTemplateDetail extends SuperEntity {
	private static final long serialVersionUID = -6223056164881171028L;

	private int version;

	private Date createTime;

	private String province;

	private String city;

	private String district;

	private String reachableArea;

	private String unReachableArea;

	private TransReginTemplate transReginTemplate;
}
