package io.renren.modules.airdrop.entity;



import java.io.Serializable;
public class WybbAirdropEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private String address;
	/**
	 * 
	 */
	private String amount;
	/**
	 * 
	 */
	private String remark1;
	/**
	 *
	 */
	private String blocknum;
	/**
	 *
	 */
	private String tranhash;
	/**
	 *
	 */
	private String createdate;
	/**
	 * 
	 */
	private String remark2;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 获取：
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * 设置：
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	/**
	 * 获取：
	 */
	public String getRemark1() {
		return remark1;
	}
	/**
	 * 设置：
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	/**
	 * 获取：
	 */
	public String getRemark2() {
		return remark2;
	}

	public String getBlocknum() {
		return blocknum;
	}

	public void setBlocknum(String blocknum) {
		this.blocknum = blocknum;
	}

	public String getTranhash() {
		return tranhash;
	}

	public void setTranhash(String tranhash) {
		this.tranhash = tranhash;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}
