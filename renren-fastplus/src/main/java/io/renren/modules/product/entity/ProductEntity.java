package io.renren.modules.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-10 12:22:45
 */
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; //
    private Date createdAt; //
    private Date updatedAt; //
    private String remark; //
    private Integer sort; //
    private Integer state; //
    private String code; //
    private String description; //
    private String image; //
    private String name; //
    private BigDecimal marketPrice; // 市场价
    private BigDecimal price; // 微云价
    private Long insuranceCategoryId; // 产品类型
    private Long insuranceCompanyId; // 承保公司
    private Long organizationId; // 组织机构
    private String productSpec; // 产品描述
    private String specName; //
    private Long specId; //
    private String shareTile; // 分享标题
    private String shareDesc; // 分享描述
    private String shareLink; // 分享链接
    private String shareImgUrl; // 分享图片地址
    private Integer validDays; // 有效期
    private String plancode; //
    private String rate; //
    private String suminsured; //
    private String rangedate; //
    private String commissionratio; //
    private String kindcode; //
    private String kindind; //
    private String shortrate; //
    private String grosspremium; //
    private String maxSuminsured; //
    private String imageName; //
    private Long parentId; // 产品父类字段
    private Integer isHotSale; // 热销产品标识
    private Long couponId; // 优惠券红包产品优惠券id
    private Integer obDays; // 观察期
    private String serviceBeanName; // 产品出单时业务方法bean
    private String remarkEn; //
    private String remarkKr; //
    private String imageEn; //
    private String imageKr; //
    private String imageJp; //
    private String nameEn; //
    private String nameKr; //
    private String nameJp; //
    private String tokenAddress; // 产品token地址
    private String remarkJp; //
    private BigDecimal equivalence; // 等价值多少
    private Integer overDate; //

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

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
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

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setInsuranceCategoryId(Long insuranceCategoryId) {
        this.insuranceCategoryId = insuranceCategoryId;
    }

    public Long getInsuranceCategoryId() {
        return insuranceCategoryId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setShareTile(String shareTile) {
        this.shareTile = shareTile;
    }

    public String getShareTile() {
        return shareTile;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl;
    }

    public String getShareImgUrl() {
        return shareImgUrl;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setPlancode(String plancode) {
        this.plancode = plancode;
    }

    public String getPlancode() {
        return plancode;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public void setSuminsured(String suminsured) {
        this.suminsured = suminsured;
    }

    public String getSuminsured() {
        return suminsured;
    }

    public void setRangedate(String rangedate) {
        this.rangedate = rangedate;
    }

    public String getRangedate() {
        return rangedate;
    }

    public void setCommissionratio(String commissionratio) {
        this.commissionratio = commissionratio;
    }

    public String getCommissionratio() {
        return commissionratio;
    }

    public void setKindcode(String kindcode) {
        this.kindcode = kindcode;
    }

    public String getKindcode() {
        return kindcode;
    }

    public void setKindind(String kindind) {
        this.kindind = kindind;
    }

    public String getKindind() {
        return kindind;
    }

    public void setShortrate(String shortrate) {
        this.shortrate = shortrate;
    }

    public String getShortrate() {
        return shortrate;
    }

    public void setGrosspremium(String grosspremium) {
        this.grosspremium = grosspremium;
    }

    public String getGrosspremium() {
        return grosspremium;
    }

    public void setMaxSuminsured(String maxSuminsured) {
        this.maxSuminsured = maxSuminsured;
    }

    public String getMaxSuminsured() {
        return maxSuminsured;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setIsHotSale(Integer isHotSale) {
        this.isHotSale = isHotSale;
    }

    public Integer getIsHotSale() {
        return isHotSale;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setObDays(Integer obDays) {
        this.obDays = obDays;
    }

    public Integer getObDays() {
        return obDays;
    }

    public void setServiceBeanName(String serviceBeanName) {
        this.serviceBeanName = serviceBeanName;
    }

    public String getServiceBeanName() {
        return serviceBeanName;
    }

    public void setRemarkEn(String remarkEn) {
        this.remarkEn = remarkEn;
    }

    public String getRemarkEn() {
        return remarkEn;
    }

    public void setRemarkKr(String remarkKr) {
        this.remarkKr = remarkKr;
    }

    public String getRemarkKr() {
        return remarkKr;
    }

    public void setImageEn(String imageEn) {
        this.imageEn = imageEn;
    }

    public String getImageEn() {
        return imageEn;
    }

    public void setImageKr(String imageKr) {
        this.imageKr = imageKr;
    }

    public String getImageKr() {
        return imageKr;
    }

    public void setImageJp(String imageJp) {
        this.imageJp = imageJp;
    }

    public String getImageJp() {
        return imageJp;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameKr(String nameKr) {
        this.nameKr = nameKr;
    }

    public String getNameKr() {
        return nameKr;
    }

    public void setNameJp(String nameJp) {
        this.nameJp = nameJp;
    }

    public String getNameJp() {
        return nameJp;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setRemarkJp(String remarkJp) {
        this.remarkJp = remarkJp;
    }

    public String getRemarkJp() {
        return remarkJp;
    }

    public void setEquivalence(BigDecimal equivalence) {
        this.equivalence = equivalence;
    }

    public BigDecimal getEquivalence() {
        return equivalence;
    }

    public void setOverDate(Integer overDate) {
        this.overDate = overDate;
    }

    public Integer getOverDate() {
        return overDate;
    }

}
