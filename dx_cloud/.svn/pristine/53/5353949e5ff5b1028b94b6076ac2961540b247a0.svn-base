package com.dw.servce.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.dao.impl.RoleMenuDaoImpl;
import com.dw.model.RoleMenu;
import com.dw.servce.IRoleMenuService;
@Service("roleMenuService")
@Transactional
public class RoleMenuServiceImpl implements IRoleMenuService{

	@Autowired
	RoleMenuDaoImpl roleMenuDao;

	@Override
	public List<Map<String, String>> getRoleMenuList(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		return roleMenuDao.getRoleMenuList(roleMenu);
	}

	@Override
	public boolean saveOrUpdateRoleMenu(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		return roleMenuDao.saveOrUpdateRoleMenu(roleMenu);
	}

	@Override
	public boolean delRoleMenu(String ids) {
		// TODO Auto-generated method stub
		return roleMenuDao.delRoleMenu(ids);
	}

	@Override
	public Boolean delRoleMenuByRoleIdAndMenuId(RoleMenu rm) {
		// TODO Auto-generated method stub
		return roleMenuDao.delRoleMenuByRoleIdAndMenuId(rm);
	}

}
