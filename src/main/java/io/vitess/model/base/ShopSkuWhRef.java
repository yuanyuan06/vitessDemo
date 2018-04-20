package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.model.mq.CompanyShop;
import lombok.Data;

import java.util.Date;

/**
 * 店铺-商品-仓库编码关系维护
 * 
 * @className com.jumbo.model.baseinfo.ShopSkuWhRef
 * @author hailiang.jiang
 * @date 2014年10月9日 下午8:17:20
 */
@Data
@TableName("t_ma_shop_sku_wh_ref")
public class ShopSkuWhRef extends SuperEntity {
    private static final long serialVersionUID = -6665876585239104076L;

	@TableField("VERSION")
	private int version;

	@TableField("SHOP_ID")
	private CompanyShop shop;

	@TableField("SHOP_NAME")
	private String shopName;

	@TableField("EXT_CODE")
	private String extCode;

	@TableField("WH_CODE")
	private String whCode;

	@TableField("WH_NAME")
	private String whName;

	@TableField("EFFECTIVE_TIME_BEGIN")
	private Date effectiveTimeBegin;

	@TableField("EFFECTIVE_TIME_END")
	private Date effectiveTimeEnd;

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
