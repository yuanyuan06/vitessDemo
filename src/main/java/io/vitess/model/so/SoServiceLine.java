package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.SoServiceType;

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
@TableName("t_td_so_service_line")
public class SoServiceLine extends SuperEntity {

    private static final long serialVersionUID = 6490463075316045036L;

    @TableField("PLATFORM_LINE_ID")
    private String platformLineId;

    @TableField("SERVICE_ID")
    private String serviceId;

    @TableField("QTY")
    private Integer qty;

    @TableField("UNIT_PRICE")
    private BigDecimal unitPrice;

    @TableField("TOTAL_ACTUAL")
    private BigDecimal totalActual;

    @TableField("PAYMENT")
    private BigDecimal payment;

    @TableField("TITLE")
    private String title;

    @TableField("SERVICE_TYPE")
    private SoServiceType serviceType;

    @TableField("SERVICE_TYPE_DESC")
    private String serviceTypeDesc;

    @TableField("TMSER_SPU_CODE")
    private String tmserSpuCode;

    @TableField("PROVIDER_CODE")
    private String providerCode;

    @TableField("PROVIDER_NAME")
    private String providerName;

    @TableField("SHOP_ID")
    private Long shopId;

}
