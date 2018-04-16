package io.renren.modules.c2c.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-04 15:05:52
 */
public class VolunteerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 在线状态
    public enum OnLine {
        onLine(1, "在线"), offLine(0, "离线");
        private int value;
        private String desc;

        OnLine(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public static OnLine findByValue(int value) {
            OnLine state = null;

            for (OnLine g : OnLine.values()) {
                if (g.getValue() == value) {
                    state = g;
                    break;
                }
            }

            return state;
        }

        public static OnLine findByDesc(String desc) {
            OnLine state = null;
            for (OnLine g : OnLine.values()) {
                if (g.getDesc().equals(desc)) {
                    state = g;
                    break;
                }
            }
            return state;
        }

        public static OnLine findByName(String name) {
            OnLine state = null;

            for (OnLine g : OnLine.values()) {
                if (g.name().equals(name)) {
                    state = g;
                    break;
                }
            }

            return state;
        }
    }

    private Long id; //
    private String name; // 姓名
    private Integer online; // 在线状态1在线，0离开
    private String onlineDesc;
    private Integer deposit; // 是否缴纳保障金，0未缴，1缴纳
    private Long managerId; // 管理员id
    private Date createdAt; //
    private Date updatedAt; //
    private String remark; //
    private Integer state; //
    private Integer sort = 0; //
    private BigDecimal amount; //
    private String fromAddress;
    private Integer completeNumber;
    private String completeRate;
    private String mobile;
    private String walletPath;
    private String amountTransactionHash;
    private String walletPwd;//钱包密码

    private PaymentMethodEntity bankPay;
    private PaymentMethodEntity weixinPay;
    private PaymentMethodEntity aliPay;

    private List<PaymentMethodEntity> paymentMethodList;

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

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getOnline() {
        return online;
    }

    public String getOnlineDesc() {
        return onlineDesc;
    }

    public void setOnlineDesc(String onlineDesc) {
        this.onlineDesc = onlineDesc;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getManagerId() {
        return managerId;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<PaymentMethodEntity> getPaymentMethodList() {
        return paymentMethodList;
    }

    public void setPaymentMethodList(List<PaymentMethodEntity> paymentMethodList) {
        this.paymentMethodList = paymentMethodList;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Integer getCompleteNumber() {
        return completeNumber;
    }

    public void setCompleteNumber(Integer completeNumber) {
        this.completeNumber = completeNumber;
    }

    public String getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(String completeRate) {
        this.completeRate = completeRate;
    }

    public PaymentMethodEntity getBankPay() {
        return bankPay;
    }

    public void setBankPay(PaymentMethodEntity bankPay) {
        this.bankPay = bankPay;
    }

    public PaymentMethodEntity getWeixinPay() {
        return weixinPay;
    }

    public void setWeixinPay(PaymentMethodEntity weixinPay) {
        this.weixinPay = weixinPay;
    }

    public PaymentMethodEntity getAliPay() {
        return aliPay;
    }

    public void setAliPay(PaymentMethodEntity aliPay) {
        this.aliPay = aliPay;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWalletPath() {
        return walletPath;
    }

    public void setWalletPath(String walletPath) {
        this.walletPath = walletPath;
    }

    public String getAmountTransactionHash() {
        return amountTransactionHash;
    }

    public void setAmountTransactionHash(String amountTransactionHash) {
        this.amountTransactionHash = amountTransactionHash;
    }

    public String getWalletPwd() {
        return walletPwd;
    }

    public void setWalletPwd(String walletPwd) {
        this.walletPwd = walletPwd;
    }
}
