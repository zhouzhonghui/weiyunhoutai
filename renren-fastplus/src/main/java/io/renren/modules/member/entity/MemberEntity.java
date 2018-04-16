package io.renren.modules.member.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-30 13:40:55
 */
public class MemberEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Gender {
        secret(0, "保密"), male(1, "男"), female(2, "女");
        private Integer id;
        private String desc;

        Gender(Integer id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public Integer getId() {
            return id;
        }

        public static Gender findById(Integer id) {
            Gender gender = null;

            for (Gender g : Gender.values()) {
                if (g.getId() == id) {
                    gender = g;
                    break;
                }
            }

            return gender;
        }

        public static Gender findByDesc(String desc) {
            Gender gender = null;

            for (Gender g : Gender.values()) {
                if (g.getDesc().equals(desc)) {
                    gender = g;
                    break;
                }
            }

            return gender;
        }
    }

    public enum FromSource {
        WeiXin(0, "WeiXin"), PC(1, "PC"), Android(2, "Android"), iOS(3, "iOS"), WAP(4, "WAP");
        private Integer id;
        private String desc;

        FromSource (Integer id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public Integer getId() {
            return id;
        }

        public static FromSource findById(Integer id) {
            FromSource fromSource = null;

            for (FromSource f : FromSource.values()) {
                if (f.getId() == id) {
                    fromSource = f;
                    break;
                }
            }

            return fromSource;
        }

        public static FromSource findByDesc(String desc) {
            FromSource fromSource = null;

            for (FromSource f : FromSource.values()) {
                if (f.getDesc().equals(desc)) {
                    fromSource = f;
                    break;
                }
            }

            return fromSource;
        }
    }

    public enum State {
        onLine(1, "有效"), offLine(0, "失效");
        private Integer id;
        private String desc;

        State (Integer id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public Integer getId() {
            return id;
        }

        public static State findById(Integer id) {
            State state = null;

            for (State s : State.values()) {
                if (s.getId() == id) {
                    state = s;
                    break;
                }
            }

            return state;
        }

        public static State findByDesc(String desc) {
            State state = null;

            for (State s : State.values()) {
                if (s.getDesc().equals(desc)) {
                    state = s;
                    break;
                }
            }

            return state;
        }
    }

    public enum Type {
        common("会员"), agency("代理人"), salesman("业务员"), agentOrganization("代理机构");

        private String desc;

        Type(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public static Type findByDesc(String desc) {
            Type type = null;

            for (Type g : Type.values()) {
                if (g.getDesc().equals(desc)) {
                    type = g;
                    break;
                }
            }

            return type;
        }
    }

    private Long id;
    private Date createdAt;
    private String remark;
    private Integer sort = 0;
    private Integer state;
    private String stateDesc;
    private Date updatedAt;
    private String address;
    private Integer age;
    private String avatar;
    private BigDecimal balance;
    private Date birthday;
    private String code;
    private String codeImage;
    private String email;
    private Integer gender;
    private String genderDesc;
    private String idcard;
    private Integer isValidMobile;
    private String lastLoginIp;
    private Date lastLoginTime;
    private String loginIp;
    private Integer loginNum;
    private Date loginTime;
    private String salt;
    private String mobile;
    private String nickname;
    private String password;
    private Integer points;
    private String realname;
    private Integer type;
    private String typeDesc;
    private String username;
    private String weixinOpenId;
    private Long recommendMemberId;
    private Long areaId;
    private Integer isAngel;
    private String inviteCode;
    private Integer isvirtual;
    private String fromSource;
    private Long inviteCodeId;
    private Long organizationId;
    private String department;
    private String channel; //渠道来源
    private String openPlatform; // 开通的平台
    private String mobileProvince; // 手机号码归属地所在省份
    private String mobileCity; // 手机号码归属地所在城市
    private String mobileAreaCode; // 手机号码归属地区号
    private String mobileZip; // 手机号码归属地邮编
    private String mobileCompany; // 手机号码运营商
    private String mobileCard; // 手机卡信息
    private String deviceId; // 设备id
    private String ipAddress; // ip地址
    private String imei; // 移动装备辨识码
    private String idfa; // ios设备唯一标识
    private String mac; // mac地址
    private String androidId; // android设备唯一标识
    private String openClient; // 用户登录过的客户端已","分割
    private String ipOfArea; // ip所属地区
    private Long countryId;
    private String nationCode;
    private String randomToken;

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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeImage() {
        return codeImage;
    }

    public void setCodeImage(String codeImage) {
        this.codeImage = codeImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getIsValidMobile() {
        return isValidMobile;
    }

    public void setIsValidMobile(Integer isValidMobile) {
        this.isValidMobile = isValidMobile;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWeixinOpenId() {
        return weixinOpenId;
    }

    public void setWeixinOpenId(String weixinOpenId) {
        this.weixinOpenId = weixinOpenId;
    }

    public Long getRecommendMemberId() {
        return recommendMemberId;
    }

    public void setRecommendMemberId(Long recommendMemberId) {
        this.recommendMemberId = recommendMemberId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Integer getIsAngel() {
        return isAngel;
    }

    public void setIsAngel(Integer isAngel) {
        this.isAngel = isAngel;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(Integer isvirtual) {
        this.isvirtual = isvirtual;
    }

    public String getFromSource() {
        return fromSource;
    }

    public void setFromSource(String fromSource) {
        this.fromSource = fromSource;
    }

    public Long getInviteCodeId() {
        return inviteCodeId;
    }

    public void setInviteCodeId(Long inviteCodeId) {
        this.inviteCodeId = inviteCodeId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOpenPlatform() {
        return openPlatform;
    }

    public void setOpenPlatform(String openPlatform) {
        this.openPlatform = openPlatform;
    }

    public String getMobileProvince() {
        return mobileProvince;
    }

    public void setMobileProvince(String mobileProvince) {
        this.mobileProvince = mobileProvince;
    }

    public String getMobileCity() {
        return mobileCity;
    }

    public void setMobileCity(String mobileCity) {
        this.mobileCity = mobileCity;
    }

    public String getMobileAreaCode() {
        return mobileAreaCode;
    }

    public void setMobileAreaCode(String mobileAreaCode) {
        this.mobileAreaCode = mobileAreaCode;
    }

    public String getMobileZip() {
        return mobileZip;
    }

    public void setMobileZip(String mobileZip) {
        this.mobileZip = mobileZip;
    }

    public String getMobileCompany() {
        return mobileCompany;
    }

    public void setMobileCompany(String mobileCompany) {
        this.mobileCompany = mobileCompany;
    }

    public String getMobileCard() {
        return mobileCard;
    }

    public void setMobileCard(String mobileCard) {
        this.mobileCard = mobileCard;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getOpenClient() {
        return openClient;
    }

    public void setOpenClient(String openClient) {
        this.openClient = openClient;
    }

    public String getIpOfArea() {
        return ipOfArea;
    }

    public void setIpOfArea(String ipOfArea) {
        this.ipOfArea = ipOfArea;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getRandomToken() {
        return randomToken;
    }

    public void setRandomToken(String randomToken) {
        this.randomToken = randomToken;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getGenderDesc() {
        return genderDesc;
    }

    public void setGenderDesc(String genderDesc) {
        this.genderDesc = genderDesc;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }
}
