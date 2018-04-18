package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableName;
import com.jumbo.model.BaseModel;
import com.jumbo.model.sales.enums.SalesMode;
import io.vitess.common.SuperEntity;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品
 * 
 * @author fanht
 * 
 */
@TableName("t_ma_sku")
public class Product extends SuperEntity{
	private static final long serialVersionUID = 5469350702688787492L;

	public static final Integer SKU_STATUS_ONSALES = 5;

	public static final Integer SKU_STATUS_OFFSALES = 1;

	public static final Integer SKU_STATUS_DISABLED = 0;

	public static final Integer SKU_STOCK_STATUS_ENOUGH = 5; // 现货

	public static final Integer SKU_STOCK_STATUS_OUT_OF_STOCK = 0; // 缺货

	public static final Integer SKU_STOCK_STATUS_RESERVE = 1; // 预订

	public static final Integer PRODUCT_WARRANTY_CARD_TYPE_NOT_NEEDED = new Integer(1);// 1:无需保修卡

	public static final Integer PRODUCT_WARRANTY_CARD_TYPE_NEEDED = new Integer(2);// 2:需要保修卡

	public static final Integer PRODUCT_WARRANTY_CARD_TYPE_NOT_FORCED = new Integer(3);// 3:不强制校验保修卡

	/**
	 * 商品标识 PK
	 */
	private Long id;

	/**
	 * JM商品编码
	 */
	private String code;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品货号
	 */
	private String supplierSkuCode;

	/**
	 * 商品类型
	 */
	private ProductType type;

	/**
	 * 商品销售模式
	 *0: 付款经销
	 *1: 代销
	 *2: 结算经销
	 *3: 混合模式（结算经销+代销）
	 */
	@Deprecated
	private String salesModesStr;
	
	/**
	 * 商品销售模式
     *0: 付款经销
	 *1: 代销
	 *2: 结算经销
	 *3: 混合模式(结算经销+代销)
	 *##4: 混合模式(付款经销+结算经销)
	 */
	private Integer salesMode;

	/**
	 * 制造商
	 */
	private String manufacture;

	/**
	 * 商品毛重（kg）
	 */
	private BigDecimal grossWeight;

	/**
	 * 商品净重（kg）
	 */
	private BigDecimal netWeight;

	/**
	 * 商品长度（mm）
	 */
	private BigDecimal length;

	/**
	 * 商品宽度（mm）
	 */
	private BigDecimal width;

	/**
	 * 商品高度（mm）
	 */
	private BigDecimal height;

    /**
     * 是否是sn商品
     */
    private Boolean isSnSku;

	/**
	 * 英文名称
	 */
	private String enName;

	/**
	 * version
	 */
	private int version;

	/**
	 * 商品创建日期
	 */
	private Date createTime = new Date();

	/**
	 * 最后修改日期
	 */
	private Date lastModifyTime = new Date();

	/**
	 * 品牌商品类目
	 */
	private String brandCategory1;

	/**
	 * 商品对应的SKU列表
	 */
	private List<Sku> skuList = new ArrayList<Sku>();

	/**
	 * 是否可用
	 */
	private Boolean isAvailable = Boolean.TRUE;

	/**
	 * 是否赠品
	 */
	private Boolean isGift = Boolean.FALSE;

	/**
	 * 是否液体、锂电池
	 */
	private Boolean isRailway;

	/**
	 * 商品品类
	 */
	private String prodCategory;

	/**
	 * 保修类型 1:无需保修卡 2:需要保修卡3:不强制校验保修卡
	 */
	private Integer warrantyCardType = PRODUCT_WARRANTY_CARD_TYPE_NOT_NEEDED;

	/**
	 * 品牌编码
	 */
	private String brandCode;

	/**
	 * 品牌名称
	 */
	private String brandName;

	/**
	 * 供应商编码
	 */
	private String supplierCode;

	/**
	 * 供应商名称
	 */
	private String supplierName;

	/**
	 * 市场价
	 */
	private BigDecimal listPrice;
	
	/**
	 * 商品销售区域
	 */
	private String businessRegionType;
	
	/**
	 * 仓储客户代码
	 */
	private String whCustomerCode;

	/**
     * 品牌附加类型
     */
    private Integer brandSpecialType;
	
	/**
	 *  0	普通商品
	 *  1	有状态SN商品
	 *  2  平台虚拟仓储实物
	 *  3  N合一卡（有状态SN商品）
	 * 
	 */
	private Integer spType;
	
	public static Integer SP_TYPE_NORMAL = 0;
	
	public static Integer SP_TYPE_SVC_CARD = 1;
	
	public static Integer SP_TYPE_ETICKET_ACTUAL = 2;
	
	public static Integer SP_TYPE_SVC_MERGE = 3; //N合一

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	@Column(name = "CODE", length = 20)
	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	@Column(name = "SUPPLIER_SKU_CODE", length = 100)
	public String getSupplierSkuCode(){
		return supplierSkuCode;
	}

	public void setSupplierSkuCode(String supplierSkuCode){
		this.supplierSkuCode = supplierSkuCode;
	}

	@Column(name = "TYPE", columnDefinition = "TINYINT")
	@Type(type = "loxia.dao.support.GenericEnumUserType", parameters = { @Parameter(name = "enumClass", value = "com.jumbo.model.baseinfo.ProductType") })
	public ProductType getType(){
		return type;
	}

	public void setType(ProductType type){
		this.type = type;
	}

	@Column(name = "NAME", length = 300)
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@Column(name = "EN_NAME", length = 300)
	public String getEnName(){
		return enName;
	}

	public void setEnName(String enName){
		this.enName = enName;
	}

