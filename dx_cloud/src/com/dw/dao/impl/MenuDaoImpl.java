package com.dw.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dw.common.StringUtil;
import com.dw.common.base.TempJdbcDao;
import com.dw.dao.MenuDao;
import com.dw.model.Menu;
@Repository
public class MenuDaoImpl extends TempJdbcDao implements MenuDao {

	@Override
	public List<Map<String, String>> getMenuList(Menu menu) {
		StringBuffer sql =new  StringBuffer("select id,menu_id,menu_name,menu_desc,menu_url,menu_level,menu_id_parent,create_time,update_time from ums_menu where 1=1 ");
		if (!"".equals(menu.getId()) && !StringUtil.isEmpty(menu.getId()+"") && menu.getId() != 0 ) {
			sql.append(" and id="+menu.getId());
		}
		if(!"".equals(menu.getMenuName()) && !StringUtil.isEmpty(menu.getMenuName())){
			sql.append(" and menu_name='"+menu.getMenuName()+"'");
		}
		if (!"".equals(menu.getMenuIdParent()) && menu.getMenuIdParent() != null) {
			sql.append(" and menu_id_parent="+menu.getMenuIdParent());
		}
		if(!"".equals(menu.getMenuLevel()) && menu.getMenuLevel() !=null){
			sql.append(" and menu_level="+menu.getMenuLevel());
		}
		return querylist(sql.toString());
	}

	@Override
	public boolean saveOrUpdateMenu(Menu menu) {
		Session session = getCurrentSession();
		try {
			session.save(menu);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteMen(String menuIds) {
		StringBuffer sql = new StringBuffer("delete from ums_menu where id in ("+menuIds+")");
		Session session = getCurrentSession();
		try {
			int num = session.createSQLQuery(sql.toString()).executeUpdate();
			if(num == 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
