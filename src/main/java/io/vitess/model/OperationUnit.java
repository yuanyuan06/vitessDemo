package io.vitess.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YSH4807
 * @date 2018/4/11 11:17
 */
@Data
public class OperationUnit implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 组织编码
     */
    private String code;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 组织全称
     */
    private String fullName;

    /**
     * 组织是否可用
     */
    private Boolean isAvailable = true;

    /**
     * VERSION
     */
    private int version;

    /**
     * 组织类型
     */
    private OperationUnitType ouType;

    /**
     * 父组织
     */
    private OperationUnit parentUnit;

    /**
     * 子组织列表
     */
    private List<OperationUnit> childrenUnits = new ArrayList<OperationUnit>();

    /**
     * 备注
     */
    private String comment;
}
