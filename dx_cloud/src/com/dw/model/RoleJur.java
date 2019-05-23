package com.dw.model;

import java.util.List;

public class RoleJur {

	private Integer id;
	
	private String role_id;
	
	private String jur_id;
	
	private String menu_id;
	
	private List<Jur> jur_data;
	
	private List child;
	
  
	
	
	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public List<Jur> getJur_data() {
		return jur_data;
	}

	public void setJur_data(List<Jur> jur_data) {
		this.jur_data = jur_data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getJur_id() {
		return jur_id;
	}

	public void setJur_id(String jur_id) {
		this.jur_id = jur_id;
	}

	public List getChild() {
		return child;
	}

	public void setChild(List child) {
		this.child = child;
	}
	
	
	
	
}
