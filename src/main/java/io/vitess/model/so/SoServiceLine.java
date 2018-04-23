package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import io.vitess.enums.SoServiceType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单服务信息
 * 
 * @classname com.jumbo.model.sales.SalesOrderServiceLine
 * @author hailiang.jiang
 * @date 2015年3月4日 下午4:41:17
 * @version: v1.0.0
 * @see
 */
@Data
//@TableName("t_td_so_service_line")
public class SoServiceLine extends SuperEntity {

    private static final long serialVersionUID = 6490463075316045036L;


    private String platformLineId;


    private String serviceId;


    private Integer qty;


    private BigDecimal unitPrice;


    private BigDecimal totalActual;


    private BigDecimal payment;


    private String title;


    private SoServiceType serviceType;


    private String serviceTypeDesc;


    private String tmserSpuCode;


    private String providerCode;


    private String providerName;


    private Long shopId;


    private SalesOrder salesOrder;

}
