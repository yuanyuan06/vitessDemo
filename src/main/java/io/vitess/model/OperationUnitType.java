package io.vitess.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YSH4807
 * @date 2018/4/11 11:18
 */
@Data
public class OperationUnitType implements Serializable {

    /**
     * 集团类型
     */
    public static final String OUTYPE_ROOT = "Root";

    /**
     * 公司类型
     */
    public static final String OUTYPE_COMPANY = "Company";

    /**
     * 运营中心类型
     */
    public static final String OUTYPE_OPERATION_CENTER = "OpreationUnit";

    /**
     * 仓库类型
     */
    public static final String OUTYPE_WAREHOUSE = "Warehouse";

    /**
     * 销售事业部
     */
    public static final String OUTYPE_DIVISION = "Division";


    /**
     * 店铺
     */
    public static final String OUTYPE_COMPANY_SHOP = "CompanyShop";

    /**
     * 经销商
     */
    public static final String OUTYPE_SUPPLIER = "Supplier";

    /**
     *
     */
    private static final long serialVersionUID = 6613686886535649399L;

    /**
     * ID
     */
    private Long id;

    /**
     * 组织类型简称/编码
     */
    private String name;

    /**
     * 组织类型全称
     */
    private String displayName;

    /**
     * 是否可用
     */
    private Boolean isAvailable = true;

    /**
     * 组织类型描述
     */
    private String description;

    /**
     * 当前组织类型下已有组织列表
     */
    private List<OperationUnit> ous = new ArrayList<OperationUnit>();

    /**
     * 组织类型，例如：店铺、分公司、经销商等
     */
    private OperationUnitType parentUnitType;
}
