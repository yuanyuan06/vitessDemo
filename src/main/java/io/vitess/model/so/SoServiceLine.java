package io.vitess.model.so;

import com.jumbo.model.BaseModel;
import com.jumbo.model.sales.enums.SoServiceType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单服务信息
 * 
 * @classname com.jumbo.model.sales.SalesOrderServiceLine
 * @author hailiang.jiang
 * @date 2015年3月4日 下午4:41:17
 * @version: v1.0.0
 * @see
 */
@Entity
@Table(name = "t_td_so_service_line")
public class SoServiceLine extends BaseModel {

    private static final long serialVersionUID = 6490463075316045036L;
    
    /**
     * PK
     */
    private Long id;
    
    /** 销售订单ID，关联SALES_ORDER_ID */
    private SalesOrder salesOrder;
    
    /**
     * 虚拟服务子订单订单号
     */
    private String platformLineId;
    
    /**
     * 服务数字id
     */
    private String serviceId;
    
    /**
     * 购买数量，取值范围为大于0的整数
     */
    private Integer qty;

    /**
     * 服务价格，精确到小数点后两位：单位:元
     */
    private BigDecimal unitPrice;
    
    /**
     * 服务子订单总费用
     * 行总计unitPrice × qty
     */
    private BigDecimal totalActual;
    
    /**
     * 子订单实付金额
     */
    private BigDecimal payment;
    
    /**
     * 商品名称
     */
    private String title;
    
    /** 根据tmserSpuCode转成-->服务类型 **/
    private SoServiceType serviceType;
    
    /** SoServiceType类型对应的中文描述 **/
    private String serviceTypeDesc;
    
    /**
     * 服务类型：干支：送货上门；干线：自提；安装：上门安装；
     */
    private String tmserSpuCode;
    
    /** 服务供应商代码  **/
    private String providerCode;
    
    /** 服务供应商名称 **/
    private String providerName;
    
    //店铺ID
    private Long shopId;
    
    public SoServiceLine() {}
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALES_ORDER_ID")
    public SalesOrder getSalesOrder() {
        return this.salesOrder;
    }
    
    public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}
    
    @Column(name = "QTY")
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Column(name = "UNIT_PRICE")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Column(name = "TOTAL_ACTUAL")
    public BigDecimal getTotalActual() {
        return totalActual;
    }

    public void setTotalActual(BigDecimal totalActual) {
        this.totalActual = totalActual;
    }

    @Column(name = "PLATFORM_LINE_ID",length = 50)
    public String getPlatformLineId() {
        return platformLineId;
    }

    public void setPlatformLineId(String platformLineId) {
        this.platformLineId = platformLineId;
    }

    @Column(name = "SERVICE_ID",length = 50)
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name = "PAYMENT")
	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	@Column(name = "TITLE",length = 50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "SERVICE_TYPE", columnDefinition = "integer")
	@Type(type = "loxia.dao.support.GenericEnumUserType", parameters = { @Parameter(name = "enumClass", value = "com.jumbo.model.sales.enums.SoServiceType") })
	public SoServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(SoServiceType serviceType) {
		this.serviceType = serviceType;
	}
	
	@Column(name = "SERVICE_TYPE_DESC",length = 50)
	public String getServiceTypeDesc() {
		return serviceTypeDesc;
	}

	public void setServiceTypeDesc(String serviceTypeDesc) {
		this.serviceTypeDesc = serviceTypeDesc;
	}

	@Column(name = "TMSER_SPU_CODE",length = 50)
	public String getTmserSpuCode() {
		return tmserSpuCode;
	}

	public void setTmserSpuCode(String tmserSpuCode) {
		this.tmserSpuCode = tmserSpuCode;
	}

	@Column(name = "PROVIDER_CODE",length = 50)
	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	@Column(name = "PROVIDER_NAME",length = 150)
	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}
