package io.vitess.service.common;

import io.vitess.command.SalesOrderCommand;
import io.vitess.service.BaseManager;

public interface PromotionManager extends BaseManager {
    
    /**
     * 促销应用
     * @param soc
     * @return
     */
    public SalesOrderCommand applyPromotionAndAddGiftLine(SalesOrderCommand soc);
    
    /**
     * VMI促销应用
     * @param soc
     * @return
     */
    public SalesOrderCommand applyVmiPromotion(SalesOrderCommand soc);

}
