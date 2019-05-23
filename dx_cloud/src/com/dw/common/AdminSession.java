package com.dw.common;

import java.util.Date;

import com.dw.model.Role;

/**
 * 登陆session信息
 * @author tlk
 *
 */
public class AdminSession {
	private int id;//主键
	private String loginName;//登录名
	private String password;//密码
	private String role;//角色
 	private String jsSessionId;
 	private String imCode;
	private Date cTime;//创建时间
	
	public String getImCode() {
		return imCode;
	}
	public void setImCode(String imCode) {
		this.imCode = imCode;
	}
	public String getJsSessionId() {
		return jsSessionId;
	}
	public void setJsSessionId(String jsSessionId) {
		this.jsSessionId = jsSessionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	@Override
	public String toString() {
		return "AdminSession [id=" + id + ", loginName=" + loginName + ", password=" + password + ", role=" + role
				+ ", jsSessionId=" + jsSessionId + ", imCode=" + imCode + ", cTime=" + cTime + "]";
	}
	
}
