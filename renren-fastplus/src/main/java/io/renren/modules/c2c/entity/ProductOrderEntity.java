package io.renren.modules.c2c.entity;

import io.renren.modules.product.entity.ProductEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-09 20:18:01
 */
public class ProductOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; //
    private String name; // 姓名
    //    private Long productId; // 产品id
//    private Long volunteerId; // 义工id
    private BigDecimal totalAmount; // 总金额
    private BigDecimal price; // 价格
    private BigDecimal number; // 数量
    private String priceUnit; // 价格单位
    private Date createdAt; //
    private Date updatedAt; //
    private String remark; //
    private Integer state; //
    private Integer sort = 0; //

    private ProductEntity product;
    private VolunteerEntity volunteer;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getPriceUnit() {
        return priceUnit;
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

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public VolunteerEntity getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(VolunteerEntity volunteer) {
        this.volunteer = volunteer;
    }
}
