package io.vitess.model.mq;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 店铺绑定的仓库
 * 
 * @author fanht
 * 
 */
@TableName("t_ma_shop_wh")
public class ShopWh extends SuperEntity {
    private static final long serialVersionUID = -3096402703545912438L;

    /**
     *仓库编码
     */
    @TableField("WH_CODE")
    private String whCode;

    /**
     * 仓库名称
     */
    @TableField("WH_NAME")
    private String whName;
    
    /**
     * 关联店铺
     */
    @TableField("SHOP_ID")
    private CompanyShop shop;

    /**
     * 是否默认发货仓库
     */
    @TableField("IS_DEFAULT")
    private Boolean isDefault = false;
    
    /**
     * 是否平台默认发货仓库
     */
    @TableField("IS_PLATFORM_DEFAULT")
    private Boolean isPlatformDefault = false;
    
    /**
     * 是否默认的退货仓库
     */
    @TableField("IS_RTN_DEFAULT")
    private Boolean isRtnDefault = false;
    
    /**
     * VERSION
     */
    @Version
    private int version;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 最近修改时间
     */
    @TableField("LAST_MODIFY_TIME")
    private Date lastModifyTime;
    
    /**
     * 
     * @Description:  仓库系统CODE
     * @author zhiyong.shi
     * 2017年3月16日
     */
    @TableField("SYSTEM_CODE")
    private String systemCode;
    /**
     * @author xin.feng
     * 仓库是否支持预包装业务
     */
    @TableField("IS_PRE_PACKAGE")
    private Boolean isPrePackage;
}
