package io.renren.modules.coinInfo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-03 13:45:51
 */
public class CoinInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; //
    private String nameCN; // 币中文名
    private String nameEN; // 币英文名
    private String coinLogo; // 币logo
    private BigDecimal coinPrice; // 单价币值
    private Date createdAt; //
    private String remark; //
    private Integer sort = 0; //
    private Integer state; //
    private Date updatedAt; //
    private Integer version; // 并发版本控制

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCN() {
        return nameCN;
    }

    public void setNameCN(String nameCN) {
        this.nameCN = nameCN;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getCoinLogo() {
        return coinLogo;
    }

    public void setCoinLogo(String coinLogo) {
        this.coinLogo = coinLogo;
    }

    public BigDecimal getCoinPrice() {
        return coinPrice;
    }

    public void setCoinPrice(BigDecimal coinPrice) {
        this.coinPrice = coinPrice;
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
}
