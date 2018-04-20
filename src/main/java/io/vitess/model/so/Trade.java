package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.model.mq.CompanyShop;
import lombok.Data;

import java.util.Date;

/**
 * Trade
 * @author 李光辉
 * @date 2014年7月1日 下午3:32:28
 * @since
 */
@Data
@TableName("t_td_trade")
public class Trade extends SuperEntity {
    
    private static final long serialVersionUID = 8748723190492855413L;
    @TableField("PLATFORM_ORDER_CODE")
    private String platformOrderCode;

    @TableField("PLATFORM_ORDER_CREATE_TIME")
    private Date platformOrderCreateTime;

    @TableField("ERP_SHOP_CODE")
    private CompanyShop companyShop;

    @TableField("IS_SPLIT")
    private Integer isSplit;

    @TableField("ORDER_COUNT")
    private Integer orderCount;

    @TableField("ALIPAY_ACCOUNT")
    private String payAccount;

    @TableField("ALIPAY_NO")
    private String payNo;

    @TableField("VERSION")
    private Date version;

    
}
