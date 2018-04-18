/**
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Jumbomart. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jumbo.
 * 
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 * 
 */

package io.vitess.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织
 * 
 * @author benjamin
 * 
 */
@TableName("t_au_operation_unit")
public class OperationUnit extends SuperEntity {

    private static final long serialVersionUID = 2141774961700250964L;

    /**
     * 组织编码
     */
    @TableField("CODE")
    private String code;

    /**
     * 组织名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 组织全称
     */
    @TableField("FULL_NAME")
    private String fullName;

    /**
     * 组织是否可用
     */
    @TableField("IS_AVAILABLE")
    private Boolean isAvailable = true;

    /**
     * VERSION
     */
    @TableField("VERSION")
    private int version;

    /**
     * 组织类型
     */
    @TableField("OU_TYPE_ID")
    private Long ouTypeId;
//    private OperationUnitType ouType;

    /**
     * 父组织
     */
    @TableField("PARENT_OU_ID")
    private OperationUnit parentUnit;

    /**
     * 子组织列表
     */
    @TableField("parentUnit")
    private List<OperationUnit> childrenUnits = new ArrayList<OperationUnit>();

    /**
     * 备注
     */
    @TableField("OU_COMMENT")
    private String comment;

}
