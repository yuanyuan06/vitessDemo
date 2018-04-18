package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableName;
import com.jumbo.model.BaseModel;
import io.vitess.common.SuperEntity;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 物流模板
 * @author fanht
 *
 */

@TableName("t_ma_def_trans_template")
public class DefaultTransTemplete extends SuperEntity{
    private static final long serialVersionUID = 8896970291216745685L;
    
    private Long id;
    private String code;
    private String name;
    private String remark;
    private Date lastModifyTime;
    private String lastModifyUser;
    /**
     * 是否排除EMS匹配逻辑
     */
    private Boolean isExcludeEMS;
    /**
     * 相关明细
     */
    private List<DefaultTransTempleteDetail> tempDetails;
    
    /**
     * COD默认物流服务商
     */
    private Transportator codTrans;
    
    /**
     * VERSION
     */
    private Integer version;
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "LAST_MODIFY_TIME")
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Column(name = "LAST_MODIFY_USER")
    public String getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "defTemp", orphanRemoval = false)
    @OrderBy("id")
    public List<DefaultTransTempleteDetail> getTempDetails() {
        return tempDetails;
    }

    public void setTempDetails(List<DefaultTransTempleteDetail> tempDetails) {
        this.tempDetails = tempDetails;
    }

    @Column(name = "IS_EXCLUDE_EMS")
    public Boolean getIsExcludeEMS() {
        return isExcludeEMS;
    }

    public void setIsExcludeEMS(Boolean isExcludeEMS) {
        this.isExcludeEMS = isExcludeEMS;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_TRANS_ID")
	public Transportator getCodTrans() {
		return codTrans;
	}

	public void setCodTrans(Transportator codTrans) {
		this.codTrans = codTrans;
	}
	
    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
}
