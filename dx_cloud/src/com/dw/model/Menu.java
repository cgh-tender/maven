package com.dw.model;
/**
 * 菜单管理
 * @author hutianlong
 *
 */
public class Menu {
	private int id;
	private String menuId;//菜单编码
	private String menuName;//菜单名称
	private String menuDesc;//菜单描述
	private String menuUrl;//菜单链接
	private String menuLevel;//菜单等级
	private String menuIdParent;//菜单上级编码
	private String createTime;
	private String updateTime;
	
 	
	
	
	
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuIdParent() {
		return menuIdParent;
	}
	public void setMenuIdParent(String menuIdParent) {
		this.menuIdParent = menuIdParent;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
