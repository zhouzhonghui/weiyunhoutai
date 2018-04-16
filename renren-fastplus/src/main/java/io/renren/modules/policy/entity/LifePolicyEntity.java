package io.renren.modules.policy.entity;

import io.renren.modules.coinInfo.entity.CoinInfoEntity;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.product.entity.ProductEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-02 16:19:08
 */
public class LifePolicyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; //
    private Date createdAt; //
    private String remark; //
    private Integer sort = 0; //
    private Integer state; //
    private Date updatedAt; //
    private BigDecimal baseInsuranceAmount; //
    private Date insuranceEndDate; //
    private BigDecimal insurancePrice; //
    private Date insuranceStartDate; //
    private Integer insuranceTimeLimit; //
    private String insuredAge; //
    private String insuredNo; //
    private Integer paymentMethods; //
    private String policyNo; //
    private Integer quantity; //
    private Long beneficiaryId; //
    private Long holderPeopleId; //
    private Long insuredPeopleId; //
    private Long sourceOrderId; //
    private Long orderId; //
    private String hospital; // 就诊医院
    private String itemName; // 就诊项目
    private Date optDate; // 手术日期
    private String insuredRemark; // 第三方保险公司核保结果信息
    private Integer insurePayWay; // 缴别（缴纳的年数）
    private Date insureTime; // 投保时间
    private Date underwriteTime; // 保险公司承保时间
    private String durationPeriodValue; // 保期值
    private String bankCode; // 银行编码
    private String accNo; // 银行卡号
    private String secretKey; //
    private String insuredPeopleCard; // 被保险人身份证号码
    private String insuredPeopleName; // 被保险人姓名
    private Long countryId; //
    private String ucName; //
    private String ucMobile; //
    private String ucEmail; //
    private Long ucCountryId; //
    private String nationCode; //

    private MemberEntity member;
    private ProductEntity product;
    private PolicyAccountEntity policyAccount;
    private CoinInfoEntity coinInfo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setBaseInsuranceAmount(BigDecimal baseInsuranceAmount) {
        this.baseInsuranceAmount = baseInsuranceAmount;
    }

    public BigDecimal getBaseInsuranceAmount() {
        return baseInsuranceAmount;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsurancePrice(BigDecimal insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public BigDecimal getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceTimeLimit(Integer insuranceTimeLimit) {
        this.insuranceTimeLimit = insuranceTimeLimit;
    }

    public Integer getInsuranceTimeLimit() {
        return insuranceTimeLimit;
    }

    public void setInsuredAge(String insuredAge) {
        this.insuredAge = insuredAge;
    }

    public String getInsuredAge() {
        return insuredAge;
    }

    public void setInsuredNo(String insuredNo) {
        this.insuredNo = insuredNo;
    }

    public String getInsuredNo() {
        return insuredNo;
    }

    public void setPaymentMethods(Integer paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public Integer getPaymentMethods() {
        return paymentMethods;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setHolderPeopleId(Long holderPeopleId) {
        this.holderPeopleId = holderPeopleId;
    }

    public Long getHolderPeopleId() {
        return holderPeopleId;
    }

    public void setInsuredPeopleId(Long insuredPeopleId) {
        this.insuredPeopleId = insuredPeopleId;
    }

    public Long getInsuredPeopleId() {
        return insuredPeopleId;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public PolicyAccountEntity getPolicyAccount() {
        return policyAccount;
    }

    public void setPolicyAccount(PolicyAccountEntity policyAccount) {
        this.policyAccount = policyAccount;
    }

    public void setSourceOrderId(Long sourceOrderId) {
        this.sourceOrderId = sourceOrderId;
    }

    public Long getSourceOrderId() {
        return sourceOrderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospital() {
        return hospital;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setInsuredRemark(String insuredRemark) {
        this.insuredRemark = insuredRemark;
    }

    public String getInsuredRemark() {
        return insuredRemark;
    }

    public void setInsurePayWay(Integer insurePayWay) {
        this.insurePayWay = insurePayWay;
    }

    public Integer getInsurePayWay() {
        return insurePayWay;
    }

    public void setInsureTime(Date insureTime) {
        this.insureTime = insureTime;
    }

    public Date getInsureTime() {
        return insureTime;
    }

    public void setUnderwriteTime(Date underwriteTime) {
        this.underwriteTime = underwriteTime;
    }

    public Date getUnderwriteTime() {
        return underwriteTime;
    }

    public void setDurationPeriodValue(String durationPeriodValue) {
        this.durationPeriodValue = durationPeriodValue;
    }

    public String getDurationPeriodValue() {
        return durationPeriodValue;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setInsuredPeopleCard(String insuredPeopleCard) {
        this.insuredPeopleCard = insuredPeopleCard;
    }

    public String getInsuredPeopleCard() {
        return insuredPeopleCard;
    }

    public void setInsuredPeopleName(String insuredPeopleName) {
        this.insuredPeopleName = insuredPeopleName;
    }

    public String getInsuredPeopleName() {
        return insuredPeopleName;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setUcName(String ucName) {
        this.ucName = ucName;
    }

    public String getUcName() {
        return ucName;
    }

    public void setUcMobile(String ucMobile) {
        this.ucMobile = ucMobile;
    }

    public String getUcMobile() {
        return ucMobile;
    }

    public void setUcEmail(String ucEmail) {
        this.ucEmail = ucEmail;
    }

    public String getUcEmail() {
        return ucEmail;
    }

    public void setUcCountryId(Long ucCountryId) {
        this.ucCountryId = ucCountryId;
    }

    public Long getUcCountryId() {
        return ucCountryId;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNationCode() {
        return nationCode;
    }

    public CoinInfoEntity getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfoEntity coinInfo) {
        this.coinInfo = coinInfo;
    }
}
