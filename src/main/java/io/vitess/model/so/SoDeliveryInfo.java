package io.vitess.model.so;

import com.jumbo.Constants;
import com.jumbo.model.BaseModel;
import com.jumbo.model.sales.enums.TransTimeType;
import com.jumbo.util.FormatUtil;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "t_td_so_delivery_info")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
public class SoDeliveryInfo extends BaseModel {

    private static final long serialVersionUID = 6490463075316045036L;
    
    /** 销售订单ID，关联SALES_ORDER_ID */
    private SalesOrder salesOrder;
    
    /** 国家 */
    private String country;
    
    /** 省 */
    private String province;
    
    /** 市 */
    private String city;
    
    /** 区 */
    private String district;
    
    /** 地址 */
    private String address;
    
    /** 邮编 */
    private String zipcode;
    
    /** 联系人 */
    private String contactPerson;
    
    /** 收货人 */
    private String receiver;
    
    /** 收货人电话 */
    private String receiverPhone;
    
    /** 收货人手机 */
    private String receiverMobile;
    
    /** 收货人邮箱 */
    private String memberEmail;
    
    /** 物流商内部平台对接编码，全局唯一 */
    private String transExpCode;
    
    /** 物流商名称 */
    private String transName;
    
    /** 物流单号/快递单号 */
    private String transNumber;
    
    /**
     * 实际发货物流
     */
    private String actualTransExpCode;
    
    /**
     * 实际快递单号
     */
    private String actualTransNumber;
    
    /**
     * 实际物流商名称
     */
    private String actualTransName;
    
    /**
     * 运送方式(快递附加服务)
     * 1:普通
     * 4:空运
     * 6:陆运
     * 7:特惠
     */
    private Integer transServiceType = Constants.TRANS_SERVICE_TYPE_NORMAL;
    
    /** 派件时间类型（快递附加服务）1：普通	5：当日	 6：次日 */
    private TransTimeType transTimeType;
    
    /** 备注 */
    private String remark;
    
    /** 国家en */
    private String countryEn;
    
    /** 省en */
    private String provinceEn;
    
    /** 市en */
    private String cityEn;
    
    /** 区en */
    private String districtEn;
    
    /** 地址en */
    private String addressEn;
    
    /** 收货人en */
    private String receiverEn;
    
    /** 邮编EN */
    private String zipcodeEn;
    
    /** 备注en */
    private String remarkEn;
    
    /**门店编码  */
    private String storeCode;
    
    /**门店编码  */
    private String storeName;
    
    //店铺ID
    private Long shopId;
    /**
     * 大头笔
     */
    private String transBigWord;
    /**
     * 
     * @Description: 集包地
     * @author zhiyong.shi
     * 2017年7月23日
     */
    private String packageCenterCode;
    
    
    
    
    public SoDeliveryInfo() {}

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALES_ORDER_ID")
    public SalesOrder getSalesOrder() {
        return this.salesOrder;
    }
    
    @Column(name = "COUNTRY", length = 64)
    public String getCountry() {
        return this.country;
    }

    @Column(name = "PROVINCE", length = 64)
    public String getProvince() {
        return this.province;
    }

    @Column(name = "CITY", length = 64)
    public String getCity() {
        return this.city;
    }

    @Column(name = "DISTRICT", length = 64)
    public String getDistrict() {
        return this.district;
    }

    @Column(name = "ADDRESS", length = 512)
    public String getAddress() {
        return this.address;
    }

    @Column(name = "ZIPCODE", length = 16)
    public String getZipcode() {
        return this.zipcode;
    }

    @Column(name = "RECEIVER", length = 64)
    public String getReceiver() {
        return this.receiver;
    }

    @Column(name = "RECEIVER_PHONE", length = 32)
    public String getReceiverPhone() {
        return this.receiverPhone;
    }

    @Column(name = "RECEIVER_MOBILE", length = 32)
    public String getReceiverMobile() {
        return this.receiverMobile;
    }
    
    @Column(name = "MEMBER_EMAIL", length = 128)
    public String getMemberEmail() {
        return this.memberEmail;
    }

    @Column(name = "TRANS_TIME_TYPE", columnDefinition = "integer")
    @Type(type = "loxia.dao.support.GenericEnumUserType", parameters = {@Parameter(name = "enumClass", value = "com.jumbo.model.sales.enums.TransTimeType")})
    public TransTimeType getTransTimeType() {
        return this.transTimeType;
    }

