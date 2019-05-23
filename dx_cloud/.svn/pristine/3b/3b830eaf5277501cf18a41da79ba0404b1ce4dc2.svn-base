package com.dw.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dw.common.StringUtil;
import com.dw.common.base.TempJdbcDao;
import com.dw.dao.IRoleDao;
import com.dw.dao.IRoleMenuDao;
import com.dw.model.Role;
import com.dw.model.RoleMenu;
@Repository
public class RoleMenuDaoImpl extends TempJdbcDao implements IRoleMenuDao {

	@Override
	public List<Map<String, String>> getRoleMenuList(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("SELECT  id,menu_id,role_id from ums_role_menu where 1=1");
		if (!"".equals(roleMenu.getId()) && !StringUtil.isEmpty(roleMenu.getId()+"") && roleMenu.getId() != 0) {
			sql.append(" and id="+roleMenu.getId());
		}
		if (!"".equals(roleMenu.getRoleId()) && !StringUtil.isEmpty(roleMenu.getRoleId()+"")) {
			sql.append(" and role_id='"+roleMenu.getRoleId()+"'");
		}
		if (!"".equals(roleMenu.getMenuId()) && !StringUtil.isEmpty(roleMenu.getMenuId()+"")) {
			sql.append(" and menu_id='"+roleMenu.getMenuId()+"'");
		}
		
		if (!"".equals(roleMenu.getMenu_id()) && !StringUtil.isEmpty(roleMenu.getMenu_id()+"")) {
			sql.append(" and menu_id='"+roleMenu.getMenu_id()+"'");
		}
		
		return querylist(sql.toString());
	}

	@Override
	public boolean saveOrUpdateRoleMenu(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		Session session=getCurrentSession();
		try { 
		//session.save(roleMenu);
			 
			
			StringBuffer sql = new StringBuffer("INSERT INTO ums_role_menu (role_id,menu_id)");
			sql.append(" VALUES ('"+roleMenu.getRoleId()+"','"+roleMenu.getMenuId()+"')");


			int num = session.createSQLQuery(sql.toString()).executeUpdate(); 
			
			if (num == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delRoleMenu(String ids) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("delete from ums_role_menu where role_id in ("+ids+")");
		Session session=getCurrentSession();
		try {
			int num = session.createSQLQuery(sql.toString()).executeUpdate(); 
			if (num == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean delRoleMenuByRoleIdAndMenuId(RoleMenu rm) {
		StringBuffer sql = new StringBuffer("delete from ums_role_menu where role_id = '").append(rm.getRoleId());
		sql.append("' and menu_id = '").append(rm.getMenu_id()).append("'");
		Session session=getCurrentSession(); 
 		try{
			session.createSQLQuery(sql.toString()).executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
 		return true;
	}

}
