package io.vitess.model.so;



import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 销售出库时，出库SN
 * @author fanht
 *
 */

@Data
//@TableName("t_td_so_line_sn")
public class SoLineSn extends SuperEntity {
	private static final long serialVersionUID = -8937396455362773350L;


	private Long soId;


	private Long soLineId;


	private String sn;


	private Integer status = 0;


	private Long shopId;


	private Integer isHaveSub = 0;


}
