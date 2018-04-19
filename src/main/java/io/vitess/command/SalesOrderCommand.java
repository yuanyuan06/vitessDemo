package io.vitess.command;

import io.vitess.constants.Constants;
import io.vitess.model.so.OrderMember;
import io.vitess.model.so.PlatformPromotion;
import io.vitess.model.so.SalesOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YSH4807
 * @date 2018/4/19 21:21
 */
public class SalesOrderCommand extends SalesOrder {
    private static final long serialVersionUID = -5306506326418206277L;


    /**
     * 用户邮箱
     */
    private String memberEmail;

    /**
     * 会员真实姓名
     */
    private String memberName;

    /**
     * so是否涉及多个送货地址
     */
    private boolean soMoreAddress = false;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 送货地址
     */
    private String address;

    /**
     * 邮编
     */
    private String zipcode;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 收货人电话
     */
    private String receiveryPhone;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 区
     */
    private String district;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 发货信息备注
     */
    private String deliveryRemark;

    /**
     * 物流供应商外部编码
     */
    private String transExpCode;
    /**
     * 订单类型-int
     */
    private Integer orderTypeInt;
    /**
     *  订单类型-string
     **/
    private String orderTypeStr;
    /**
     * 订单状态-int
     */
    private Integer statusInt;
    /**
     * 订单状态-string
     */
    private String statusStr;

    /**
     * 是否预售订单
     */
    private String isPresaleStr;

    private String platformOrderStatus;
    /**
     * 创建开始时间
     */
    private Date startCreateTime;
    /**
     * 创建结束时间
     */
    private Date endCreateTime;
    /**
     * 创建开始时间
     */
    private Date startPlatformPaymentTime;
    /**
     * 创建结束时间
     */
    private Date endPlatformPaymentTime;

    /**
     * NIKE 发票导出类型：2,3: nikeid &giftcard;4: return
     */
    private Integer soInvoiceTaxNikeType;


    private Long shopId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    private List<SalesOrderLineCommand> soLineCommandList;

    private OrderMember orderMember;

    public Date getEndPlatformPaymentTime() {
        return endPlatformPaymentTime;
    }

    public void setEndPlatformPaymentTime(Date endPlatformPaymentTime) {
        this.endPlatformPaymentTime = endPlatformPaymentTime;
    }

    /**
     * 确认开始时间
     */
    private Date startSureTime;
    /**
     * 确认结束时间
     */
    private Date endSureTime;
    /**
     * 发货开始时间
     */
    private Date startDelieveryTime;
    /**
     * 发货结束时间
     */
    private Date endDelieveryTime;
    /**
     * 物流商名称
     */
    private String transName;
    /**
     * WMS推荐物流商名称
     */
    private String recommendTransName;
    /**
     * 关键 属性
     */
    private String keyProperties;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 编码
     */
    private String skuCode;
    /**
     * 快递单号
     */
    private String transCode;
    /**
     * 商品编码
     */
    private String productCode;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 货号
     */
    private String supplierCode;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 收货人手机
     *
     */
    private String receiverMobile;
    /**
     * 发货仓库
     *
     * @return
     */
    private String warehouseStr;
    private Integer isWlbOrderInt;

    /**
     * k3客户id
     */
    private Long k3CustomerId;

    /**
     * k3客户code
     */
    private String k3CustomerCode;

    /**
     * k3客户name
     */
    private String k3CustomerName;

    /**
     * 物流服务商id
     */
    private Long transportId;

    /**
     * 是否需要发票（0: 否1：是）
     */
    private Integer isNeedInvoiceInt;

    /**
     * 是否需要发票（0: 否1：是）
     */
    private Integer isPurchaseCycleInt;

    /**
     * 是否需要发票Str
     */
    private String isNeedInvoiceStr;

    /**
     * 发票类型(1:普票 2:增票)
     */
    private Integer invoiceTypeValue;

    /**
     * 发票是否需要备注明细（0: 否 1：是）
     */
    private Integer isBillingInvoiceDetailInt;

    /**
     * 是否需要财务手工拆票（0: 否 1：是）
     */
    private Integer isBillingManualInt;

    private String orderSeq;

    private String shopName;

    private String currentNodeNo;

    private Date toCreateTime;

    private Date toLastTowhFailedTime;

    private Date LastTowhFailedTime;
    private String Description;
    private String ResultShow;

    /**
     * 订单总金额
     */
    private BigDecimal salesOrderTotalFee;

    /**
     * 订单商品总金额
     */
    private BigDecimal unitPriceTotal;

    /**
     * 订单总数量
     */
    private Long requestedTotalQty;

