package io.vitess.model.so;

import com.jumbo.model.BaseModel;
import com.jumbo.model.baseinfo.CompanyShop;
import com.jumbo.model.baseinfo.Product;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "t_ma_vmi_timed_promotion")
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION)
public class VmiTimedPromotion extends BaseModel {
    private static final long serialVersionUID = 4601006153535998415L;

    private Long id;
    
    /**
     * version
     */
    private int version;
    
    private Product product;
    
    /**
     * VMI促销编码
     */
    private String code;
    
    private CompanyShop shop;
    /**
     * 活动类型
     * 01、商品售价打折 02、满金额整单减  03、满金额送赠品 04、单品买赠
     */
    private String proType;
    
    private String description;
    /**
     * 促销金额1（01类型促销时是商品售价,02类型促销时是优惠下限,03类型促销时是整单额度下限）
     */
    private BigDecimal proAmount;
    
    /**
     * 促销金额2（02类型促销时是优惠上线,03类型促销时是整单额度上限）
     */
    private BigDecimal proAmount2;
    
    private Integer status;
    
    private Date startTime;
    
    private Date endTime;
    
    /**
     * 赠品配额
     */
    private Integer giftQuota;
    
    /**
     * 类型时所使用的主卖品
     */
    private Product masterProduct;

    @Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKU_ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "CODE", length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOP_ID",updatable=false)
    public CompanyShop getShop() {
        return shop;
    }

    public void setShop(CompanyShop shop) {
        this.shop = shop;
    }

    @Column(name = "PRO_TYPE", length = 10)
    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    @Column(name = "DESCRIPTION", length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "PRO_AMOUNT", length = 15, scale = 5)
    public BigDecimal getProAmount() {
        return proAmount;
    }

    public void setProAmount(BigDecimal proAmount) {
        this.proAmount = proAmount;
    }

    @Column(name = "PRO_AMOUNT2", length = 15, scale = 5)
    public BigDecimal getProAmount2() {
        return proAmount2;
    }

    public void setProAmount2(BigDecimal proAmount2) {
        this.proAmount2 = proAmount2;
    }

    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "START_TIME", length = 7)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "END_TIME", length = 7)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "GIFT_QUOTA")
    public Integer getGiftQuota() {
        return giftQuota;
    }

    public void setGiftQuota(Integer giftQuota) {
        this.giftQuota = giftQuota;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MST_SKU_ID")
    public Product getMasterProduct() {
        return masterProduct;
    }

    public void setMasterProduct(Product masterProduct) {
        this.masterProduct = masterProduct;
    } 
    
}
