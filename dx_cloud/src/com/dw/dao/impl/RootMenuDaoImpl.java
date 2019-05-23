package com.dw.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dw.common.StringUtil;
import com.dw.common.base.TempJdbcDao;
import com.dw.dao.MenuDao;
import com.dw.dao.RootMenuDao;
import com.dw.model.Menu;
import com.dw.model.RootMenu;
@Repository
public class RootMenuDaoImpl extends TempJdbcDao implements RootMenuDao {

	@Override
	public List  getRootMenuList (RootMenu menu) {
		
		StringBuffer sql =new  StringBuffer(" select id,menu_id,menu_name,menu_desc,menu_url,menu_level,menu_id_parent,create_time,update_time,menu_Index,menu_Icon_code from ums_menu where 1=1 ");
	
		Session session=getCurrentSession();
 		
		if (!"".equals(menu.getId()) && !StringUtil.isEmpty(menu.getId()+"") && menu.getId() != 0 ) {
			sql.append(" and id="+menu.getId());
		}
		if(!"".equals(menu.getMenu_name()) && !StringUtil.isEmpty(menu.getMenu_name())){
			sql.append(" and menu_name='"+menu.getMenu_name()+"'");
		}
		if (!"".equals(menu.getMenu_id_parent()) && menu.getMenu_id_parent() != null) {
			sql.append(" and menu_id_parent="+menu.getMenu_id_parent());
		}
		if(!"".equals(menu.getMenu_level()) && menu.getMenu_level() !=null){
			sql.append(" and menu_level="+menu.getMenu_level());
		}
		if(!"".equals(menu.getMenu_id()) && menu.getMenu_id() !=null){
			sql.append(" and menu_id="+menu.getMenu_id());
		}
		
		if(!"".equals(menu.getMenu_desc()) && menu.getMenu_desc() != null){
			sql.append(" and menu_desc = '"+menu.getMenu_desc()+"'");
		}
 		
 		return session.createSQLQuery(sql.toString()).addEntity(RootMenu.class).list();
  	}

	@Override
	public boolean UpdateMenu(RootMenu rootmenu) {
		Session session = getCurrentSession();
		try {
			String hql=" update RootMenu set menu_index = ?,menu_name=?,menu_desc=?,menu_url=?,update_time=?,menu_icon_code=? where menu_id = ? ";
			 
			Query query  = session.createQuery(hql); 
			
			query.setInteger(0, rootmenu.getMenu_index());
			query.setString(1, rootmenu.getMenu_name());
			query.setString(2, rootmenu.getMenu_desc());
			query.setString(3, rootmenu.getMenu_url());
			query.setString(4, rootmenu.getUpdate_time());
			query.setString(5, rootmenu.getMenu_icon_code());
			query.setString(6, rootmenu.getMenu_id());
     		int i = query.executeUpdate(); 	
 			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	 public boolean deleteMenu(String menuId){
		StringBuffer sql = new StringBuffer("delete from ums_menu where menu_id = '"+menuId+"'");
		Session session = getCurrentSession();
		try {
			int num = session.createSQLQuery(sql.toString()).executeUpdate();
			if(num == 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	 public RootMenu addMenu(RootMenu rootmenu){
		Session session = getCurrentSession();
		try{
			session.save(rootmenu);
			return rootmenu;
		}catch(Exception e){
			e.printStackTrace();
		}
		 return null;
	 }

	@Override
	public  List<RootMenu> getMenuByDesc(RootMenu rootmenu) {
		Session session = getCurrentSession();
		StringBuffer sql = new StringBuffer("select * from ums_menu where menu_desc = '"+rootmenu.getMenu_desc()+"'");
 		try {			
 			List<RootMenu> resultList = session.createSQLQuery(sql.toString()).addEntity(RootMenu.class).list();
			 return resultList;
  		} catch (Exception e) {
 			return new ArrayList<RootMenu>();
		}
	}

}
