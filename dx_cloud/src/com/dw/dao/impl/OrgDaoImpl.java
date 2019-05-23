package com.dw.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dw.common.StringUtil;
import com.dw.common.base.TempJdbcDao;
import com.dw.dao.IOrgDao;
import com.dw.model.Organization;
@Repository
public class OrgDaoImpl extends TempJdbcDao implements IOrgDao {

	@Override
	public List<Map<String, String>> getOrgList(Organization org) {
		StringBuffer sql = new StringBuffer("select id,org_id,org_name,org_desc,org_id_parent,org_level,create_time,update_time,role_id from ums_organization where 1=1 ");
		if (!"".equals(org.getId()) && !StringUtil.isEmpty(org.getId()+"") && org.getId() !=0) {
			sql.append(" and id="+org.getId());
		}
		if (!"".equals(org.getOrgId()) && org.getOrgId() != null) {
			sql.append(" and org_id='"+org.getOrgId()+"'");
		}
		if (!"".equals(org.getOrgName()) && org.getOrgName() != null) {
			sql.append(" and org_name='"+org.getOrgName()+"'");
		}
		if(!"".equals(org.getOrgIdParent()) && org.getOrgIdParent() != null){
			sql.append(" and org_id_parent="+org.getOrgIdParent());
		}
		if (!"".equals(org.getOrgLevel()) && org.getOrgLevel() !=null ) {
			sql.append(" and org_level="+org.getOrgLevel());
		}
		sql.append(" order by create_time");
		return querylist(sql.toString());
	}

	@Override
	public boolean saveOrUpOrg(Organization org) {
		Session session=getCurrentSession();
		try {
			session.save(org);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean delOrgs(String ids) {
		StringBuffer sql = new StringBuffer("delete from ums_organization where org_id in ("+ids+")");
		try {
			Session session = getCurrentSession();
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
	public List<Map<String, String>> getOrgTreeList(Organization org) {
		  
				 
		StringBuffer sql = new StringBuffer(" select id,org_id,org_name,org_desc,create_time,update_time,org_id_parent,org_level from("); 
		if (!"".equals(org.getId()) && !StringUtil.isEmpty(org.getId()+"") && org.getId() !=0) {
			sql.append("select id,a.org_id,org_name,org_desc,create_time,update_time,org_id_parent,org_level from ums_organization a, " +
					"(select org_id from ums_organization  where id="+org.getId()+" ) b where a.org_id_parent=b.org_id  ");
			
		}
		if (!"".equals(org.getOrgId()) && !StringUtil.isEmpty(org.getOrgId()+"")) {
			sql.append("select id,a.org_id,org_name,org_desc,create_time,update_time,org_id_parent,org_level from ums_organization a, " +
					"(select org_id from ums_organization  where org_id='"+org.getOrgId()+"' ) b where a.org_id_parent=b.org_id  ");
			
		}
		if (!"".equals(org.getOrgName()) && org.getOrgName() != null) {
			sql.append("select id,a.org_id,org_name,org_desc,create_time,update_time,org_id_parent,org_level from ums_organization a, " +
					"(select org_id from ums_organization  where org_name='"+org.getOrgName()+"') b where a.org_id_parent=b.org_id  ");
		}
		if(!"".equals(org.getOrgIdParent()) && org.getOrgIdParent() != null){
			sql.append(" select id,a.org_id,org_name,org_desc,create_time,update_time,org_id_parent,org_level from ums_organization a, " +
					"(select org_id from ums_organization  where org_id_parent='"+org.getOrgIdParent()+"' ) b where a.org_id_parent=b.org_id  ");
		}
		if (!"".equals(org.getOrgLevel()) && org.getOrgLevel() !=null ) {
			sql.append(" select id,a.org_id,org_name,org_desc,create_time,update_time,org_id_parent,org_level from ums_organization a, " +
					"(select org_id from ums_organization  where org_level='"+org.getOrgLevel()+"' ) b where a.org_id_parent=b.org_id  ");
		}
		sql.append(")a ");
		return querylist(sql.toString());
	}

}
