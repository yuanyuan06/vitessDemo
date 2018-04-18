package io.vitess.model.so;

import com.jumbo.model.BaseModel;
import com.jumbo.model.sales.enums.PackageType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "t_td_sales_order_line_package")
public class SalesOrderLinePackage extends BaseModel {

    private static final long serialVersionUID = -2564254394778763431L;
    
    private Long id;
    
    /** 销售订单ID，关联SALES_ORDER_ID */
    private SalesOrder salesOrder;
    
    /** 销售订单明细ID，关联SALES_ORDER_LINE_ID */
    private SalesOrderLine salesOrderLine;
    
    /** 包装类型：SCM定义的列表中选取 */
    private PackageType packageType;
    
    /** 备注 */
    private String remark;
    
    //店铺ID
    private Long shopId;

    public SalesOrderLinePackage() {}
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALES_ORDER_ID")
    public SalesOrder getSalesOrder() {
        return this.salesOrder;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALES_ORDER_LINE_ID")
    public SalesOrderLine getSalesOrderLine() {
        return this.salesOrderLine;
    }
    
    @Column(name = "PACKAGE_TYPE", columnDefinition = "integer")
    @Type(type = "loxia.dao.support.GenericEnumUserType", parameters = {@Parameter(name = "enumClass", value = "com.jumbo.model.sales.enums.PackageType")})
    public PackageType getPackageType() {
        return this.packageType;
    }

    @Column(name = "REMARK", length = 256)
    public String getRemark() {
        return this.remark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public void setSalesOrderLine(SalesOrderLine salesOrderLine) {
        this.salesOrderLine = salesOrderLine;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}
