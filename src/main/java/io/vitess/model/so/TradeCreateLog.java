package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import io.vitess.model.base.User;
import io.vitess.enums.SoCreateLogType;
import io.vitess.model.base.CompanyShop;

import java.util.Date;

/**
 * 销售单导入信息
 * 
 * @author wudan
 * 
 */
//@TableName("t_td_create_log")
public class TradeCreateLog extends SuperEntity{
    private static final long serialVersionUID = -1929133150235638750L;

    /**
     * version
     */
    private int version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 店铺
     */
    private CompanyShop shopId;


    /**
     * 类型
     */
    private SoCreateLogType type;

    /**
     * 单据数
     */
    private Long tdQty;

    /**
     * 操作人
     */
    private User creator;


}
