package com.dw.dao;

import java.util.List;
import java.util.Map;

import com.dw.model.Role;
import com.dw.model.RoleMenu;
/**
 * 角色管理
 * @author lulutong
 *
 */
public interface IRoleMenuDao {
	/**
	 * 列表
	 * @param role
	 * @return
	 */
	public List<Map<String,String>> getRoleMenuList(RoleMenu roleMenu);
	/**
	 * 编辑、新增
	 * @param role
	 * @return
	 */
	public boolean saveOrUpdateRoleMenu(RoleMenu roleMenu);
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean delRoleMenu(String ids);
	
	
	/**
	 * 通过menuId 和 role_id
	 * 
	 */
	public Boolean delRoleMenuByRoleIdAndMenuId(RoleMenu rm);
	
}
