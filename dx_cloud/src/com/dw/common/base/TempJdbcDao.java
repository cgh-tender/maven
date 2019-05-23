package com.dw.common.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.transform.Transformers;
import org.hibernate.jdbc.Work;

public class TempJdbcDao extends BaseDAOHibernate{
	private static Log logger = LogFactory.getLog(TempJdbcDao.class);
	public List querylist(String sql){
		Session session=getCurrentSession();
		Query q=session.createSQLQuery(sql);
		q.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		return   q.list();
	}
	/**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public List<Map<String, Object>> getColumnNames(final String sql) {
        final List<Map<String, Object>> columnNames = new ArrayList<Map<String,Object>>();
        //与数据库的连接
//        Session session=getCurrentSession();
        Session session=getNewSession();
        try {
			session.beginTransaction();
			session.doWork(new Work(){

				@Override
				public void execute(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement( sql );
			        ResultSet rs = ps.executeQuery();
			        try {
			        	Map<String, Object> field = new HashMap<String, Object>();
			            ResultSetMetaData metadata = rs.getMetaData();
			            int size = metadata.getColumnCount();
			            List<String> headList = new ArrayList<String>();
			            for (int i = 1; i <= size; i++) {
			            	headList.add(metadata.getColumnName(i));
			            	field.put("field", metadata.getColumnName(i));
			            	field.put("title", metadata.getColumnName(i));
			            	field.put("visible", true);
			            	field.put("sortable", true);
			            	columnNames.add(field);
			            	field = new HashMap<String, Object>();
			            }
			            String aaString = "";
			            for (int j = 0; j < headList.size(); j++) {
			            	aaString += aaString.equals("") ? headList.get(j) : "|" + headList.get(j);
						}
			            System.out.println(aaString);
			        }
			        finally {
			            doClose(null,ps,rs);
			        }
				}
				
			});
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.doClose(session, null, null);
			return columnNames;
		}
    }
    protected void doClose(Session session, Statement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
                rs=null;
            } catch (Exception ex) {
                rs=null;
//                log.error(ex,ex);
                ex.printStackTrace();
            }
        }
        // Statement对象关闭时,会自动释放其管理的一个ResultSet对象
        if(stmt != null){
            try {
                stmt.close();
                stmt=null;
            } catch (Exception ex) {
                stmt=null;
//                log.error(ex,ex);
                ex.printStackTrace();
            }
        }
//      当Hibernate的事务由Spring接管时,session的关闭由Spring管理.不用手动关闭
//      if(session != null){
//          session.close();
//      }
    }
    
    /**
	 * 进行封装 分页
	 * 
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public String buildPageSqlMySql(String sql, int start, int limit) {
		  StringBuilder pageSql = new StringBuilder();
		  pageSql.append("SELECT ").append(sql.trim().substring(7)).append(" limit ").append(start).append(",")
		    .append(limit).append(" ");
		  return pageSql.toString();
	}
	
	public String buildCountSqlMysql(String sql) throws Exception{
		StringBuilder countSql = new StringBuilder();
		countSql.append("SELECT concat(count(*),'') count from (").append(sql).append(") a where 1 = 1 ");
		List<Map<String, String>> questList = this.querylist(countSql.toString());
		return questList.get(0).get("count");
		 
	}
}