    /**
     * 订单商品金额
     */
    private BigDecimal unitPrice;

    /**
     * 订单数量
     */
    private BigDecimal requestedQty;




    /**
     * 是否有物流
     */
    private Boolean isNullTransportator;

    private Long divisionId;

    private Integer paymentStatusInt;
    /**
     * 发货仓库ID
     *
     * @return
     */
    private String whOuId;

    /**
     * 发货仓库名称
     *
     * @return
     */
    private String whOuName;

    /**
     * 该订单最近的退换货所产生的新so编码
     */
    private String newestSoCode;

    /**
     * 付款方式
     */
    private String paymentTypeStr;
    private String expenseTime;
    /**
     * order 状态
     */
    private String orderStatusStr;


    private String orderContent;

    /**
     * 物流方式（00：客户自提 01：商城快递）
     */
    private String transportatorType;

    private String telephone;


    private String detailKinds;

    private String isWlbOrderStr;
    private String isLandTransStr;

    private Integer financeStatusInteger;

    private String isOwnWmsStr;

    private BigDecimal needPayTotalFee;

    private Long whId;

    private Integer goodsTypeInt;

    /**
     * 确认审批时间
     */
    private Date startApproveTime;
    /**
     * 确认审批时间
     */
    private Date endApproveTime;

    /**
     * 是否为o2o店铺订单(1:表示是，0表示否)
     */
    private Integer isO2oShop;

    /**
     * 是否O2O订单
     */
    private Boolean isO2oOrder = Boolean.FALSE;

    private Integer wfBranchInt;

    private String account; // 买家会员名
    private BigDecimal totalSkuFee; // 买家应付货款

    private Date paytime; // 订单付款时间
    private String remark; // 买家留言
    private BigDecimal actualTransFee; // 买家应付邮费
    private String lpcode;
    private String expcode;//送货方式
    private String upc;
    private Integer qty;

    private String wfBranchStr;

    //尊宝网使用
    private String orderCode;
    private String orderCode2;
    private String memberCode;
    private BigDecimal postFee;
    private BigDecimal qtyBig;
    /**
     * umboMart FOB（含税）
     */
    private BigDecimal fob;
    /**
     * 市场价
     */
    private BigDecimal listPrice;
    /**
     * 商品单价
     */
    private BigDecimal lineUnitPrice;
    /**
     * 用于尊宝分页使用
     */
    private Integer num;

    /**
     * 是否周期购订单
     */
    private String isPurchaseCycleStr;

    /**
     * 是否预售订单
     */
    private String isPreSalesStr;

    /**
     * 是否预售订单
     */
    private Integer isPreSalesInt;

    /**
     * 预计到货时间-开始时间
     */
    private Date estArrivalTimeBegin;

    /**
     * 预计到货时间-结束时间
     */
    private Date estArrivalTimeEnd;

    /**
     * 预计到货时间-开始时间（字符串）
     */
    private String estArrivalTimeBeginStr;

    /**
     * 预计到货时间-结束时间（字符串）
     */
    private String estArrivalTimeEndStr;

    /**
     * 是否授信订单
     */
    private String isCreditOrderStr;
    /**
     * 是否授信订单
     */
    private Integer isCreditOrderInt;
    /**
     * 发货商品编码
     */
    private String shipmentSkuCode;
    /**
     * 发货商品数量
     */
    private Integer shipmentSkuQty;
    /**
     * 发货失败原因
     */
    private String shipmentErrorMsg;
    /**
     * 接收发货数据时间
     */
    private Date shipmentCreateTime;


    /**
     * 发票类型(1:普票(普通商业零售发票) 2:增票(增值税专用发票))
     */
    private String invoiceTypeStr;

    /**
     * 发票形式(true:电子发票 false:纸质发票)
     */
    private String invoiceKindStr;

    private String givenName;
    private String familyName;
    /**
     * 运送方式(快递附加服务)
     * 1:普通
     * 4:空运
     * 6:陆运
     * 7:特惠
     */
    private Integer transServiceType = Constants.TRANS_SERVICE_TYPE_NORMAL;


    /**
     * 快递时间限制（快递附加服务）
     * 1：普通
     * 5：当日
     * 6：次日
     */
    private Integer transTimeType = Constants.TRANS_TIME_TYPE_NORMAL;
    /**
     * 订单平台金额促销活动信息
     */
    private List<PlatformPromotion> soPlatformPromotionList;
    /**
     * 是否不合并订单行
     */
    private Boolean isNotToMergerSoLine;

    private String platformCreateTimeStr;

    private String platformPaymentTimeStr;
    //刷款人名
    private String payeeName;

