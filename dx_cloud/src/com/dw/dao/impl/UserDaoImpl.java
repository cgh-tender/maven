package com.dw.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dw.common.StringUtil;
import com.dw.common.base.TempJdbcDao;
import com.dw.dao.IUserDAO;
import com.dw.model.Menu;
import com.dw.model.Role;
import com.dw.model.User;
@Repository
public class UserDaoImpl extends TempJdbcDao implements IUserDAO {
	
	public List<Map<String, String>> getUserList(User am) { 
		StringBuffer sql = new StringBuffer("SELECT id,user_id,login_name,pass_show,user_name,phone,email,role_id,organization_id,create_time,update_time,org_name,role_name,job FROM(SELECT a.id,user_id,login_name,pass_show,user_name,phone,email,a.role_id,organization_id,a.create_time,b.update_time,b.org_name,c.role_name,job FROM ums_user a LEFT JOIN ums_organization b ON a.organization_id = b.org_id left join ums_role c on a.role_id=c.role_id) d WHERE 1 = 1 ");
		if (!"".equals(am.getId()) && !StringUtil.isEmpty(am.getId()+"") && am.getId() != 0) {
			sql.append("and id="+am.getId());
		} 
		if(am.getLoginName() != null && !"".equals(am.getLoginName()+"")){
			sql.append("and login_name='"+am.getLoginName()+"'");
		}

		if (am.getOrganizationId() != null && !"".equals(am.getOrganizationId())) {
			sql.append(" and organization_id='"+am.getOrganizationId()+"'");
		}
		
		if (am.getRoleId() != null && !"".equals(am.getRoleId())) {
			sql.append(" and role_id="+am.getRoleId());
		}
		if (am.getUserId() != null && !"".equals(am.getUserId())) {
			sql.append(" and user_id="+am.getUserId());
		}
		sql.append(" order by create_time");
		return querylist(sql.toString());
	} 
	public boolean saveOrUpdateUser(User am) {
		Session session=getCurrentSession();
		try { 
			session.save(am);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	public boolean delUser(String ids) {
		StringBuffer sql = new StringBuffer("delete from ums_user where id in ("+ids+")");
		Session session=getCurrentSession();
		try {
			int num=session.createSQLQuery(sql.toString()).executeUpdate();
			if (num == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public List<Map<String, String>> getUsers(String loginName,String password) {
		StringBuffer sql = new StringBuffer("select CONCAT(id,'') strid ,t.* from ums_user t where 1=1 ");
		if(!"".equals(loginName) && !StringUtil.isEmpty(loginName)){
			sql.append(" and login_Name='"+loginName+"' ");
		}
		if(!"".equals(password) && !StringUtil.isEmpty(password)){
			sql.append(" and password='"+password+"' ");
		} 
		return  querylist(sql.toString());
	}
	@Override
	public List<Map<String, String>> getMenuTreeList(User user, Menu menu) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("SELECT u.login_name,m.menu_id,m.menu_name,m.menu_url,m.menu_level,m.menu_id_parent");
		sql.append(" FROM ums_user u");
		sql.append(" INNER JOIN ums_role_menu rm ON rm.role_id=u.role_id");
		sql.append(" INNER JOIN ums_menu m ON  rm.menu_id=m.menu_id");
		if(user.getUserId() != null && !"".equals(user.getUserId()+"")){
			sql.append(" and u.user_id='"+user.getUserId()+"'");
		}
		if(menu.getMenuLevel() != null && !"".equals(menu.getMenuLevel()+"")){
			sql.append(" and m.menu_level='"+menu.getMenuLevel()+"'");
		}

		if (menu.getMenuId() != null && !"".equals(menu.getMenuId())) {
			sql.append(" and m.menu_id="+menu.getMenuId());
		}
		
		if (menu.getMenuIdParent() != null && !"".equals(menu.getMenuIdParent())) {
			sql.append(" and m.menu_id_parent="+menu.getMenuIdParent());
		}
		return querylist(sql.toString());
	}
	
	/**
	 * 
	 * 通过加密的MD5 用户名查询
	 */
	public List<Map<String, String>>  getUserListByMd5Name(User user){
		Session session=getCurrentSession();

		StringBuffer sql = new StringBuffer("SELECT u.login_name ");
		sql.append(" FROM ums_user u where 1 = 1 ");
		if(user.getLoginName() != null && !"".equals(user.getLoginName()+"")){
			sql.append(" AND  MD5(login_name) = '"+user.getLoginName()+"'");
 			return querylist(sql.toString());
 		}else{
			return null;
		}
 	}
	@Override
	public int updateUser(User user) {
		Session session = getCurrentSession();
		try {
			String hql=" UPDATE User  SET username = ? ,phone = ?, email = ?, roleId = ?, organizationId = ? ,updateTime=?, regionId= ? , orgName = ? ,"
					+ " job = ? ,unit = ?,telephone = ?,sex = ? ,employmentNature = ? where login_name = ? ";
 			Query query  = session.createQuery(hql); 
 			query.setString(0, user.getUsername());
			query.setString(1, user.getPhone());
			query.setString(2, user.getEmail());
			query.setString(3,user.getRoleId());
			query.setString(4, user.getOrganizationId());
			query.setString(5, user.getUpdateTime());
			query.setInteger(6, user.getRegionId());
			query.setString(7, user.getOrgName());
			query.setString(8, user.getJob());
			query.setString(9, user.getUnit());
			query.setString(10, user.getTelephone());
			query.setInteger(11, user.getSex());
			query.setInteger(12, user.getEmploymentNature());
			query.setString(13, user.getLoginName());

      		int i = query.executeUpdate(); 	
 			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateUserRole(User user){
		Session session = getCurrentSession();
		try {
			String hql=" update ums_user  set ";
			if(null != user.getRoleId() && !"null".equals(user.getRoleId()) && !"".equals(user.getRoleId())){
				hql+=" role_id = '"+user.getRoleId()+"',";
			}
			if(null != user.getUpdateTime() && !"null".equals(user.getUpdateTime()) && !"".equals(user.getUpdateTime())){
				hql+=" update_time = '"+user.getUpdateTime()+"',";
			}
			if(null != user.getStyle_flag() && !"null".equals(user.getStyle_flag()) && !"".equals(user.getStyle_flag())){
				hql+=" style_flag = '"+user.getStyle_flag()+"',";
			}
			hql = hql.substring(0, hql.length() -1);
  			hql+=" where id = "+user.getId();
 			int i = session.createSQLQuery(hql).executeUpdate(); 
 			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
 	
}
