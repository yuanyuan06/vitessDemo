package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.model.mq.CompanyShop;
import lombok.Data;

import java.util.Date;

/**
 * 包裹商品数量（每个包裹商品的最大数量）
 * 
 * @className com.jumbo.model.baseinfo.PackageSkuNum
 * @author hailiang.jiang
 * @date 2014年12月3日 下午3:27:47
 */
@Data
@TableName("t_ma_package_sku_num")
public class PackageSkuNum extends SuperEntity {
    private static final long serialVersionUID = -6665876585239104076L;

	@TableField("VERSION")
	private int version;

	@TableField("SHOP_ID")
	private CompanyShop shop;

	@TableField("SHOP_NAME")
	private String shopName;

	@TableField("EXT_CODE")
	private String extCode;

	@TableField("QTY")
	private Integer qty;

	@TableField("ACTIVE_OFF")
	private String activeOff;

	@TableField("REMARK")
	private String remark;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("CREATE_USER_NO")
	private String createUserNo;

	@TableField("UPDATE_TIME")
	private Date updateTime;

	@TableField("UPDATE_USER_NO")
	private String updateUserNo;

}
