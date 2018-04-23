package io.vitess.model.mq;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 店铺绑定的仓库
 * 
 * @author fanht
 * 
 */
@Data
//@TableName("t_ma_shop_wh")
public class ShopWh extends SuperEntity {
    private static final long serialVersionUID = -3096402703545912438L;

    /**
     *仓库编码
     */
    private String whCode;

    /**
     * 仓库名称
     */
    private String whName;
    
    /**
     * 关联店铺
     */
    private CompanyShop shop;

    /**
     * 是否默认发货仓库
     */
    private Boolean isDefault = false;
    
    /**
     * 是否平台默认发货仓库
     */
    private Boolean isPlatformDefault = false;
    
    /**
     * 是否默认的退货仓库
     */
    private Boolean isRtnDefault = false;
    
    /**
     * VERSION
     */
    private int version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近修改时间
     */
    private Date lastModifyTime;
    
    /**
     * 
     * @Description:  仓库系统CODE
     * @author zhiyong.shi
     * 2017年3月16日
     */
    private String systemCode;
    /**
     * @author xin.feng
     * 仓库是否支持预包装业务
     */
    private Boolean isPrePackage;
}
