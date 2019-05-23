package com.dw.model;

import java.util.List;

public class Menu_jur {
	
	String role_id;
	List<RoleMenu> role_check_menu;
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public List<RoleMenu> getRole_check_menu() {
		return role_check_menu;
	}
	public void setRole_check_menu(List<RoleMenu> role_check_menu) {
		this.role_check_menu = role_check_menu;
	}
 
	
	

}
