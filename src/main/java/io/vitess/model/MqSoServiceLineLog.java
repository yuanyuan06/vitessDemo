package io.vitess.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author YSH4807
 * @date 2018/4/11 11:22
 */
@Data
public class MqSoServiceLineLog implements Serializable {

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
