package io.renren.modules.wybbbatchtransfer.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author shunshine
 * @email shunshine@99weiyun.com
 * @date 2018-04-10 09:46:58
 */
public class WybbBatchtransferEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
		private Long id;
	/**
	 * 付款地址
	 */
	private String payaddress;
	/**
	 * 收款地址
	 */
	private String gatheraddress;
	/**
	 * 付款金额
	 */
	private String amount;
	/**
	 * 创建日期
	 */
	private String createdate;
	/**
	 * 区块号
	 */
	private String blocknum;
	/**
	 * 区块hash
	 */
	private String tranhash;
	/**
	 * 标识，1代表eth的钱包转账，2代表的是she转账
	 */
	private String flag;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 备注1
	 */
	private String remark1;
	/**
	 * 备注2
	 */
	private String remark2;

	/**
	 * 设置：主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：付款地址
	 */
	public void setPayaddress(String payaddress) {
		this.payaddress = payaddress;
	}
	/**
	 * 获取：付款地址
	 */
	public String getPayaddress() {
		return payaddress;
	}
	/**
	 * 设置：收款地址
	 */
	public void setGatheraddress(String gatheraddress) {
		this.gatheraddress = gatheraddress;
	}
	/**
	 * 获取：收款地址
	 */
	public String getGatheraddress() {
		return gatheraddress;
	}
	/**
	 * 设置：付款金额
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 获取：付款金额
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：创建日期
	 */
	public String getCreatedate() {
		return createdate;
	}
	/**
	 * 设置：区块号
	 */
	public void setBlocknum(String blocknum) {
		this.blocknum = blocknum;
	}
	/**
	 * 获取：区块号
	 */
	public String getBlocknum() {
		return blocknum;
	}
	/**
	 * 设置：区块hash
	 */
	public void setTranhash(String tranhash) {
		this.tranhash = tranhash;
	}
	/**
	 * 获取：区块hash
	 */
	public String getTranhash() {
		return tranhash;
	}
	/**
	 * 设置：标识，1代表eth的钱包转账，2代表的是she转账
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * 获取：标识，1代表eth的钱包转账，2代表的是she转账
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：备注1
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	/**
	 * 获取：备注1
	 */
	public String getRemark1() {
		return remark1;
	}
	/**
	 * 设置：备注2
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	/**
	 * 获取：备注2
	 */
	public String getRemark2() {
		return remark2;
	}
}
