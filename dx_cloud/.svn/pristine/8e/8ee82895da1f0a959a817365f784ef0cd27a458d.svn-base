package com.dw.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dw.common.StringUtil;
import com.dw.common.base.TempJdbcDao;
import com.dw.dao.IRoleDao;
import com.dw.model.CityCode;
import com.dw.model.InterFace;
import com.dw.model.Jur;
import com.dw.model.Menu;
import com.dw.model.Role;
import com.dw.model.RoleJur;
import com.dw.model.RootMenu;
@Repository
public class RoleDaoImpl extends TempJdbcDao implements IRoleDao {

	public List<Role> getRoleList(Role role) {
		
		Session session=getCurrentSession();
 		
		StringBuffer sql = new StringBuffer("select id,role_id,role_name,role_desc,create_time,update_time,role_index from ums_role where 1=1  ");
		
		if (!"".equals(role.getId()) && !StringUtil.isEmpty(role.getId()+"") && role.getId() != 0) {
			sql.append(" and id="+role.getId());
		}
		if (!"".equals(role.getRole_id()) && !StringUtil.isEmpty(role.getRole_id()+"")) {
			sql.append(" and role_id = '"+role.getRole_id()+"'");
		}
 		if (!"".equals(role.getRole_name()) && !StringUtil.isEmpty(role.getRole_name()+"")) {
			sql.append(" and role_name='"+role.getRole_name()+"'");
		}
		sql.append(" order by role_index asc ");
		
 		return session.createSQLQuery(sql.toString()).addEntity(Role.class).list();

 	}

	//修改
	
