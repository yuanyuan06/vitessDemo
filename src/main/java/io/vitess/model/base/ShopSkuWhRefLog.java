package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.model.mq.CompanyShop;
import lombok.Data;

import java.util.Date;

/**
 * 店铺-商品-仓库编码关系维护-log日志
 * 
 * @className com.jumbo.model.baseinfo.ShopSkuWhRefLog
 * @author hailiang.jiang
 * @date 2014年10月11日 下午3:20:14
 */
@Data
@TableName("t_ma_shop_sku_wh_ref_log")
public class ShopSkuWhRefLog extends SuperEntity {
    private static final long serialVersionUID = -6665876585239104076L;

	@TableField("SHOP_SKU_WH_ID")
	private ShopSkuWhRef shopSkuWhRef;

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

}
