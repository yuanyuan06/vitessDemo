package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 依次过仓未处理数据
 * @author zhiyong.shi
 * 2017年6月8日
 */
@Data
//@TableName("t_td_sales_one_by_one_to_wh")
public class SalesOrderOnebyOneTowh extends SuperEntity {


	private static final long serialVersionUID = -4999972147115543016L;

	private Integer processStatus = 0;

	private String processResult;

	private Long shopId;

	private Date createTime;

	private Long soId;

	private String warehouseCode;

	private Date updateTime;
}
