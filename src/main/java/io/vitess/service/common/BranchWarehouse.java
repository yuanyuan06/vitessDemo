package io.vitess.service.common;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.BranchWarehouseStatus;
import io.vitess.enums.BranchWarehouseWmsType;
import lombok.Data;

import java.util.Date;

/**
 * 基础数据-O2O分仓编码维护
 * 
 * @className com.jumbo.model.baseinfo.BranchWarehouse
 * @author hailiang.jiang
 * @date 2014年10月21日 下午2:03:15
 */
@Data
@TableName("t_ma_branch_warehouse")
public class BranchWarehouse extends SuperEntity {
    private static final long serialVersionUID = -5447659683465153839L;

    @TableField("VERSION")
    private int version;

    @TableField("shop_id")
    private Long shopId;

    @TableField("wh_code")
    private String whCode;

    @TableField("wh_name")
    private String whName;

    @TableField("status")
    private BranchWarehouseStatus status;

    @TableField("WMS_TYPE")
    private BranchWarehouseWmsType wmsType;

    @TableField("PLATFORM_TYPE")
    private String platformType;

    @TableField("REMARK")
    private String remark;

    @TableField("CREATE_USER_NO")
    private String createUserNo;

    @TableField("create_time")
    private Date createTime;

    @TableField("UPDATE_USER_NO")
    private String updateUserNo;

    @TableField("UPDATE_TIME")
    private Date updateTime;

}
