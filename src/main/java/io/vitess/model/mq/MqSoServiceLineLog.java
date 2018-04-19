package io.vitess.model.mq;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 服务行信息
 * @author fanht
 *
 */
@Data
@TableName("t_mq_so_service_line_log")
public class MqSoServiceLineLog extends SuperEntity {

    private static final long serialVersionUID = -3491854187095045360L;

    /**
     * PK
     */
    @TableField("ID")
    private Long id;
    
    /**
     * 虚拟服务子订单订单号
     */
    @TableField("PLATFORM_LINE_ID")
    private String platformLineId;
    
    /**
     * 服务数字id
     */
    @TableField("SERVICE_ID")
    private String serviceId;
    
    /**
     * 购买数量，取值范围为大于0的整数
     */
    @TableField("QTY")
    private Integer qty;

    /**
     * 服务价格，精确到小数点后两位：单位:元
     */
    @TableField("UNIT_PRICE")
    private BigDecimal unitPrice;
    
    /**
     * 服务子订单总费用
     * 行总计unitPrice × qty
     */
    @TableField("TOTAL_ACTUAL")
    private BigDecimal totalActual;
    
    /**
     * 子订单实付金额
     */
    @TableField("PAYMENT")
    private BigDecimal payment;
    
    /**
     * 商品名称
     */
    @TableField("TITLE")
    private String title;
    
    /**
     * 服务类型：干支：送货上门；干线：自提；安装：上门安装；
     */
    @TableField("TMSER_SPU_CODE")
    private String tmserSpuCode;
    
    //店铺ID
    @TableField("SHOP_ID")
    private Long shopId;

    @TableField("SO_LOG_ID")
    private Long soLog;

}
