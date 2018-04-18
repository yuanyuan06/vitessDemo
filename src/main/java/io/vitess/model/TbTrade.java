package io.vitess.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @author fanht
 * 
 */
@Data
@TableName("t_top_tb_trade")
public class TbTrade extends SuperEntity{

    private static final long serialVersionUID = -9099901955418076253L;

    @TableField("ID")
    private Long id;
    @TableField("TID")
    private Long tid;
    /**
     * 调用淘宝API: taobao.trade.fullinfo.get 所得到的response body字符串 内容为该Trade对应的JSON字符串
     */
    @TableField("CONTENT")
    private String content;
    @TableField("STATUS")
    private Integer status = 0; // call success/failed DEFAULT 0 : NOT_CALL_YET
    @TableField("LAST_CALL_TIME")
    private Date lastCallTime;
    @TableField("LOAD_TIME")
    private Date loadTime;
    @TableField("CREATE_TIME")
    private Date createTime = Calendar.getInstance().getTime();

    // 淘宝交易创建时间。格式:yyyy-MM-dd HH:mm:ss
    @TableField("TRADE_CREATE_TIME")
    private Date tradeCreateTime;


    // 淘宝数据推送的修改时间。格式:yyyy-MM-dd HH:mm:ss
    @TableField("JDP_MODIFIED")
    private Date jdpModified;

    /**
     * taobao.trade.fullinfo.get 调用次数
     */
    @TableField("COUNTER")
    private Integer counter = 0;

    @TableField("ERROR_CODE")
    private String errorCode;

    /**
     * 主动接口获取到的订单状态
     */
    @TableField("NOTIFY_TRADE_STATUS")
    private String notifyTradeStatus;

    /**
     * 订单明细接口获取到的订单状态
     */
    @TableField("FULLINFO_STATUS")
    private String fullInfoStatus;

    /**
     * 分阶段付款的订单状态
     */
    @TableField("STEP_TRADE_STATUS")
    private String stepTradeStatus;

    /**
     * 订单描述，主要针对手工补单和SoldInc补单
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 支付时间
     */
    @TableField("TRADE_PAY_TIME")
    private Date tradePayTime;

    /**
     * 订单类型
     */
    @TableField("TRADE_TYPE")
    private String tradeType;

    /**
     * 获取订单明细时间
     */
    @TableField("FULLINFO_GET_TIME")
    private Date fullInfoGetTime;
    @TableField("NEXT_RUN_TIME")
    private Date nextRunTime;
    @TableField("SHOP_ID")
    private Long shopId;
    @TableField("SYNC_STATUS")
    private Integer syncStatus = 0;

    @TableField("SYNC_COUNTER")
    private Integer syncCounter = 0;

    @TableField("SYNC_TIME")
    private Date syncTime;

    @TableField("CONFIRM_TIME")
    private Date confirmTime;

    @TableField("ERROR_MESSAGE")
    private String errorMessage;

    @TableField("VERSION")
    private String version = "1.2.6";

    @TableField("OMS_SHOP_ID")
    private Long omsShopId;

    @TableField("NO_PAY_STATUS")
    private Integer noPayStatus = 0;
    @TableField("NO_PAY_PROCESS_TIME")
    private Date noPayProcessTime;

}
