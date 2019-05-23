package com.dw.model;
/**
 * 角色信息
 * @author hutianlong
 *
 */
public class Role {
	private static final long serialVersionUID = 1L;
	private int id;//主键
	private String role_id;//角色编码
	private String role_name;//角色名称
	private String role_desc;//角色描述
	private String create_time;//创建时间
	private String update_time;//修改时间
	private Integer role_index;//顺序
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public Integer getRole_index() {
		return role_index;
	}
	public void setRole_index(Integer role_index) {
		this.role_index = role_index;
	}
	
}
