package io.vitess.model.base;

import io.vitess.common.SuperEntity;
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
//@TableName("t_ma_package_sku_num")
public class PackageSkuNum extends SuperEntity {
    private static final long serialVersionUID = -6665876585239104076L;

	private int version;

	private CompanyShop shop;

	private String shopName;

	private String extCode;

	private Integer qty;

	private String activeOff;

	private String remark;

	private Date createTime;

	private String createUserNo;

	private Date updateTime;

	private String updateUserNo;

}
