package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableName;
import com.jumbo.model.BaseModel;
import io.vitess.common.SuperEntity;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * SKU
 * 
 * @author fanht
 * 
 */
@TableName("t_ma_inv_sku")
public class Sku extends SuperEntity {
    private static final long serialVersionUID = 2960655395582098457L;

    /**
     * PK
     */
    private Long id;

    /**
     * 编码，唯一标示Sku的编码 JM_SKU_CODE
     */
    private String code;
    
    /**
     * 供应商商品编码
     */
    private String supplierCode;
    
    /**
     * Sku条码，唯一标示Sku的编码
     */
    private String barCode;
    
    /**
     * Product中JMCode
     */
    private String jmCode;//prodCode
    
    /**
     * 扩展属性
     */
    private String keyProperties;

    /**
     * Sku名称，一般来说这个信息=Product的名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 其他用于唯一标示Sku的编码:官方商城,淘宝...
     */
    private String extensionCode1;

    /**
     * 其他用于唯一标示Sku的编码:品牌信息
     */
    private String extensionCode2;

    /**
     * 其他用于唯一标示Sku的编码
     */
    private String extensionCode3;

    /**
     * version
     */
    private int version;

    /**
     * 所属产品
     */
    private Product product;
    
    /**
     * 颜色编码
     */
    private String color;
    
    /**
     * 颜色中文描述
     */
    private String colorName;
    
    /**
     * 尺码
     */
    private String skuSize;
    
    /**
     * SKU特殊类型(如apple学生价商品)
     */
    private SkuSpecialType specialType = SkuSpecialType.DEFAULT;
    
    /**
     * 仓储系统商品编码
     */
    private String itemId;
    /**
     * 
     * @Description: 配送类型
     * @author zhiyong.shi
     * 2017年6月16日
     */
    private String shippingMethods;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CODE", length = 150)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "NAME", length = 300)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "EN_NAME", length = 300)
    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "EXT_CODE1", length = 150)
    @Index(name = "IDX_EXT_CODE")
    public String getExtensionCode1() {
        return extensionCode1;
    }

    public void setExtensionCode1(String extensionCode1) {
        this.extensionCode1 = extensionCode1;
    }

    @Column(name = "EXT_CODE2", length = 150)
    public String getExtensionCode2() {
        return extensionCode2;
    }

    public void setExtensionCode2(String extensionCode2) {
        this.extensionCode2 = extensionCode2;
    }

    @Column(name = "EXT_CODE3", length = 150)
    public String getExtensionCode3() {
        return extensionCode3;
    }

    public void setExtensionCode3(String extensionCode3) {
        this.extensionCode3 = extensionCode3;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "BAR_CODE", length = 100)
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Column(name = "KEY_PROPERTIES", length = 100)
    public String getKeyProperties() {
        return keyProperties;
    }

    public void setKeyProperties(String keyProperties) {
        this.keyProperties = keyProperties;
    }

    @Column(name = "SUPPLIER_CODE", length = 100)
    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Column(name = "JM_CODE", length = 255)
    public String getJmCode() {
        return jmCode;
    }

    public void setJmCode(String jmCode) {
        this.jmCode = jmCode;
    }

    @Column(name = "COLOR", length = 50)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "COLOR_NAME", length = 50)
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Column(name = "SKU_SIZE", length = 50)
    public String getSkuSize() {
        return skuSize;
    }

    public void setSkuSize(String skuSize) {
        this.skuSize = skuSize;
    }

    @Column(name = "SPECIAL_TYPE", columnDefinition = "TINYINT")
    @Type(type = "loxia.dao.support.GenericEnumUserType", parameters = {@Parameter(name = "enumClass", value = "com.jumbo.model.baseinfo.SkuSpecialType")})
	public SkuSpecialType getSpecialType() {
		return specialType;
	}

	public void setSpecialType(SkuSpecialType specialType) {
		this.specialType = specialType;
	}
	
	@Column(name = "ITEM_ID", length = 50)
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	@Column(name = "SHIPPING_METHODS")
	public String getShippingMethods() {
		return shippingMethods;
	}

	public void setShippingMethods(String shippingMethods) {
		this.shippingMethods = shippingMethods;
	}
	
}