    /**
     *  是否禁止线下单（GO单+补发单）
     */
    private Boolean isForbidOffline;

    /**
     * 平台已退积分金额
     */
    private BigDecimal rtnPlatformPointAmt;
    /**
     * 当前未退实际货币金额
     */
    private BigDecimal noRefundAmt;

    /**
     * 是否忽略店铺的默认开发票设置
     */
    private Boolean ignoreDefaultInvoiceApply=Boolean.FALSE;

    private List<SoServiceLineCommand> soServiceLineCmdList;

    /** 订单类型 **/
    private Integer specialTypeInt;

    /**
     * 订单类型
     */
    private String soSpecialTypeStr;

    /**
     * trade id
     */
    private Long tradeId;


    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPlatformPaymentTimeStr() {
        return platformPaymentTimeStr;
    }

    public void setPlatformPaymentTimeStr(String platformPaymentTimeStr) {
        this.platformPaymentTimeStr = platformPaymentTimeStr;
    }

    public String getExpenseTime() {
        return expenseTime;
    }

    public void setExpenseTime(String expenseTime) {
        this.expenseTime = expenseTime;
    }

    public Integer getPaymentStatusInt() {
        return paymentStatusInt;
    }

    public void setPaymentStatusInt(Integer paymentStatusInt) {
        this.paymentStatusInt = paymentStatusInt;
    }