	@Version
	@Column(name = "VERSION")
	public int getVersion(){
		return version;
	}

	public void setVersion(int version){
		this.version = version;
	}

    @Column(name = "IS_SN_SKU", columnDefinition = "TINYINT")
    public Boolean getIsSnSku() {
        return isSnSku;
    }

    public void setIsSnSku(Boolean isSnSku) {
        this.isSnSku = isSnSku;
    }

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product", orphanRemoval = true)
	@OrderBy("id")
	public List<Sku> getSkuList(){
		return skuList;
	}

	public void setSkuList(List<Sku> skuList){
		this.skuList = skuList;
	}

	@Column(name = "SALES_MODES")
	public String getSalesModesStr(){
		return salesModesStr;
	}

	public void setSalesModesStr(String salesModesStr){
		this.salesModesStr = salesModesStr;
	}

	public void setSalesModes(List<SalesMode> salesModes){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < salesModes.size(); i++) {
			SalesMode tmp = salesModes.get(i);
			sb.append(tmp.getValue());
			if (i != salesModes.size() - 1) {
				sb.append(",");
			}
		}
		this.salesModesStr = sb.toString();
	}

	@Column(name = "GROSS_WEIGHT")
	public BigDecimal getGrossWeight(){
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight){
		this.grossWeight = grossWeight;
	}

	@Column(name = "NET_WEIGHT")
	public BigDecimal getNetWeight(){
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight){
		this.netWeight = netWeight;
	}

	@Column(name = "WIDTH")
	public BigDecimal getWidth(){
		return width;
	}

	public void setWidth(BigDecimal width){
		this.width = width;
	}

	@Column(name = "HEIGHT")
	public BigDecimal getHeight(){
		return height;
	}

	public void setHeight(BigDecimal height){
		this.height = height;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	@Column(name = "LAST_MODIFY_TIME")
	public Date getLastModifyTime(){
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime){
		this.lastModifyTime = lastModifyTime;
	}

	@Column(name = "BRAND_CATEGORY1")
	public String getBrandCategory1(){
		return brandCategory1;
	}

	public void setBrandCategory1(String brandCategory1){
		this.brandCategory1 = brandCategory1;
	}

	@Column(name = "IS_AVAILABLE", columnDefinition = "TINYINT")
	public Boolean getIsAvailable(){
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable){
		this.isAvailable = isAvailable;
	}

	@Column(name = "MANUFACTURE", length = 50)
	public String getManufacture(){
		return manufacture;
	}

	public void setManufacture(String manufacture){
		this.manufacture = manufacture;
	}

	@Column(name = "LENGTH")
	public BigDecimal getLength(){
		return length;
	}

	public void setLength(BigDecimal length){
		this.length = length;
	}

	@Column(name = "IS_GIFT", columnDefinition = "TINYINT")
	public Boolean getIsGift(){
		return isGift;
	}

	public void setIsGift(Boolean isGift){
		this.isGift = isGift;
	}

	@Column(name = "IS_RAILWAY", columnDefinition = "TINYINT")
	public Boolean getIsRailway(){
		return isRailway;
	}

	public void setIsRailway(Boolean isRailway){
		this.isRailway = isRailway;
	}

	@Column(name = "PROD_CATEGORY")
	public String getProdCategory(){
		return prodCategory;
	}

	public void setProdCategory(String prodCategory){
		this.prodCategory = prodCategory;
	}

	@Column(name = "WARRANTY_CARD_TYPE")
	public Integer getWarrantyCardType(){
		return warrantyCardType;
	}

	public void setWarrantyCardType(Integer warrantyCardType){
		this.warrantyCardType = warrantyCardType;
	}

	@Column(name = "BRAND_CODE", length = 50)
	public String getBrandCode(){
		return brandCode;
	}

	public void setBrandCode(String brandCode){
		this.brandCode = brandCode;
	}

	@Column(name = "BRAND_NAME", length = 100)
	public String getBrandName(){
		return brandName;
	}

	public void setBrandName(String brandName){
		this.brandName = brandName;
	}

	@Column(name = "SUPPLIER_CODE", length = 50)
	public String getSupplierCode(){
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode){
		this.supplierCode = supplierCode;
	}

	@Column(name = "SUPPLIER_NAME", length = 100)
	public String getSupplierName(){
		return supplierName;
	}

	public void setSupplierName(String supplierName){
		this.supplierName = supplierName;
	}

	@Column(name = "LIST_PRICE")
	public BigDecimal getListPrice(){
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice){
		this.listPrice = listPrice;
	}

	@Column(name = "BUSINESS_REGION_TYPE")
	public String getBusinessRegionType() {
		return businessRegionType;
	}

	public void setBusinessRegionType(String businessRegionType) {
		this.businessRegionType = businessRegionType;
	}
	
	@Column(name = "SP_TYPE")
	public Integer getSpType() {
		return spType;
	}

	public void setSpType(Integer spType) {
		this.spType = spType;
	}

	@Column(name = "BRAND_SPECIAL_TYPE")
    public Integer getBrandSpecialType() {
        return brandSpecialType;
    }

    public void setBrandSpecialType(Integer brandSpecialType) {
        this.brandSpecialType = brandSpecialType;
    }

    @Column(name = "SALES_MODE")
    public Integer getSalesMode() {
		return salesMode;
	}

	public void setSalesMode(Integer salesMode) {
		this.salesMode = salesMode;
	}
	
	@Column(name = "WH_CUSTOMER_CODE", length = 100)
    public String getWhCustomerCode() {
		return whCustomerCode;
	}

	public void setWhCustomerCode(String whCustomerCode) {
		this.whCustomerCode = whCustomerCode;
	}
	
}
