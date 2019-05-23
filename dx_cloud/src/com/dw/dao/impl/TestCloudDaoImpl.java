package com.dw.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dw.common.base.TempJdbcDao;
import com.dw.dao.TestCloudDao;
import com.dw.model.testTree;

@Repository
public class TestCloudDaoImpl extends TempJdbcDao implements TestCloudDao {

	@Override
	public List<testTree> addSystemInfo(String unit_id) {
		StringBuffer sb = new StringBuffer();
		Session session=getCurrentSession();
		sb.append("SELECT *  from ( " + 
				"SELECT t.`CODE`,t.DISPLAY_NAME, IFNULL( SUM(order_count ), 0 ) order_count, " + 
				"IFNULL( SUM( finish_count ), 0 ) finish_count, " + 
				"ifnull( sum( unfinish_count ), 0 ) unfinish_count, " + 
				"FORMAT( SUM( finish_count ) / sum( order_count ) * 100, 2 ) processingRate  from ums_domain_temp t  " + 
				"LEFT JOIN RA_O_WORK_STATIS a on FIND_IN_SET(a.UNIT_ID, t.childOrgs) > 0 " + 
				"where t.PARENT_DOMAIN_CODE ='" + unit_id + "' " + 
				"GROUP BY t.`CODE` ) b " + 
				"WHERE b.order_count > 0  " + 
				"ORDER BY b.order_count DESC ");
		return session.createSQLQuery(sb.toString()).addEntity(testTree.class).list();
	}

	@Override
	public List<Map<String, String>> getcount(String unit_id) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT SUM(A.FINISH_COUNT) doneCount, SUM(A.UNFINISH_COUNT) unDoneCount ");
        builder.append("FROM RA_O_WORK_STATIS A ");
        builder.append("WHERE A.UNIT_ID='" + unit_id + "'");
		return querylist(builder.toString());
	}

	@Override
	public List<Map<String, String>> getuntreated(String unit_id) {
		StringBuilder builder = new StringBuilder();
        builder.append("SELECT b.DIR_NAME name, SUM( a.UNFINISH_COUNT ) value ");
        builder.append("FROM RA_O_WORK_STATIS a LEFT JOIN RA_M_DIR b ON b.DIR_ID = a.AUDIT_SN ");
        builder.append("WHERE A.UNIT_ID='").append(unit_id).append("'");
        builder.append("GROUP BY A.AUDIT_SN");
		return querylist(builder.toString());
	}

}