    public Long getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Long divisionId) {
        this.divisionId = divisionId;
    }

    private String handleResult;

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public SalesOrder generateSalesOrder(SalesOrder newSo){
        try {
            BeanUtils.copyProperties(this, newSo);
        } catch (Exception e) {
            throw new RuntimeException("copy property from socommand to so error.");
        }
        return newSo;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveryPhone() {
        return receiveryPhone;
    }

    public void setReceiveryPhone(String receiveryPhone) {
        this.receiveryPhone = receiveryPhone;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeliveryRemark() {
        return deliveryRemark;
    }

    public void setDeliveryRemark(String deliveryRemark) {
        this.deliveryRemark = deliveryRemark;
    }

    public String getTransExpCode() {
        return transExpCode;
    }

    public void setTransExpCode(String transExpCode) {
        this.transExpCode = transExpCode;
    }

    public Integer getIsWlbOrderInt() {
        return isWlbOrderInt;
    }

    public void setIsWlbOrderInt(Integer isWlbOrderInt) {
        this.isWlbOrderInt = isWlbOrderInt;
    }

    public String getWarehouseStr() {
        return warehouseStr;
    }

    public void setWarehouseStr(String warehouseStr) {
        this.warehouseStr = warehouseStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Date getStartSureTime() {
        return startSureTime;
    }

    public void setStartSureTime(Date startSureTime) {
        this.startSureTime = startSureTime;
    }

    public Date getEndSureTime() {
        return endSureTime;
    }

    public void setEndSureTime(Date endSureTime) {
        this.endSureTime = endSureTime;
    }

    public Date getStartDelieveryTime() {
        return startDelieveryTime;
    }

    public void setStartDelieveryTime(Date startDelieveryTime) {
        this.startDelieveryTime = startDelieveryTime;
    }

    public Date getEndDelieveryTime() {
        return endDelieveryTime;
    }

    public void setEndDelieveryTime(Date endDelieveryTime) {
        this.endDelieveryTime = endDelieveryTime;
    }

    public Integer getStatusInt() {
        return statusInt;
    }

    public void setStatusInt(Integer statusInt) {
        this.statusInt = statusInt;
    }

    public String getOrderTypeStr() {
        return orderTypeStr;
    }

    public void setOrderTypeStr(String orderTypeStr) {
        this.orderTypeStr = orderTypeStr;
    }

    public Integer getOrderTypeInt() {
        return orderTypeInt;
    }

    public void setOrderTypeInt(Integer orderTypeInt) {
        this.orderTypeInt = orderTypeInt;
    }

    public Long getK3CustomerId() {
        return k3CustomerId;
    }

    public void setK3CustomerId(Long k3CustomerId) {
        this.k3CustomerId = k3CustomerId;
    }

    public String getK3CustomerCode() {
        return k3CustomerCode;
    }

    public void setK3CustomerCode(String k3CustomerCode) {
        this.k3CustomerCode = k3CustomerCode;
    }

    public String getK3CustomerName() {
        return k3CustomerName;
    }

    public void setK3CustomerName(String k3CustomerName) {
        this.k3CustomerName = k3CustomerName;
    }

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public Integer getIsNeedInvoiceInt() {
        return isNeedInvoiceInt;
    }

    public void setIsNeedInvoiceInt(Integer isNeedInvoiceInt) {
        this.isNeedInvoiceInt = isNeedInvoiceInt;
    }

    public Integer getInvoiceTypeValue() {
        return invoiceTypeValue;
    }

    public void setInvoiceTypeValue(Integer invoiceTypeValue) {
        this.invoiceTypeValue = invoiceTypeValue;
    }

    public Integer getIsBillingInvoiceDetailInt() {
        return isBillingInvoiceDetailInt;
    }

    public void setIsBillingInvoiceDetailInt(Integer isBillingInvoiceDetailInt) {
        this.isBillingInvoiceDetailInt = isBillingInvoiceDetailInt;
    }

    public Integer getIsBillingManualInt() {
        return isBillingManualInt;
    }

    public void setIsBillingManualInt(Integer isBillingManualInt) {
        this.isBillingManualInt = isBillingManualInt;
    }

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCurrentNodeNo() {
        return currentNodeNo;
    }

    public void setCurrentNodeNo(String currentNodeNo) {
        this.currentNodeNo = currentNodeNo;
    }

    public Date getToCreateTime() {
        return toCreateTime;
    }

    public void setToCreateTime(Date toCreateTime) {
        this.toCreateTime = toCreateTime;
    }

    public Date getToLastTowhFailedTime() {
        return toLastTowhFailedTime;
    }

    public void setToLastTowhFailedTime(Date toLastTowhFailedTime) {
        this.toLastTowhFailedTime = toLastTowhFailedTime;
    }

    public BigDecimal getSalesOrderTotalFee() {
        return salesOrderTotalFee;
    }

    public void setSalesOrderTotalFee(BigDecimal salesOrderTotalFee) {
        this.salesOrderTotalFee = salesOrderTotalFee;
    }

    public BigDecimal getUnitPriceTotal() {
        return unitPriceTotal;
    }

    public void setUnitPriceTotal(BigDecimal unitPriceTotal) {
        this.unitPriceTotal = unitPriceTotal;
    }

    public Long getRequestedTotalQty() {
        return requestedTotalQty;
    }

    public void setRequestedTotalQty(Long requestedTotalQty) {
        this.requestedTotalQty = requestedTotalQty;
    }

    public Boolean getIsNullTransportator() {
        return isNullTransportator;
    }

    public void setIsNullTransportator(Boolean isNullTransportator) {
        this.isNullTransportator = isNullTransportator;
    }

    public String getOrderStatusStr() {
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getTransportatorType() {
        return transportatorType;
    }

    public void setTransportatorType(String transportatorType) {
        this.transportatorType = transportatorType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDetailKinds() {
        return detailKinds;
    }

    public void setDetailKinds(String detailKinds) {
        this.detailKinds = detailKinds;
    }

    public String getWhOuId() {
        return whOuId;
    }

    public void setWhOuId(String whOuId) {
        this.whOuId = whOuId;
    }

    public String getWhOuName() {
        return whOuName;
    }

    public void setWhOuName(String whOuName) {
        this.whOuName = whOuName;
    }

    public String getNewestSoCode() {
        return newestSoCode;
    }

    public void setNewestSoCode(String newestSoCode) {
        this.newestSoCode = newestSoCode;
    }

    public String getPaymentTypeStr() {
        return paymentTypeStr;
    }

    public void setPaymentTypeStr(String paymentTypeStr) {
        this.paymentTypeStr = paymentTypeStr;
    }

    public String getIsWlbOrderStr() {
        return isWlbOrderStr;
    }

    public void setIsWlbOrderStr(String isWlbOrderStr) {
        this.isWlbOrderStr = isWlbOrderStr;
    }

    public Integer getFinanceStatusInteger() {
        return financeStatusInteger;
    }

    public void setFinanceStatusInteger(Integer financeStatusInteger) {
        this.financeStatusInteger = financeStatusInteger;
    }

    public String getIsOwnWmsStr() {
        return isOwnWmsStr;
    }

    public void setIsOwnWmsStr(String isOwnWmsStr) {
        this.isOwnWmsStr = isOwnWmsStr;
    }

    public BigDecimal getNeedPayTotalFee() {
        return needPayTotalFee;
    }

    public void setNeedPayTotalFee(BigDecimal needPayTotalFee) {
        this.needPayTotalFee = needPayTotalFee;
    }

    public Long getWhId() {
        return whId;
    }

    public void setWhId(Long whId) {
        this.whId = whId;
    }

    public Integer getGoodsTypeInt() {
        return goodsTypeInt;
    }

    public void setGoodsTypeInt(Integer goodsTypeInt) {
        this.goodsTypeInt = goodsTypeInt;
    }

    public Date getStartApproveTime() {
        return startApproveTime;
    }

    public void setStartApproveTime(Date startApproveTime) {
        this.startApproveTime = startApproveTime;
    }

    public Date getEndApproveTime() {
        return endApproveTime;
    }

    public void setEndApproveTime(Date endApproveTime) {
        this.endApproveTime = endApproveTime;
    }

    public String getIsNeedInvoiceStr() {
        return isNeedInvoiceStr;
    }

    public void setIsNeedInvoiceStr(String isNeedInvoiceStr) {
        this.isNeedInvoiceStr = isNeedInvoiceStr;
    }

    public Date getLastTowhFailedTime() {
        return LastTowhFailedTime;
    }

    public void setLastTowhFailedTime(Date lastTowhFailedTime) {
        LastTowhFailedTime = lastTowhFailedTime;
    }

    public Integer getIsO2oShop() {
        return isO2oShop;
    }

    public void setIsO2oShop(Integer isO2oShop) {
        this.isO2oShop = isO2oShop;
    }

    public Boolean getIsO2oOrder() {
        return isO2oOrder;
    }

    public void setIsO2oOrder(Boolean isO2oOrder) {
        this.isO2oOrder = isO2oOrder;
    }

    public Integer getWfBranchInt() {
        return wfBranchInt;
    }

    public void setWfBranchInt(Integer wfBranchInt) {
        this.wfBranchInt = wfBranchInt;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getTotalSkuFee() {
        return totalSkuFee;
    }

    public void setTotalSkuFee(BigDecimal totalSkuFee) {
        this.totalSkuFee = totalSkuFee;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getActualTransFee() {
        return actualTransFee;
    }

    public void setActualTransFee(BigDecimal actualTransFee) {
        this.actualTransFee = actualTransFee;
    }

    public String getWfBranchStr() {
        return wfBranchStr;
    }

    public void setWfBranchStr(String wfBranchStr) {
        this.wfBranchStr = wfBranchStr;
    }

    public String getLpcode() {
        return lpcode;
    }

    public void setLpcode(String lpcode) {
        this.lpcode = lpcode;
    }

    public String getExpcode() {
        return expcode;
    }

    public void setExpcode(String expcode) {
        this.expcode = expcode;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode2() {
        return orderCode2;
    }

    public void setOrderCode2(String orderCode2) {
        this.orderCode2 = orderCode2;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public BigDecimal getPostFee() {
        return postFee;
    }

    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    public BigDecimal getQtyBig() {
        return qtyBig;
    }

    public void setQtyBig(BigDecimal qtyBig) {
        this.qtyBig = qtyBig;
    }

    public BigDecimal getFob() {
        return fob;
    }

    public void setFob(BigDecimal fob) {
        this.fob = fob;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public Integer getNum() {
        return num;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getKeyProperties() {
        return keyProperties;
    }

    public void setKeyProperties(String keyProperties) {
        this.keyProperties = keyProperties;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getResultShow() {
        return ResultShow;
    }

    public void setResultShow(String resultShow) {
        ResultShow = resultShow;
    }

    public String getIsLandTransStr() {
        return isLandTransStr;
    }

    public void setIsLandTransStr(String isLandTransStr) {
        this.isLandTransStr = isLandTransStr;
    }

    public String getRecommendTransName() {
        return recommendTransName;
    }

    public void setRecommendTransName(String recommendTransName) {
        this.recommendTransName = recommendTransName;
    }

    public String getIsPurchaseCycleStr() {
        return isPurchaseCycleStr;
    }

    public void setIsPurchaseCycleStr(String isPurchaseCycleStr) {
        this.isPurchaseCycleStr = isPurchaseCycleStr;
    }

    public Date getEstArrivalTimeBegin() {
        return estArrivalTimeBegin;
    }

    public void setEstArrivalTimeBegin(Date estArrivalTimeBegin) {
        this.estArrivalTimeBegin = estArrivalTimeBegin;
    }

    public Date getEstArrivalTimeEnd() {
        return estArrivalTimeEnd;
    }

    public void setEstArrivalTimeEnd(Date estArrivalTimeEnd) {
        this.estArrivalTimeEnd = estArrivalTimeEnd;
    }

    public String getEstArrivalTimeBeginStr() {
        return estArrivalTimeBeginStr;
    }

    public void setEstArrivalTimeBeginStr(String estArrivalTimeBeginStr) {
        this.estArrivalTimeBeginStr = estArrivalTimeBeginStr;
    }

    public String getEstArrivalTimeEndStr() {
        return estArrivalTimeEndStr;
    }

    public void setEstArrivalTimeEndStr(String estArrivalTimeEndStr) {
        this.estArrivalTimeEndStr = estArrivalTimeEndStr;
    }

    public Integer getIsPurchaseCycleInt() {
        return isPurchaseCycleInt;
    }

    public void setIsPurchaseCycleInt(Integer isPurchaseCycleInt) {
        this.isPurchaseCycleInt = isPurchaseCycleInt;
    }

    public String getIsPreSalesStr() {
        return isPreSalesStr;
    }

    public void setIsPreSalesStr(String isPreSalesStr) {
        this.isPreSalesStr = isPreSalesStr;
    }

    public Integer getIsPreSalesInt() {
        return isPreSalesInt;
    }

    public void setIsPreSalesInt(Integer isPreSalesInt) {
        this.isPreSalesInt = isPreSalesInt;
    }

    public String getIsCreditOrderStr() {
        return isCreditOrderStr;
    }

    public void setIsCreditOrderStr(String isCreditOrderStr) {
        this.isCreditOrderStr = isCreditOrderStr;
    }

    public Integer getIsCreditOrderInt() {
        return isCreditOrderInt;
    }

    public void setIsCreditOrderInt(Integer isCreditOrderInt) {
        this.isCreditOrderInt = isCreditOrderInt;
    }

    public String getShipmentSkuCode() {
        return shipmentSkuCode;
    }

    public void setShipmentSkuCode(String shipmentSkuCode) {
        this.shipmentSkuCode = shipmentSkuCode;
    }

    public Integer getShipmentSkuQty() {
        return shipmentSkuQty;
    }

    public void setShipmentSkuQty(Integer shipmentSkuQty) {
        this.shipmentSkuQty = shipmentSkuQty;
    }

    public String getShipmentErrorMsg() {
        return shipmentErrorMsg;
    }

    public void setShipmentErrorMsg(String shipmentErrorMsg) {
        this.shipmentErrorMsg = shipmentErrorMsg;
    }

    public Date getShipmentCreateTime() {
        return shipmentCreateTime;
    }

    public void setShipmentCreateTime(Date shipmentCreateTime) {
        this.shipmentCreateTime = shipmentCreateTime;
    }

    public String getInvoiceTypeStr() {
        return invoiceTypeStr;
    }

    public void setInvoiceTypeStr(String invoiceTypeStr) {
        this.invoiceTypeStr = invoiceTypeStr;
    }

    public Integer getTransServiceType() {
        return transServiceType;
    }

    public void setTransServiceType(Integer transServiceType) {
        this.transServiceType = transServiceType;
    }

    public Integer getTransTimeType() {
        return transTimeType;
    }

    public void setTransTimeType(Integer transTimeType) {
        this.transTimeType = transTimeType;
    }

    public List<PlatformPromotion> getSoPlatformPromotionList() {
        return soPlatformPromotionList;
    }

    public void setSoPlatformPromotionList(
            List<PlatformPromotion> soPlatformPromotionList) {
        this.soPlatformPromotionList = soPlatformPromotionList;
    }

    public Boolean getIsNotToMergerSoLine() {
        return isNotToMergerSoLine;
    }

    public void setIsNotToMergerSoLine(Boolean isNotToMergerSoLine) {
        this.isNotToMergerSoLine = isNotToMergerSoLine;
    }

    public String getPlatformCreateTimeStr() {
        return platformCreateTimeStr;
    }

    public void setPlatformCreateTimeStr(String platformCreateTimeStr) {
        this.platformCreateTimeStr = platformCreateTimeStr;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getRequestedQty() {
        return requestedQty;
    }

    public void setRequestedQty(BigDecimal requestedQty) {
        this.requestedQty = requestedQty;
    }

    public Boolean getIsForbidOffline() {
        return isForbidOffline;
    }

    public void setIsForbidOffline(Boolean isForbidOffline) {
        this.isForbidOffline = isForbidOffline;
    }

    public BigDecimal getRtnPlatformPointAmt() {
        return rtnPlatformPointAmt;
    }

    public void setRtnPlatformPointAmt(BigDecimal rtnPlatformPointAmt) {
        this.rtnPlatformPointAmt = rtnPlatformPointAmt;
    }

    public BigDecimal getNoRefundAmt() {
        return noRefundAmt;
    }

    public void setNoRefundAmt(BigDecimal noRefundAmt) {
        this.noRefundAmt = noRefundAmt;
    }
    public Date getStartPlatformPaymentTime() {
        return startPlatformPaymentTime;
    }

    public void setStartPlatformPaymentTime(Date startPlatformPaymentTime) {
        this.startPlatformPaymentTime = startPlatformPaymentTime;
    }

    public Integer getSoInvoiceTaxNikeType() {
        return soInvoiceTaxNikeType;
    }

    public void setSoInvoiceTaxNikeType(Integer soInvoiceTaxNikeType) {
        this.soInvoiceTaxNikeType = soInvoiceTaxNikeType;
    }

    public BigDecimal getLineUnitPrice() {
        return lineUnitPrice;
    }

    public void setLineUnitPrice(BigDecimal lineUnitPrice) {
        this.lineUnitPrice = lineUnitPrice;
    }

    public Boolean getIgnoreDefaultInvoiceApply() {
        return ignoreDefaultInvoiceApply;
    }

    public void setIgnoreDefaultInvoiceApply(Boolean ignoreDefaultInvoiceApply) {
        this.ignoreDefaultInvoiceApply = ignoreDefaultInvoiceApply;
    }

    public List<SalesOrderLineCommand> getSoLineCommandList(){
        return soLineCommandList;
    }

    public void setSoLineCommandList(List<SalesOrderLineCommand> soLineCommandList){
        this.soLineCommandList = soLineCommandList;
    }

    public Map<String, Object> soInfoMap() {
        Map<String, Object> m = new HashMap<String, Object>();
        if (StringUtils.hasText(this.getOmsOrderCode())) {
            m.put("omsOrderCode", this.getOmsOrderCode() + "%");
        }
        if (StringUtils.hasText(this.getPlatformOrderCode())) {
            m.put("platformOrderCode", this.getPlatformOrderCode() + "%");
        }
        if (null != this.getIsWlbOrderInt()) {
            m.put("isWlbOrderInt", this.getIsWlbOrderInt().intValue());
        }
        if (null != this.getPaymentStatusInt()) {
            m.put("paymentStatusInt", this.getPaymentStatusInt().intValue());
        }
        if (null != this.getStartCreateTime()) {
            m.put("startCreateTime", this.getStartCreateTime());
        }
        if (null != this.getEndCreateTime()) {
            m.put("endCreateTime", this.getEndCreateTime());
        }
        if (null != this.getStartPlatformPaymentTime()) {
            m.put("startPlatformPaymentTime", this.getStartPlatformPaymentTime());
        }
        if (null != this.getEndPlatformPaymentTime()) {
            m.put("endPlatformPaymentTime", this.getEndPlatformPaymentTime());
        }
        if (null != this.getStartSureTime()) {
            m.put("startSureTime", this.getStartSureTime());
        }
        if (null != this.getEndSureTime()) {
            m.put("endSureTime", this.getEndSureTime());
        }
        //审批时间
        if (null != this.getStartApproveTime()) {
            m.put("startApproveTime", this.getStartApproveTime());
        }
        if (null != this.getEndApproveTime()) {
            m.put("endApproveTime", this.getEndApproveTime());
        }
        if (null != this.getStartDelieveryTime()) {
            m.put("startDelieveryTime", this.getStartDelieveryTime());
        }
        if (null != this.getEndDelieveryTime()) {
            m.put("endDelieveryTime", this.getEndDelieveryTime());
        }
//        if (StringUtils.hasText(this.getCode())) {
//            m.put("code", this.getCode() + "%");
//        }
//        if (StringUtils.hasText(this.getOuterOrderCode())) {
//            m.put("outerOrderCode", this.getOuterOrderCode() + "%");
//        }
//        if (StringUtils.hasText(this.getRootSoCode())) {
//            m.put("rootSoCode", this.getRootSoCode() + "%");
//        }

        if (StringUtils.hasText(this.getProductCode())) {
            m.put("productCode", this.getProductCode() + "%");
        }
        if (StringUtils.hasText(this.getProductName())) {
            m.put("productName", this.getProductName() + "%");
        }
        if (StringUtils.hasText(this.getSupplierCode())) {
            m.put("supplierCode", this.getSupplierCode() + "%");
        }
        if (StringUtils.hasText(this.getReceiver())) {
            m.put("receiver", this.getReceiver() + "%");
        }
        if (StringUtils.hasText(this.getReceiverMobile())) {
            m.put("receiverMobile", this.getReceiverMobile() + "%");
        }
        if (StringUtils.hasText(this.getReceiverPhone())) {
            m.put("receiverPhone", this.getReceiverPhone() + "%");
        }
        if (StringUtils.hasText(this.getTransCode())) {
            m.put("transCode", this.getTransCode() + "%");
        }
        if (StringUtils.hasText(this.getTransName())) {
            m.put("transName", this.getTransName() + "%");
        }
        if (null != this.getStatusInt()) {
            m.put("statusInt", this.getStatusInt());
        }
        if (null != this.getOrderTypeInt()) {
            m.put("orderTypeInt", this.getOrderTypeInt());
        }
//        if (null != this.getPaymentType()) {
//            m.put("paymentType", this.getPaymentType().toString());
//        }
//        if (null != this.getShopId()) {
//            m.put("shopId", this.getShopId());
//        }
        if (null != this.getDivisionId()) {
            m.put("divisionId", this.getDivisionId());
        }
        if (null != this.getFinanceStatusInteger()) {
            m.put("financeStatusInteger", this.getFinanceStatusInteger());
        }
        if (null != this.getGoodsTypeInt()) {
            m.put("goodsTypeInt", this.getGoodsTypeInt());
        }
        if (null != this.getIsNeedInvoiceInt()) {
            m.put("isNeedInvoiceInt", this.getIsNeedInvoiceInt());
        }
        if (null != this.getTransportId()) {
            m.put("TransportId", this.getTransportId());
        }
        if (null != this.getIsO2oShop()) {
            m.put("isO2oShop", this.getIsO2oShop());
        }
        if (StringUtils.hasText(this.getK3CustomerCode())) {
            m.put("k3CustomerCode", this.getK3CustomerCode() + "%");
        }
        if (StringUtils.hasText(this.getCurrentNodeNo())) {
            m.put("currentNodeNo", this.getCurrentNodeNo());
        }
        if (StringUtils.hasText(this.getK3CustomerName())) {
            m.put("k3CustomerName", this.getK3CustomerName() + "%");
        }
        if (null != this.getIsPurchaseCycleInt()) {
            m.put("isPurchaseCycleInt", this.getIsPurchaseCycleInt());
        }
        if (null != this.getIsPreSalesInt()) {
            m.put("isPreSalesInt", this.getIsPreSalesInt());
        }
        if (null != this.getEstArrivalTimeBegin()) {
            m.put("estArrivalTimeBegin", this.getEstArrivalTimeBegin());
        }
        if (null != this.getEstArrivalTimeEnd()) {
            m.put("estArrivalTimeEnd", this.getEstArrivalTimeEnd());
        }
        if (null != this.getIsCreditOrderInt()) {
            m.put("isCreditOrderInt", this.getIsCreditOrderInt());
        }
        if (null != this.getPaymentStatusInt()) {
            m.put("paymentStatusInt", this.getPaymentStatusInt().intValue());
        }
//        if (!StringUtil.isEmpty(this.getRefSoCode())) {
//            m.put("refSoCode", this.getRefSoCode());
//        }
//        if (!StringUtil.isEmpty(this.getRefOocCode())) {
//            m.put("refOocCode", this.getRefOocCode());
//        }
        if (null != this.getSoInvoiceTaxNikeType()) {
            m.put("soInvoiceTaxNikeType", this.getSoInvoiceTaxNikeType());
        }
        return m;
    }

    public OrderMember getOrderMember(){
        return orderMember;
    }

    public void setOrderMember(OrderMember orderMember){
        this.orderMember = orderMember;
    }

    public boolean isSoMoreAddress() {
        return soMoreAddress;
    }

    public void setSoMoreAddress(boolean soMoreAddress) {
        this.soMoreAddress = soMoreAddress;
    }

    public String getIsPresaleStr() {
        return isPresaleStr;
    }

    public void setIsPresaleStr(String isPresaleStr) {
        this.isPresaleStr = isPresaleStr;
    }

    public String getPlatformOrderStatus(){
        return platformOrderStatus;
    }

    public void setPlatformOrderStatus(String platformOrderStatus){
        this.platformOrderStatus = platformOrderStatus;
    }

    public List<SoServiceLineCommand> getSoServiceLineCmdList() {
        return soServiceLineCmdList;
    }

    public void setSoServiceLineCmdList(List<SoServiceLineCommand> soServiceLineCmdList) {
        this.soServiceLineCmdList = soServiceLineCmdList;
    }

    public Integer getSpecialTypeInt() {
        return specialTypeInt;
    }

    public void setSpecialTypeInt(Integer specialTypeInt) {
        this.specialTypeInt = specialTypeInt;
    }

    public String getSoSpecialTypeStr() {
        return soSpecialTypeStr;
    }

    public void setSoSpecialTypeStr(String soSpecialTypeStr) {
        this.soSpecialTypeStr = soSpecialTypeStr;
    }

    public String getInvoiceKindStr() {
        return invoiceKindStr;
    }

    public void setInvoiceKindStr(String invoiceKindStr) {
        this.invoiceKindStr = invoiceKindStr;
    }
}
