package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.math.BigDecimal;
import java.util.Date;

@TableName("t_ma_vmi_timed_promotion")
public class VmiTimedPromotion extends SuperEntity {

    private static final long serialVersionUID = 4601006153535998415L;

    @TableField("VERSION")
    private int version;

    @TableField("CODE")
    private String code;

    @TableField("PRO_TYPE")
    private String proType;

    @TableField("DESCRIPTION")
    private String description;

    @TableField("PRO_AMOUNT")
    private BigDecimal proAmount;

    @TableField("PRO_AMOUNT2")
    private BigDecimal proAmount2;

    @TableField("STATUS")
    private Integer status;

    @TableField("START_TIME")
    private Date startTime;

    @TableField("END_TIME")
    private Date endTime;

    @TableField("GIFT_QUOTA")
    private Integer giftQuota;
    
}
