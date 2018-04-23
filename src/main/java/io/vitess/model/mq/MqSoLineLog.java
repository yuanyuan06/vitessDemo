package io.vitess.model.mq;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
//@TableName("t_mq_so_line_log")
public class MqSoLineLog extends SuperEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3491854187095045360L;


    private Date version;

    /**
     * 条码(供应商到颜色尺码)
     */
    @Deprecated
    private String barCode;

    private String extentionCode;

    /**
     * 数量
     */
    private Integer qty;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 市场价
     */
    private BigDecimal listPrice;

    /**
     * markdown价格
     */
    private BigDecimal mdPrice;

    /**
     * 行总计 (扣减所有活动优惠且未扣减积分抵扣金额) sum(sl.totalActual)=so.totalActual totalActual + discountFee =
     * unitPrice × qty
     */
    private BigDecimal totalActual;

    /**
     * 行优惠总金额
     */
    private BigDecimal discountFee;

    /**
     * 订单行使用外部积分支付金额
     */
    private BigDecimal outerPointValue;

    /**
     * 订单行使用内部积分支付金额
     */
    private BigDecimal innerPointValue;

    /**
     * 积分外其它虚拟货币支付金额（如预付卡）
     */
    private BigDecimal otherVc;

    /**
     * 平台订单行ID
     */
    private String platformLineId;

    /**
     * 扩展字段1
     */
    private String extProp1;

    private Long soLog;

    /**
     * 平台来源
     */
    private String platformSource;

    /**
     * 平台商品名称
     */
    private String platformSkuName;

    /**
     * 保修时长（月份）
     */
    private Long warrantyMonths;

    /**
     * 平台分仓编码
     */
    private String platformWhCode;

    /**
     * 是否为赠品
     */
    private Boolean isPrezzie;
    
    /** 商家的预计发货时间 */
    private String estConTime;
    
    /**
     * 图片路径连接
     */
    private String picPath;
    
    //天猫国际官网直供子订单关税税费
    private BigDecimal subOrderTaxFee = BigDecimal.ZERO;
    
    //店铺ID
    private Long shopId;
    
    /**
     * 用于记录捆绑oid
     */
    private String assemblyRela;

    /**
     * 包装信息
     */
    private MqSoPackingInfoLog mqSoPackingInfoLog;
    
    /* 平台part_mjz_discount:整单折扣分摊金额*/
    private BigDecimal partMjzDiscount;
    
    /* 平台total_fee:应付金额(商品价格*商品数量+手工调整金额 -子订单级订单优惠金额)*/
    private BigDecimal totalFee;
    /**
     * 全渠道门店编号（若派到的是门店，则此处为门店id；若派到的是电商仓，则此处为电商仓的名字）
     * @return
     */
    private String targetCode;
    /**
     * 派到的是门店(STORE)还是电商仓(WAREHOUSE)。
     * @return
     */
    private String targetType;
    /**
     * 星盘派单号
     * @return 
     */
    private String allocationCode;
    
    //------------- add by chenping 20170629 begin
    /**
     * 花呗分期期数
     */
    private Long fqgNum;
    
    /**
     * 是否商家承担手续费
     * @return
     */
    private Boolean isFqgSFee;
  //------------- add by chenping 20170629 end

}
