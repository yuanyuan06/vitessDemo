package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.common.User;
import io.vitess.enums.SoCreateLogType;
import io.vitess.model.mq.CompanyShop;

import java.util.Date;

/**
 * 销售单导入信息
 * 
 * @author wudan
 * 
 */
@TableName("t_td_create_log")
public class TradeCreateLog extends SuperEntity{
    private static final long serialVersionUID = -1929133150235638750L;

    /**
     * version
     */
    @TableField("VERSION")
    private int version;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 店铺
     */
    @TableField("SHOP_ID")
    private CompanyShop shopId;


    /**
     * 类型
     */
    @TableField("SO_CREATE_LOG_TYPE")
    private SoCreateLogType type;

    /**
     * 单据数
     */
    @TableField("TD_QTY")
    private Long tdQty;

    /**
     * 操作人
     */
    @TableField("USER_ID")
    private User creator;


}
