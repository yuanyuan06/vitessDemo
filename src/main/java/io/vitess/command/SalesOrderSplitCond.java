package io.vitess.command;

import io.vitess.common.DateUtil;
import io.vitess.common.NumberUtils;
import io.vitess.model.so.SoDeliveryInfo;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesOrderSplitCond implements Serializable {

	private static final long serialVersionUID = 9023073890214663162L;
	
	private Long soId;
	
	/**
	 * 订单商品明细行，半角逗号分隔(,)
	 */
	private String soLineIdsStr;
	
	/**
	 * 订单服务行，半角逗号分隔(,)
	 */
	private String serviceLineIdArr;
	
	/**
	 * 创建时间-开始
	 */
	private String soCreateTimeBeginStr;
	
	/**
	 * 创建时间-结束
	 */
	private String soCreateTimeEndStr;
	
	/**
	 * 平台订单号
	 */
	private String platformOrderCode;
	
	/**
	 * OMS订单号
	 */
	private String omsOrderCode;
	
	/**
	 * 子订单号
	 */
	private String platformOrderCodeN;
	
	/**
	 * PACS订单号
	 */
	private String erpOrderCode;
	
	/**
	 * 新订单（拆单）-
	 */
	private String newPlatformOrderCodeN;
	
	/** 是否需要发票 */
	private Integer needInvoice;

	/** 是否需要拆票 */
	private Integer isBillingManual;

	/** 发票类型(1:普通商业零售发票、2:增值税专用发票) */
	private Integer invoiceTypeInt;
	
	/**  发票备注 */
    private String invoiceMemo;

	/** 发票抬头 */
	private String invoiceTitle;
	
	/** 是否开具发票明细 */
	private Integer isBillingInvoiceDetail;

	/** 发票内容 */
	private String invoiceContent;

	/** 增票-公司名 */
	private String vaTaxCompanyName;

	/** 赠票-税号 */
	private String vaTaxCode;

	/** 赠票-公司注册地址 */
	private String vaTaxRegisterAddress;

	/** 赠票-公司电话 */
	private String vaTaxTelephone;

	/** 赠票-开户行 */
	private String vaTaxBankName;

	/** 赠票-开户行帐号 */
	private String vaTaxBankCard;
	
	/** 卖家备注 */
	private String sellerMemo;

	/** 买家备注 */
	private String buyerMemo;
	/**
	 * 发货信息
	 */
	private SoDeliveryInfo soDeliveryInfo;
	
	/**
	 * 运费
	 */
	private BigDecimal transFee;
	
	public Map<String, Object> getSoInfoMap() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        if (StringUtils.hasText(this.getSoCreateTimeBeginStr())) {
        	paramMap.put("createTimeBegin", DateUtil.parse(this.getSoCreateTimeBeginStr()));
        }
        
        if (StringUtils.hasText(this.getSoCreateTimeEndStr())) {
        	paramMap.put("createTimeEnd", DateUtil.parse(this.getSoCreateTimeEndStr()));
        }
        
        if (StringUtils.hasText(this.getOmsOrderCode())) {
        	paramMap.put("omsOrderCode", this.getOmsOrderCode());
        }
        
        if (StringUtils.hasText(this.getPlatformOrderCode())) {
        	paramMap.put("platformOrderCode", this.getPlatformOrderCode());
        }
        
        if (StringUtils.hasText(this.getPlatformOrderCodeN())) {
        	paramMap.put("platformOrderCodeN", this.getPlatformOrderCodeN());
        }
        
        if (StringUtils.hasText(this.getErpOrderCode())) {
        	paramMap.put("erpOrderCode", this.getErpOrderCode());
        }
        
        return paramMap;
    }
	
	/**
	 * 订单明细行ID
	 */
	public List<Long> getSalesOrderLineIdList() {
		List<Long> salesOrderLineIdList = new ArrayList<Long>();
		if (StringUtils.hasText(this.getSoLineIdsStr())) {
			for (String soLineId : soLineIdsStr.split(",")) {
    			if (NumberUtils.isNumber(soLineId)) {
    				salesOrderLineIdList.add(NumberUtils.convertToBigDecimal(soLineId).longValue());
    			}
    		}
		}
		return salesOrderLineIdList;
	}
	
	/**
	 * 服务明细行ID
	 */
	public List<Long> getSoServiceLineIdList() {
		List<Long> soServiceLineIdList = new ArrayList<Long>();
		if (StringUtils.hasText(this.getServiceLineIdArr())) {
			for (String soLineId : serviceLineIdArr.split(",")) {
				if (NumberUtils.isNumber(soLineId)) {
					soServiceLineIdList.add(NumberUtils.convertToBigDecimal(soLineId).longValue());
				}
			}
		}
		return soServiceLineIdList;
	}

	public Long getSoId() {
		return soId;
	}

	public void setSoId(Long soId) {
		this.soId = soId;
	}

	public String getSoLineIdsStr() {
		return soLineIdsStr;
	}

	public void setSoLineIdsStr(String soLineIdsStr) {
		this.soLineIdsStr = soLineIdsStr;
	}

	public String getServiceLineIdArr() {
		return serviceLineIdArr;
	}

	public void setServiceLineIdArr(String serviceLineIdArr) {
		this.serviceLineIdArr = serviceLineIdArr;
	}

	public String getSoCreateTimeBeginStr() {
		return soCreateTimeBeginStr;
	}

	public void setSoCreateTimeBeginStr(String soCreateTimeBeginStr) {
		this.soCreateTimeBeginStr = soCreateTimeBeginStr;
	}

	public String getSoCreateTimeEndStr() {
		return soCreateTimeEndStr;
	}

	public void setSoCreateTimeEndStr(String soCreateTimeEndStr) {
		this.soCreateTimeEndStr = soCreateTimeEndStr;
	}


	public String getPlatformOrderCode() {
		return platformOrderCode;
	}

	public void setPlatformOrderCode(String platformOrderCode) {
		this.platformOrderCode = platformOrderCode;
	}

	public String getOmsOrderCode() {
		return omsOrderCode;
	}

	public void setOmsOrderCode(String omsOrderCode) {
		this.omsOrderCode = omsOrderCode;
	}

	public String getPlatformOrderCodeN() {
		return platformOrderCodeN;
	}

	public void setPlatformOrderCodeN(String platformOrderCodeN) {
		this.platformOrderCodeN = platformOrderCodeN;
	}

	public String getNewPlatformOrderCodeN() {
		return newPlatformOrderCodeN;
	}

	public void setNewPlatformOrderCodeN(String newPlatformOrderCodeN) {
		this.newPlatformOrderCodeN = newPlatformOrderCodeN;
	}

	public SoDeliveryInfo getSoDeliveryInfo() {
		return soDeliveryInfo;
	}

	public void setSoDeliveryInfo(SoDeliveryInfo soDeliveryInfo) {
		this.soDeliveryInfo = soDeliveryInfo;
	}

	public Integer getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(Integer needInvoice) {
		this.needInvoice = needInvoice;
	}

	public Integer getIsBillingManual() {
		return isBillingManual;
	}

	public void setIsBillingManual(Integer isBillingManual) {
		this.isBillingManual = isBillingManual;
	}

	public Integer getInvoiceTypeInt() {
		return invoiceTypeInt;
	}

	public void setInvoiceTypeInt(Integer invoiceTypeInt) {
		this.invoiceTypeInt = invoiceTypeInt;
	}

	public String getInvoiceMemo() {
		return invoiceMemo;
	}

	public void setInvoiceMemo(String invoiceMemo) {
		this.invoiceMemo = invoiceMemo;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public Integer getIsBillingInvoiceDetail() {
		return isBillingInvoiceDetail;
	}

	public void setIsBillingInvoiceDetail(Integer isBillingInvoiceDetail) {
		this.isBillingInvoiceDetail = isBillingInvoiceDetail;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getVaTaxCompanyName() {
		return vaTaxCompanyName;
	}

	public void setVaTaxCompanyName(String vaTaxCompanyName) {
		this.vaTaxCompanyName = vaTaxCompanyName;
	}

	public String getVaTaxCode() {
		return vaTaxCode;
	}

	public void setVaTaxCode(String vaTaxCode) {
		this.vaTaxCode = vaTaxCode;
	}

	public String getVaTaxRegisterAddress() {
		return vaTaxRegisterAddress;
	}

	public void setVaTaxRegisterAddress(String vaTaxRegisterAddress) {
		this.vaTaxRegisterAddress = vaTaxRegisterAddress;
	}

	public String getVaTaxTelephone() {
		return vaTaxTelephone;
	}

	public void setVaTaxTelephone(String vaTaxTelephone) {
		this.vaTaxTelephone = vaTaxTelephone;
	}

	public String getVaTaxBankName() {
		return vaTaxBankName;
	}

	public void setVaTaxBankName(String vaTaxBankName) {
		this.vaTaxBankName = vaTaxBankName;
	}

	public String getVaTaxBankCard() {
		return vaTaxBankCard;
	}

	public void setVaTaxBankCard(String vaTaxBankCard) {
		this.vaTaxBankCard = vaTaxBankCard;
	}

	public String getSellerMemo() {
		return sellerMemo;
	}

	public void setSellerMemo(String sellerMemo) {
		this.sellerMemo = sellerMemo;
	}

	public String getBuyerMemo() {
		return buyerMemo;
	}

	public void setBuyerMemo(String buyerMemo) {
		this.buyerMemo = buyerMemo;
	}

	public BigDecimal getTransFee() {
		return transFee;
	}

	public void setTransFee(BigDecimal transFee) {
		this.transFee = transFee;
	}

	public String getErpOrderCode() {
		return erpOrderCode;
	}

	public void setErpOrderCode(String erpOrderCode) {
		this.erpOrderCode = erpOrderCode;
	}
	
}
