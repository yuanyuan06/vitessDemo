package io.vitess.model.mq;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @author fanht
 * 
 */
//@TableName("t_top_tb_trade")
@Data
public class TbTrade extends SuperEntity{

    private static final long serialVersionUID = -9099901955418076253L;

    private Long tid;
    /**
     * 调用淘宝API: taobao.trade.fullinfo.get 所得到的response body字符串 内容为该Trade对应的JSON字符串
     */
    private String content;
    private Integer status = 0; // call success/failed DEFAULT 0 : NOT_CALL_YET
    private Date lastCallTime;
    private Date loadTime;
    private Date createTime = Calendar.getInstance().getTime();

    // 淘宝交易创建时间。格式:yyyy-MM-dd HH:mm:ss
    private Date tradeCreateTime;


    // 淘宝数据推送的修改时间。格式:yyyy-MM-dd HH:mm:ss
    private Date jdpModified;

    /**
     * taobao.trade.fullinfo.get 调用次数
     */
    private Integer counter = 0;

    private String errorCode;

    /**
     * 主动接口获取到的订单状态
     */
    private String notifyTradeStatus;

    /**
     * 订单明细接口获取到的订单状态
     */
    private String fullInfoStatus;

    /**
     * 分阶段付款的订单状态
     */
    private String stepTradeStatus;

    /**
     * 订单描述，主要针对手工补单和SoldInc补单
     */
    private String description;

    /**
     * 支付时间
     */
    private Date tradePayTime;

    /**
     * 订单类型
     */
    private String tradeType;

    /**
     * 获取订单明细时间
     */
    private Date fullInfoGetTime;
    private Date nextRunTime;
    private Long shopId;
    private Integer syncStatus = 0;

    private Integer syncCounter = 0;

    private Date syncTime;

    private Date confirmTime;

    private String errorMessage;

    private String version = "1.2.6";

    private Long omsShopId;

    private Integer noPayStatus = 0;
    private Date noPayProcessTime;
}
