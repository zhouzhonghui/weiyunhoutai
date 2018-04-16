package io.renren.modules.order.entity;


import io.renren.modules.coinInfo.entity.CoinInfoEntity;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.policy.entity.LifePolicyEntity;
import io.renren.modules.product.entity.ProductEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-31 13:53:24
 */
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 订单类型
    public enum OrderType {
        insuranceOrder(0, "车险订单"), giveToRedPackage(1, "购买的红包"), receiveRedPackage(2, "领取的红包"), lifeInsureOrder(3, "寿险订单"), goodOrder(4, "实物订单"), mutualOrder(5, "互助保险订单"), mutualRecharge(6, "凭证充值订单");
        private int value;
        private String desc;

        OrderType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public int getValue() {
            return value;
        }

        public static OrderType findByDesc(String desc) {
            OrderType type = null;
            for (OrderType g : OrderType.values()) {
                if (g.getDesc().equals(desc)) {
                    type = g;
                    break;
                }
            }
            return type;
        }

        public static OrderType findByValue(int vaule) {
            OrderType type = null;
            for (OrderType g : OrderType.values()) {
                if (g.getValue() == vaule) {
                    type = g;
                    break;
                }
            }
            return type;
        }
    }

    // 支付状态
    public enum State {
        prePay(0, "待支付"), overtime(1, "支付超时"), payed(2, "已支付"), refund(3, "退款完成"), finish(4, "已完成"), cancel(6, "已取消"),ygcancel(7, "已取消"), refundIn(10, "撤单退款中"), toConfirm(11, "待确认");

        private String desc;
        private int value;

        State(int value, String desc) {
            this.desc = desc;
            this.value = value;
        }

        public String getDesc() {
            return desc;
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

        public int getValue() {
            return this.value;
        }
    }

    private Long coinInfoId;
    private Long id;
    private Date createdAt;
    private String remark;
    private Integer sort = 0;
    private Integer state; // 0:待付款,1:支付超时,2:已支付,3:退款完成,4:已完成,10:撤单退款中
    private String stateDesc;
    private Date updatedAt;
    private MemberEntity member;
    private ProductEntity product;
    private LifePolicyEntity lifePolicy;
    private CoinInfoEntity coinInfo;
    private BigDecimal accruedMoney;
    private BigDecimal actualMoney;
    private String orderNo;
    private String refuelOrderNo; // 关联的加油订单
    private Integer status; // 0:未处理 1:处理中 2:已完成
    private String statusDesc;
    private String invoice;
    private Long memberAddressId;
    private Date paidTime;
    private String paymentName;
    private Date formalTime;
    private Date receiveTime;
    private Long formalerId;
    private Long invoiceId; // 发票信息id
    private String packageType;
    private Integer orderType; // 订单类型 0:车险订单 1：购买红包 3：领取的红包 3寿险订单
    private String orderTypeDesc;
    private String shareProduct; // 分享产品名
    private String sharePeople; // 分享人姓名
    private String channel; // 渠道来源
    private String orderFrom; // 订单来源
    private String ageCode; // 安联年龄阶段code
    private String shareMes; // 分享留言
    private String device; // 设备pc wap ios android
    private String platform; // 平台 微云保贝 微云车险业务员 保贝加油
    private BigDecimal shipFee; // 运费
    private String validationCode; // 短信验证码
    private String inviteCode; // 订单上级邀请码
    private Integer version; // 并发版本控制
    private String fromAddress;
    private String toAddress;
    private String transactionHash; // 区块上交易hash
    private Date payEffectiveTime; // 支付有效时间

    public Long getCoinInfoId() {
        return coinInfoId;
    }

    public void setCoinInfoId(Long coinInfoId) {
        this.coinInfoId = coinInfoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public LifePolicyEntity getLifePolicy() {
        return lifePolicy;
    }

    public void setLifePolicy(LifePolicyEntity lifePolicy) {
        this.lifePolicy = lifePolicy;
    }

    public CoinInfoEntity getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfoEntity coinInfo) {
        this.coinInfo = coinInfo;
    }

    public BigDecimal getAccruedMoney() {
        return accruedMoney;
    }

    public void setAccruedMoney(BigDecimal accruedMoney) {
        this.accruedMoney = accruedMoney;
    }

    public BigDecimal getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(BigDecimal actualMoney) {
        this.actualMoney = actualMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRefuelOrderNo() {
        return refuelOrderNo;
    }

    public void setRefuelOrderNo(String refuelOrderNo) {
        this.refuelOrderNo = refuelOrderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Long getMemberAddressId() {
        return memberAddressId;
    }

    public void setMemberAddressId(Long memberAddressId) {
        this.memberAddressId = memberAddressId;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public Date getFormalTime() {
        return formalTime;
    }

    public void setFormalTime(Date formalTime) {
        this.formalTime = formalTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Long getFormalerId() {
        return formalerId;
    }

    public void setFormalerId(Long formalerId) {
        this.formalerId = formalerId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeDesc() {
        return orderTypeDesc;
    }

    public void setOrderTypeDesc(String orderTypeDesc) {
        this.orderTypeDesc = orderTypeDesc;
    }

    public String getShareProduct() {
        return shareProduct;
    }

    public void setShareProduct(String shareProduct) {
        this.shareProduct = shareProduct;
    }

    public String getSharePeople() {
        return sharePeople;
    }

    public void setSharePeople(String sharePeople) {
        this.sharePeople = sharePeople;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getAgeCode() {
        return ageCode;
    }

    public void setAgeCode(String ageCode) {
        this.ageCode = ageCode;
    }

    public String getShareMes() {
        return shareMes;
    }

    public void setShareMes(String shareMes) {
        this.shareMes = shareMes;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public BigDecimal getShipFee() {
        return shipFee;
    }

    public void setShipFee(BigDecimal shipFee) {
        this.shipFee = shipFee;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public Date getPayEffectiveTime() {
        return payEffectiveTime;
    }

    public void setPayEffectiveTime(Date payEffectiveTime) {
        this.payEffectiveTime = payEffectiveTime;
    }
}
