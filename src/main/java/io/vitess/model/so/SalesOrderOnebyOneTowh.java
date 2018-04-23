package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 依次过仓未处理数据
 * @author zhiyong.shi
 * 2017年6月8日
 */
@Data
@TableName("t_td_sales_one_by_one_to_wh")
public class SalesOrderOnebyOneTowh extends SuperEntity {


	private static final long serialVersionUID = -4999972147115543016L;

	@TableField("PROCESS_STATUS")
	private Integer processStatus = 0;

	@TableField("PROCESS_RESULT")
	private String processResult;

	@TableField("SHOP_ID")
	private Long shopId;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("SO_ID")
	private Long soId;

	@TableField("WAREHOUSE_CODE")
	private String warehouseCode;

	@TableField("UPDATE_TIME")
	private Date updateTime;
}
