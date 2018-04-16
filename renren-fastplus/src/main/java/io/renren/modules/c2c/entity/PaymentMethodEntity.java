package io.renren.modules.c2c.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-06 23:47:47
 */
public class PaymentMethodEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; //
    private String paymentName; // 支付方式 weixinpay("微信"), alipay("支付宝"),bankpay("银行卡");
    private String bankCardName; // 开户行
    private String bankCardNo; // 银行卡号
    private String bankCardAddress; // 银行卡号,支付宝账号
    private String name; // 姓名
    private String payQrcode; // 二维码地址图片地址
    private Long volunteerId; // 义工id
    private Date createdAt; //
    private Date updatedAt; //
    private String remark; //
    private Integer state; //
    private Integer sort = 0; //



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardAddress(String bankCardAddress) {
        this.bankCardAddress = bankCardAddress;
    }

    public String getBankCardAddress() {
        return bankCardAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPayQrcode(String payQrcode) {
        this.payQrcode = payQrcode;
    }

    public String getPayQrcode() {
        return payQrcode;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public Long getVolunteerId() {
        return volunteerId;
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

}
