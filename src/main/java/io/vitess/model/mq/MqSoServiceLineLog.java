package io.vitess.model.mq;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 服务行信息
 * @author fanht
 *
 */
@Data
//@TableName("t_mq_so_service_line_log")
public class MqSoServiceLineLog extends SuperEntity {

    private static final long serialVersionUID = -3491854187095045360L;

    /**
     * PK
     */
    private Long id;
    
    /**
     * 虚拟服务子订单订单号
     */
    private String platformLineId;
    
    /**
     * 服务数字id
     */
    private String serviceId;
    
    /**
     * 购买数量，取值范围为大于0的整数
     */
    private Integer qty;

    /**
     * 服务价格，精确到小数点后两位：单位:元
     */
    private BigDecimal unitPrice;
    
    /**
     * 服务子订单总费用
     * 行总计unitPrice × qty
     */
    private BigDecimal totalActual;
    
    /**
     * 子订单实付金额
     */
    private BigDecimal payment;
    
    /**
     * 商品名称
     */
    private String title;
    
    /**
     * 服务类型：干支：送货上门；干线：自提；安装：上门安装；
     */
    private String tmserSpuCode;
    
    //店铺ID
    private Long shopId;

    private Long soLog;

}
