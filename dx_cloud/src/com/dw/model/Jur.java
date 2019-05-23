package com.dw.model;

import java.util.List;

public class Jur {

	private Integer id;
	private String jur_id;
	private String jur_key;
	private String jur_value;
	private String jur_parent_id;
	private Integer jur_type;
	private String create_time;
	private String update_time;
	private Integer jur_checked;
	private String menu_id;
	private String jur_desc;
 	private List<Jur> child;
	private String role_id;
	private JurType data; 
	private String[] jur_data;
	
	
	
	
	 
	public String[] getJur_data() {
		return jur_data;
	}
	public void setJur_data(String[] jur_data) {
		this.jur_data = jur_data;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJur_id() {
		return jur_id;
	}
	public void setJur_id(String jur_id) {
		this.jur_id = jur_id;
	}
	 
	public String getJur_parent_id() {
		return jur_parent_id;
	}
	public void setJur_parent_id(String jur_parent_id) {
		this.jur_parent_id = jur_parent_id;
	}
	public Integer getJur_type() {
		return jur_type;
	}
	public void setJur_type(Integer jur_type) {
		this.jur_type = jur_type;
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
	public Integer getJur_checked() {
		return jur_checked;
	}
	public void setJur_checked(Integer jur_checked) {
		this.jur_checked = jur_checked;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public List<Jur> getChild() {
		return child;
	}
	public void setChild(List<Jur> child) {
		this.child = child;
	}
	 
	public String getJur_key() {
		return jur_key;
	}
	public void setJur_key(String jur_key) {
		this.jur_key = jur_key;
	}
	public String getJur_value() {
		return jur_value;
	}
	public void setJur_value(String jur_value) {
		this.jur_value = jur_value;
	}
	public String getJur_desc() {
		return jur_desc;
	}
	public void setJur_desc(String jur_desc) {
		this.jur_desc = jur_desc;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public JurType getData() {
		return data;
	}
	public void setData(JurType data) {
		this.data = data;
	}
	
 	
	
}
