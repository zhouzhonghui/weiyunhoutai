package io.renren.modules.c2c.entity;

import io.renren.modules.member.entity.MemberEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-04 16:49:51
 */
public class C2cOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 订单状态
    public enum State {
        prePay(0, "待支付"), overtime(1, "支付超时"), payed(2, "支付完成"), del(9, "删除"), toConfirm(11, "待确认");
        private int value;
        private String desc;

        State(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return this.value;
        }

        public String getDesc() {
            return desc;
        }

        public static State findByValue(int value) {
            State state = null;

            for (State g : State.values()) {
                if (g.getValue() == value) {
                    state = g;
                    break;
                }
            }

            return state;
        }

        public static State findByDesc(String desc) {
            State state = null;
            for (State g : State.values()) {
                if (g.getDesc().equals(desc)) {
                    state = g;
                    break;
                }
            }
            return state;
        }

        public static State findByName(String name) {
            State state = null;

            for (State g : State.values()) {
                if (g.name().equals(name)) {
                    state = g;
                    break;
                }
            }

            return state;
        }
    }

    // 支付方式
    public enum PaymentMethod {
        bankpay("银行卡"), weixinpay("微信支付"), alipay("支付宝");

        private String desc;

        PaymentMethod(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public static PaymentMethod findByName(String name) {
            PaymentMethod paymentMethod = null;
            for (PaymentMethod pm : PaymentMethod.values()) {
                if (pm.name().equals(name)) {
                    paymentMethod = pm;
                    break;
                }
            }
            return paymentMethod;
        }

    }

    private Long id; //
    private Long orderId; // 相互保障订单id
    private String orderNo; // 相互保障订单No
    private Long volunteerId; // 义工id
    private String volunteerName; // 义工姓名
    private String additionalCode; // 附加码
    private BigDecimal totalAmount; // 用户支付金额
    private BigDecimal price; // 币价格
    private BigDecimal number; // 币数量
    private Long paymentMethodId; // 支付方式id
    private String paymentName; // 支付方式
    private Date createdAt; // 创建时间
    private Date updatedAt; // 更新时间
    private String remark; //
    private Integer state; // 状态
    private String stateDesc;
    private Integer sort = 0; //
//    private Long productOrderId; // 挂单ID
    private String c2cOrderNo; // C2C订单编号
    private Integer version; //
    private Date payOverTime; // 支付超时时间
    private Date payedTime; // 支付时间
    private String transactionHash;//交易hash

    private MemberEntity member;
    private ProductOrderEntity productOrder;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setAdditionalCode(String additionalCode) {
        this.additionalCode = additionalCode;
    }

    public String getAdditionalCode() {
        return additionalCode;
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

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentName() {
        return paymentName;
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

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setC2cOrderNo(String c2cOrderNo) {
        this.c2cOrderNo = c2cOrderNo;
    }

    public String getC2cOrderNo() {
        return c2cOrderNo;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public void setPayOverTime(Date payOverTime) {
        this.payOverTime = payOverTime;
    }

    public Date getPayOverTime() {
        return payOverTime;
    }

    public Date getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(Date payedTime) {
        this.payedTime = payedTime;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public ProductOrderEntity getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProductOrderEntity productOrder) {
        this.productOrder = productOrder;
    }
}
