package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import io.vitess.model.base.CompanyShop;
import lombok.Data;

import java.util.Date;

/**
 * Trade
 * @author 李光辉
 * @date 2014年7月1日 下午3:32:28
 * @since
 */
@Data
//@TableName("t_td_trade")
public class Trade extends SuperEntity {
    
    private static final long serialVersionUID = 8748723190492855413L;
    private String platformOrderCode;

    private Date platformOrderCreateTime;

    private CompanyShop companyShop;

    private Integer isSplit;

    private Integer orderCount;

    private String payAccount;

    private String payNo;

    private Date version;

    
}
