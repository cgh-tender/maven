package com.dw.model;

import java.io.Serializable;

public class SynUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账号
	 */
	private String account;//账号
	/**
	 * 昵称
	 */
	private String nickname;//昵称（用户名称）
	
	/**
	 * 区域
	 */
	private Integer regionId;//区域代码
	
	/**
	 * 组织架构的部门编码
	 */
	private String orgCode;
	
	/**
	 * 组织架构的部门名称
	 */
	private String orgName;
	
	/**
	 * 职务
	 */
	private String job;
	
	/**
	 * 岗位
	 */
	private String unit;
	
	/**
	 * 员工编号
	 */
	private String number;
	
	/**
	 * 座机号码
	 */
	private String telephone;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 性别，0标识男，1标识女
	 */
	private Integer sex;
	
	/**
	 * 用工性质
	 */
	private Integer employmentNature;
	
	/**
	 * 对接签名
	 */
	private String sign;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getEmploymentNature() {
		return employmentNature;
	}

	public void setEmploymentNature(Integer employmentNature) {
		this.employmentNature = employmentNature;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