    @Column(name = "REMARK", length = 256)
    public String getRemark() {
        return this.remark;
    }
    
    @Column(name = "ACTUAL_TRANS_EXP_CODE", length = 20)
    public String getActualTransExpCode() {
		return actualTransExpCode;
	}

	public void setActualTransExpCode(String actualTransExpCode) {
		this.actualTransExpCode = actualTransExpCode;
	}

	@Column(name = "ACTUAL_TRANS_NUMBER", length = 50)
	public String getActualTransNumber() {
		return actualTransNumber;
	}

	public void setActualTransNumber(String actualTransNumber) {
		this.actualTransNumber = actualTransNumber;
	}

	@Column(name = "ACTUAL_TRANS_NAME", length = 50)
	public String getActualTransName() {
		return actualTransName;
	}

	public void setActualTransName(String actualTransName) {
		this.actualTransName = actualTransName;
	}

	@Column(name = "TRANS_SERVICE_TYPE")
	public Integer getTransServiceType() {
		return transServiceType;
	}

    @Column(name = "TRANS_NUMBER", length = 32)
    public String getTransNumber() {
        return transNumber;
    }

    public void setTransNumber(String transNumber) {
        this.transNumber = transNumber;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public void setTransTimeType(TransTimeType transTimeType) {
        this.transTimeType = transTimeType;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public void setMemberEmail(String memberEmail){
		this.memberEmail = memberEmail;
	}
	
	public void setTransServiceType(Integer transServiceType) {
		this.transServiceType = transServiceType;
	}
	
	@Column(name = "CONTACT_PERSON", length = 128)
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = FormatUtil.replaceSpecialCharacter(contactPerson);
    }

    @Column(name = "TRANS_EXP_CODE", length = 20)
    public String getTransExpCode() {
        return transExpCode;
    }

    public void setTransExpCode(String transExpCode) {
        this.transExpCode = transExpCode;
    }

    @Column(name = "TRANS_NAME", length = 100)
    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }
    
    @Column(name = "COUNTRY_EN")
	public String getCountryEn(){
		return countryEn;
	}

	public void setCountryEn(String countryEn){
		this.countryEn = countryEn;
	}

	@Column(name = "PROVINCE_EN")
	public String getProvinceEn(){
		return provinceEn;
	}

	public void setProvinceEn(String provinceEn){
		this.provinceEn = provinceEn;
	}

	@Column(name = "CITY_EN")
	public String getCityEn(){
		return cityEn;
	}

	public void setCityEn(String cityEn){
		this.cityEn = cityEn;
	}

	@Column(name = "DISTRICT_EN")
	public String getDistrictEn(){
		return districtEn;
	}

	public void setDistrictEn(String districtEn){
		this.districtEn = districtEn;
	}

	@Column(name = "ADDRESS_EN")
	public String getAddressEn(){
		return addressEn;
	}

	public void setAddressEn(String addressEn){
		this.addressEn = addressEn;
	}

	@Column(name = "RECEIVER_EN")
	public String getReceiverEn(){
		return receiverEn;
	}

	public void setReceiverEn(String receiverEn){
		this.receiverEn = receiverEn;
	}

	@Column(name = "ZIPCODE_EN")
	public String getZipcodeEn() {
		return zipcodeEn;
	}

	public void setZipcodeEn(String zipcodeEn) {
		this.zipcodeEn = zipcodeEn;
	}

	@Column(name = "REMARK_EN")
	public String getRemarkEn() {
		return remarkEn;
	}

	public void setRemarkEn(String remarkEn) {
		this.remarkEn = remarkEn;
	}

	@Column(name = "STORE_CODE")
	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Column(name = "STORE_NAME")
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	@Column(name = "TRANS_BIG_WORD")
	public String getTransBigWord() {
		return transBigWord;
	}

	public void setTransBigWord(String transBigWord) {
		this.transBigWord = transBigWord;
	}
	@Column(name = "PACKAGE_CENTER_CODE")
	public String getPackageCenterCode() {
		return packageCenterCode;
	}

	public void setPackageCenterCode(String packageCenterCode) {
		this.packageCenterCode = packageCenterCode;
	}
	
}
