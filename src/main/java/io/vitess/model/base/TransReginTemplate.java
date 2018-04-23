package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 物流配送区域模板
 * 
 * @description: TransReginTemplate
 * @author: jianghailiang
 * @date: 2013年10月25日
 */
@Data
//@TableName("t_ma_trans_regin_tpl")
public class TransReginTemplate extends SuperEntity{

	private static final long serialVersionUID = 8911572130419045856L;

	private int version;

	private String code;

	private String name;

	private String remark;

	private Date createTime;

	private Date lastModifyTime;

	private String lastModifyUser;

	private Transportator transportator;

}
