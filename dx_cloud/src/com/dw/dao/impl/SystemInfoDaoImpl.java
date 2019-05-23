package com.dw.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dw.common.StringUtil;
import com.dw.common.base.TempJdbcDao;
import com.dw.dao.SystemInfoDao;
import com.dw.model.Role;
import com.dw.model.SystemInfo;


@Repository
public class SystemInfoDaoImpl extends TempJdbcDao implements SystemInfoDao {

	@Override
	public SystemInfo addSystemInfo(SystemInfo systemInfo) {
		Session session = getCurrentSession();
		try {
			session.save(systemInfo);
			return systemInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SystemInfo getSystemInfo() {
		
  		Session session=getCurrentSession();
 		StringBuffer sql = new StringBuffer("select * from ums_systeminfo where 1=1 ");
  		return (SystemInfo)session.createSQLQuery(sql.toString()).addEntity(SystemInfo.class).list().get(0);
 		
	}

	@Override
	public Boolean updateSystemInfo(SystemInfo systemInfo) {
		Session session = getCurrentSession();
		try {
	  		 StringBuffer deletesql = new StringBuffer(" delete from ums_systeminfo ");
	  		 session.createSQLQuery(deletesql.toString()).executeUpdate(); 
			 session.save(systemInfo);
   			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
