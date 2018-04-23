package io.vitess.model.base;

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
//@TableName("t_ma_shop_sku_wh_ref_log")
public class ShopSkuWhRefLog extends SuperEntity {
    private static final long serialVersionUID = -6665876585239104076L;

	private ShopSkuWhRef shopSkuWhRef;

	private CompanyShop shop;

	private String shopName;

	private String extCode;

	private String whCode;

	private String whName;

	private Date effectiveTimeBegin;

	private Date effectiveTimeEnd;

	private String activeOff;

	private String remark;

	private Date createTime;

	private String createUserNo;

}
