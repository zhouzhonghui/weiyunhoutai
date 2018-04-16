package io.renren.modules.policy.entity;


import io.renren.modules.coinInfo.entity.CoinInfoEntity;
import io.renren.modules.member.entity.MemberEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-31 14:41:28
 */
public class PolicyAccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String pwd; //加密后存储
    private Integer sort = 0;
    private Integer state;
    private String remark;
    private Date createdAt;
    private Date updatedAt;
    private Integer version; // 并发版本控制
    private String privateKey; // 加密后存储
    private String accountAddress; //币账户地址
    private BigDecimal freezeAmount; // 冻结金额
    private BigDecimal availableAmount; // 可用金额

    private MemberEntity member;
    private CoinInfoEntity coinInfo;
    private LifePolicyEntity lifePolicy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LifePolicyEntity getLifePolicy() {
        return lifePolicy;
    }

    public void setLifePolicy(LifePolicyEntity lifePolicy) {
        this.lifePolicy = lifePolicy;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public CoinInfoEntity getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfoEntity coinInfo) {
        this.coinInfo = coinInfo;
    }
}
