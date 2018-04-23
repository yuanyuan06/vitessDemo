package io.vitess.model.so;



import io.vitess.common.SuperEntity;

/**
 * 销售出库时，商品类型为N合一时，出库SN对应的子SN
 * @author fanht
 *
 */
//@TableName("t_td_so_line_sn_child")
public class SoLineSnChild extends SuperEntity {
	
	private static final long serialVersionUID = 1829711040114822775L;

	private Long soId;


	private Long soLineId;


	private String sn;


	private String parentSn;


	private Long soLineSnId;


	private Integer status = 0;


	private Long shopId;

}
