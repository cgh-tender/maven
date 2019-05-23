package com.dw.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Repository;

import com.dw.common.base.TempJdbcDao;
import com.dw.dao.PushDao;
import com.dw.model.PushMenu;

@Repository
public class PushDaoImpl extends TempJdbcDao implements PushDao {

	@Override
	public int delReal(PushMenu menu) {
		Session session=getCurrentSession();
		try {
			String dt = menu.getDt();
			List<PushMenu> data = menu.getData();
			for (PushMenu map : data) {
				StringBuffer sql = new StringBuffer("INSERT INTO fix_real_count_d (dt, day, "
						+ "prov_id, dataType, dataSubType, business_types, type, count30, "
						+ "count60, count120, count180, count300, count600, count1200, count1800, "
						+ "countMax, averageTime)");
				sql.append(" VALUES ("
						+ "'" + dt + "', "
						+ "'" + map.getDay() + "', "
						+ "'" + map.getProv_id() + "',"
						+ "'" + map.getDatatype() + "',"
						+ "'" + map.getDatasubtype() + "',"
						+ "'" + map.getBusiness_types() + "',"
						+ "'" + map.getType() + "',"
						+ "'" + map.getCount30() + "',"
						+ "'" + map.getCount60() + "',"
						+ "'" + map.getCount120() + "',"
						+ "'" + map.getCount180() + "',"
						+ "'" + map.getCount300() + "',"
						+ "'" + map.getCount600() + "',"
						+ "'" + map.getCount1200() + "',"
						+ "'" + map.getCount1800() + "',"
						+ "'" + map.getCountmax() + "',"
						+ "'" + map.getAveragetime() + "'"
						+ ")");
				session.createSQLQuery(sql.toString()).executeUpdate(); 
			}
			return 1;
		}catch (ServiceException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int NondelReal(PushMenu menu) {
		Session session=getCurrentSession();
		try {
			String dt = menu.getDt();
			List<PushMenu> data = menu.getData();
			for (PushMenu map : data) {
				StringBuffer sql = new StringBuffer("INSERT INTO fix_real_count (dt, day, "
						+ "prov_id, dataType, dataSubType, business_types, type, count3, "
						+ "count5, count10, count20, count30, count60, count180, count300, "
						+ "countMax, averageTime)");
				sql.append(" VALUES ("
						+ "'" + dt + "', "
						+ "'" + map.getDay() + "', "
						+ "'" + map.getProv_id() + "',"
						+ "'" + map.getDatatype() + "',"
						+ "'" + map.getDatasubtype() + "',"
						+ "'" + map.getBusiness_types() + "',"
						+ "'" + map.getType() + "',"
						+ "'" + map.getCount3() + "',"
						+ "'" + map.getCount5() + "',"
						+ "'" + map.getCount10() + "',"
						+ "'" + map.getCount20() + "',"
						+ "'" + map.getCount30() + "',"
						+ "'" + map.getCount60() + "',"
						+ "'" + map.getCount180() + "',"
						+ "'" + map.getCount300() + "',"
						+ "'" + map.getCountmax() + "',"
						+ "'" + map.getAveragetime() + "'"
						+ ")");
				session.createSQLQuery(sql.toString()).executeUpdate(); 
			}
			return 1;
		}catch (ServiceException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int fail(PushMenu menu) {
		Session session=getCurrentSession();
		try {
			String dt = menu.getDt();
			List<PushMenu> data = menu.getData();
			for (PushMenu map : data) {
				StringBuffer sql = new StringBuffer("INSERT INTO fix_fail_count (dt, day, "
						+ "fail_s, fail_e, fail_time)");
				sql.append(" VALUES ("
						+ "'" + dt + "', "
						+ "'" + map.getDay() + "', "
						+ "'" + map.getFail_s() + "',"
						+ "'" + map.getFail_e() + "',"
						+ "'" + map.getFail_time() + "'"
						+ ")");
				session.createSQLQuery(sql.toString()).executeUpdate(); 
			}
			return 1;
		}catch (ServiceException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int availableProc(PushMenu menu) {
		Session session=getCurrentSession();
		try {
			String dt = menu.getDt();
			List<PushMenu> data = menu.getData();
			for (PushMenu map : data) {
				StringBuffer sql = new StringBuffer("INSERT INTO fix_availableProc_count (dt, day, "
						+ "period_time, available_processes, availability)");
				sql.append(" VALUES ("
						+ "'" + dt + "', "
						+ "'" + map.getDay() + "', "
						+ "'" + map.getPeriod_time() + "',"
						+ "'" + map.getAvailable_processes() + "',"
						+ "'" + map.getAvailability() + "'"
						+ ")");
				session.createSQLQuery(sql.toString()).executeUpdate(); 
			}
			return 1;
		}catch (ServiceException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int failProc(PushMenu menu) {
		Session session=getCurrentSession();
		try {
			String dt = menu.getDt();
			List<PushMenu> data = menu.getData();
			for (PushMenu map : data) {
				StringBuffer sql = new StringBuffer("INSERT INTO fix_failProc_count (dt, day, "
						+ "period_time ,ip , process_id, fail_s, fail_e, fail_time)");
				sql.append(" VALUES ("
						+ "'" + dt + "', "
						+ "'" + map.getDay() + "',"
						+ "'" + map.getPeriod_time() + "',"
						+ "'" + map.getIp() + "',"
						+ "'" + map.getProcess_id() + "',"
						+ "'" + map.getFail_s() + "',"
						+ "'" + map.getFail_e() + "',"
						+ "'" + map.getFail_time() + "'"
						+ ")");
				session.createSQLQuery(sql.toString()).executeUpdate(); 
			}
			return 1;
		}catch (ServiceException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
