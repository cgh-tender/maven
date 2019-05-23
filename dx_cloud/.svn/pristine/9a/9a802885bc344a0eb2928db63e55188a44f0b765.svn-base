package com.dw.servce;

import java.util.List;
import java.util.Map;

import com.dw.model.RoleMenu;

public interface IRoleMenuService {

	/**
	 * 列表
	 * @param roleMenu
	 * @return
	 */
	public List<Map<String,String>> getRoleMenuList(RoleMenu roleMenu);
	/**
	 * 编辑、新增
	 * @param roleMenu
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