	public boolean UpdateRole(Role role){
		Session session = getCurrentSession();
		try {
			String hql=" update Role set  role_name=?,role_desc=?, update_time=?,role_index=? where role_id = ? ";
 			Query query  = session.createQuery(hql); 
 			query.setString(0, role.getRole_name());
			query.setString(1, role.getRole_desc());
			query.setString(2, role.getUpdate_time());
			query.setInteger(3, role.getRole_index());
			query.setString(4, role.getRole_id());
      		int i = query.executeUpdate(); 	
 			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//添加
	public Role addRole(Role role) {
		Session session = getCurrentSession();
		try {
 			session.save(role);
  	  		return role;
 		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 	
	
	/*// 改变role_index  
	
	public Role changeIndex(Role role){
 		Session session = getCurrentSession();
  		String hql=" update Role set  role_index=? where role_id = ? ";
  		session.createSQLQuery(hql.toString()).executeUpdate();
   		 int i = query.executeUpdate(); 	
   		role.setRole_index(role.getId());
  		return role;
	}*/
	
	public boolean delRole(String role_id) {
 		StringBuffer sql = new StringBuffer("delete from ums_role where role_id = '"+role_id+"'");
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

	/***
	 * 
	 * 通过角色id 获得菜单详情
	 * 
	 * 
	 */
	public List  getRoleList(String roleid,String menulevel,String menuParentId,String type){
		StringBuffer sb = new StringBuffer();
		Session session=getCurrentSession();
		//session.clear();
 		sb.append("SELECT distinct t2.* FROM ums_role_menu t1 ");
 		if(("1501793717970").equals(roleid)){
 			sb.append(" right ");
 		}else{
 			sb.append(" left ");
 		}
  		sb.append( " JOIN ums_menu t2 ON t1.menu_id = t2.menu_id WHERE 1= 1 ");
  		
  		
  		if(type.equals("role")){ //角色查询时排除系统管理
  			sb.append(" AND t2.menu_name != '系统管理' ");
  		}
 		
 		 if(!roleid.equals("1501793717970")){
 	 		 sb.append("and t1.role_id = '").append(roleid).append("'");
  		 }
  		sb.append(" AND t2.menu_level = '")
 		.append(menulevel).append("'");
 		if(menuParentId!=null && menuParentId!=""){
 			sb.append(" AND t2.menu_id_parent = '").append(menuParentId).append("'");
 		}
 		sb.append(" ORDER BY  t2.menu_index ASC ");
  		
  		SQLQuery sqlQuery = session.createSQLQuery(sb.toString()).addEntity(RootMenu.class);
  		
  		sqlQuery.setCacheable(true);
   		
  		return sqlQuery.list();
 	}

	@Override
	public List<Jur> getJurBymenuId(String menu_id, String role_id,
			String jur_type, String jur_parent_id) {
  		
		Session session=getCurrentSession();
 		StringBuffer sb = new StringBuffer();
 		
 		
 		if( role_id == null ){//查询该菜单下所有的
 			
 			sb.append("SELECT  t1.*  FROM   ums_jur t1  ").append(" WHERE   t1.menu_id = '").append(menu_id)
 			.append("' AND t1.jur_type in (").append(jur_type).append(")");
 			if(jur_parent_id != null && jur_parent_id!= "" ){
 				sb.append(" and  t1.jur_parent_id ='").append(jur_parent_id).append("'");
 			}
 			
  		}else{		// 查询本角色有distinct的
   			sb.append("SELECT   t1.*  FROM ums_jur t1 ");
   			
   			if("1501793717970".equals(role_id)){
   				sb.append(" left ");
   			}else{
   				sb.append(" right ");
   			}
   			
   			sb.append(" join ums_role_jur t3 on t1.jur_id = t3.jur_id   where  t1.menu_id = '");
    			
 			sb.append(menu_id).append("'");
   			if(!"1501793717970".equals(role_id)){
 				sb.append("AND t3.role_id = '");
 				sb.append(role_id).append("'");
 			}
  			sb.append(" AND t1.jur_type in (")
 			.append(jur_type).append(")");
 			if(jur_parent_id != null && jur_parent_id!= "" ){
 				sb.append(" and  t1.jur_parent_id ='").append(jur_parent_id).append("'");
 			}
  		}
   		
 		System.out.println("======="+sb);
 		
  		return session.createSQLQuery(sb.toString()).addEntity(Jur.class).list();
	}
	
	 public Jur addJur(Jur jur){
	     
		 Session session = getCurrentSession();
			try {
	 			session.save(jur);
 	 	  		return jur;
	 		} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	 }
	 
		public Boolean delJur(String jur_id){
			
  		 StringBuffer sql = new StringBuffer("delete from ums_jur where jur_id in ("+jur_id+")");
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

		public Boolean UpdateJur(Jur  jur){
 			
			Session session = getCurrentSession();
			try {
				String hql=" update Jur set  menu_id=?,jur_desc=?,jur_key=?,jur_value=?,update_time=? where jur_id = ? ";
	 			Query query  = session.createQuery(hql); 
	 			query.setString(0, jur.getMenu_id());
				query.setString(1, jur.getJur_desc());
				query.setString(2, jur.getJur_key());
				query.setString(3, jur.getJur_value());
				query.setString(4, jur.getUpdate_time());
				query.setString(5, jur.getJur_id());
 	      		int i = query.executeUpdate(); 	
	 			return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			
		}
		public Boolean  delRoleJur(String role_id,String menu_id){
			
 	  		 StringBuffer sql = new StringBuffer("delete from ums_role_jur where role_id = '"+role_id+"' and menu_id = '"+menu_id+"'");
				Session session=getCurrentSession();
				try {
					int num = session.createSQLQuery(sql.toString()).executeUpdate(); 
 					return true;
				} catch (Exception e) {
					return false;
			 }
		}

		@Override
		public Boolean addRoleJur(RoleJur rolejur) {
			
 			Session session = getCurrentSession();
			try {
	 			session.save(rolejur);
 	 	  		return true;
	 		} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
  		}	
		
		/**
		 * 
		 * 添加接口
		 * 
		 */
		public Boolean addInterface(InterFace infa){
			
			 Session session = getCurrentSession();
				try {
		 			session.save(infa);
	 	 	  		return true;
		 		} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			
		}
		
		/**
		 * 通过菜单Id查找对应接口
		 * 
		 */
		
		public List<InterFace> getInterfaceAddressByMenuId (Menu me){
			 Session session = getCurrentSession();
 			 StringBuilder sb = new StringBuilder();
			 sb.append(" select * from ums_interface where menu_id = '"+me.getMenuId()+"'");
 			 return session.createSQLQuery(sb.toString()).addEntity(InterFace.class).list();
 		}
		
		public Boolean checkCascadeChild(String jurId){
    	     StringBuilder sb = new StringBuilder();
 			 sb.append("select id from ums_jur where jur_parent_id ='").append(jurId).append("'");
  			 List resultList = querylist(sb.toString());
   			 if(resultList.size() >0 || resultList == null){
  				return true;
  			 }else{
  				 return false;
  			 }
 		}
 	
		
		public List<Map>  jurcitylist(CityCode cityCode){
			
			Session session=getCurrentSession();
 			StringBuffer sql = new StringBuffer();
 			if (!"".equals(cityCode.getProv_id()) && !StringUtil.isEmpty(cityCode.getProv_id()+"")) {
				sql.append("  SELECT prov_id,prov_name,latn_id,latn_name  FROM city_code  t  ORDER BY t.latn_id   ");
 			}else{
				sql.append("  SELECT DISTINCT t.prov_id,t.prov_name FROM city_code  t ");
				sql.append(" ORDER BY t.prov_id  ");
 			}
  	 		return  querylist(sql.toString());
			
 		}
		
		public Boolean deleteInterFace(String mumeId){
			StringBuilder sb = new StringBuilder();
			sb.append(" DELETE FROM ums_interface  WHERE menu_id = '");
			sb.append(mumeId).append("'");
 			Session session=getCurrentSession();
			try {
				int num = session.createSQLQuery(sb.toString()).executeUpdate(); 
 				return true;
			} catch (Exception e) {
				return false;
			}
		}
		/**
		 * 
		 * 通过menu_id,role_id 获得权限
		 * 
		 */
		public List<Map> getJurDetailByMenuIdAndRoleId(String menu_id,String role_id,String jur_type,String jur_parent_id){
			
			Session session=getCurrentSession();
	 		StringBuffer sb = new StringBuffer();
			
	 		if("'2'".equals(jur_type)){
		 		sb.append("SELECT distinct   t1.jur_key,t1.jur_value   FROM ums_jur t1 ");
 	 		}else{
	 			sb.append("SELECT distinct   t1.jur_key,t1.jur_value,t1.jur_type,t1.jur_id   FROM ums_jur t1 ");
	 		}
    			
   			if("1501793717970".equals(role_id)){
   				sb.append(" left ");
   			}else{
   				sb.append(" right ");
   			}
    	    sb.append(" join ums_role_jur t3 on t1.jur_id = t3.jur_id   where  t1.menu_id = '");
  			sb.append(menu_id).append("'");
   			if(!"1501793717970".equals(role_id)){
 				sb.append("AND t3.role_id = '");
 				sb.append(role_id).append("'");
 			}
  			sb.append(" AND t1.jur_type in (")
 			.append(jur_type).append(")");
 			if(jur_parent_id != null && jur_parent_id!= "" ){
 				sb.append(" and  t1.jur_parent_id ='").append(jur_parent_id).append("'");
 			}
  			return  querylist(sb.toString());
		}	
		
		
}
