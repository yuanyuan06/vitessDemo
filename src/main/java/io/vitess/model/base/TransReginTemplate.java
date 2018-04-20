package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("t_ma_trans_regin_tpl")
public class TransReginTemplate extends SuperEntity{

	private static final long serialVersionUID = 8911572130419045856L;

	@TableField("VERSION")
	private int version;

	@TableField("CODE")
	private String code;

	@TableField("NAME")
	private String name;

	@TableField("REMARK")
	private String remark;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("LAST_MODIFY_TIME")
	private Date lastModifyTime;

	@TableField("LAST_MODIFY_USER")
	private String lastModifyUser;

	@TableField("TRANSPORTATOR_ID")
	private Transportator transportator;

}
