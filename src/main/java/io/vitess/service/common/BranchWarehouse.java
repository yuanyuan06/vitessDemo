package io.vitess.service.common;



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
//@TableName("t_ma_branch_warehouse")
public class BranchWarehouse extends SuperEntity {
    private static final long serialVersionUID = -5447659683465153839L;


    private int version;


    private Long shopId;


    private String whCode;


    private String whName;


    private BranchWarehouseStatus status;


    private BranchWarehouseWmsType wmsType;


    private String platformType;


    private String remark;


    private String createUserNo;


    private Date createTime;


    private String updateUserNo;


    private Date updateTime;

}
