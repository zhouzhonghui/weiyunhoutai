package io.renren.modules.wybbscalp.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author shunshine
 * @email shunshine@99weiyun.com
 * @date 2018-04-03 10:29:11
 */
public class WybbScalpEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
		private Long id;
	/**
	 * 
	 */
	private String mobile;
	/**
	 * 
	 */
	private String idno;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String useraddress;
	/**
	 * 
	 */
	private String ucname;
	/**
	 *
	 */
	private String ucmobile;
	/**
	 * 
	 */
	private String ucemail;
	/**
	 * 
	 */
	private String jobtime;
	/**
	 * 
	 */
	private String createtime;
	/**
	 * 
	 */
	private String status;
	/**
	 * 
	 */
	private String remark1;
	/**
	 * 
	 */
	private String remark2;
	/**
	 * 
	 */
	private String remark3;
	/**
	 * 
	 */
	private String remark4;

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
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setIdno(String idno) {
		this.idno = idno;
	}
	/**
	 * 获取：
	 */
	public String getIdno() {
		return idno;
	}
	/**
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	/**
	 * 获取：
	 */
	public String getUseraddress() {
		return useraddress;
	}
	/**
	 * 设置：
	 */
	public void setUcname(String ucname) {
		this.ucname = ucname;
	}
	/**
	 * 获取：
	 */
	public String getUcname() {
		return ucname;
	}
	/**
	 * 设置：
	 */
	public void setUcemail(String ucemail) {
		this.ucemail = ucemail;
	}
	/**
	 * 获取：
	 */
	public String getUcemail() {
		return ucemail;
	}
	/**
	 * 设置：
	 */
	public void setJobtime(String jobtime) {
		this.jobtime = jobtime;
	}
	/**
	 * 获取：
	 */
	public String getJobtime() {
		return jobtime;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public String getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
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
	/**
	 * 设置：
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	/**
	 * 获取：
	 */
	public String getRemark3() {
		return remark3;
	}
	/**
	 * 设置：
	 */
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}
	/**
	 * 获取：
	 */
	public String getRemark4() {
		return remark4;
	}

	public String getUcmobile() {
		return ucmobile;
	}

	public void setUcmobile(String ucmobile) {
		this.ucmobile = ucmobile;
	}
}
