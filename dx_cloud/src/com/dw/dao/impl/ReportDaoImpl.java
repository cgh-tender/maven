package com.dw.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dw.common.FiledProcFilter;
import com.dw.common.base.TempJdbcDao;
import com.dw.dao.IReportDao;

@Repository
public class ReportDaoImpl extends TempJdbcDao implements IReportDao {

	public List getReportTableHeadList(Map<String, String> map) {
		StringBuffer sql = new StringBuffer(
				"select field_name_EN,field_name_CN from z_report_table_head_info where 1=1 ");
		if (map != null) {
			if (map.get("report_ID") != null && !"".equals(map.get("report_ID") + "")) {
				sql.append("and report_ID='" + map.get("report_ID") + "'");
			}
		}
		return querylist(sql.toString());
	}

	public Map<String, String> getReportRunSql(Map<String, String> map) {
		StringBuffer sql = new StringBuffer("select * from z_report_table_data_sql where 1=1 ");
		if (map != null) {
			if (map.get("report_Name_EN") != null && !"".equals(map.get("report_Name_EN") + "")) {
				sql.append("and report_Name_EN='" + map.get("report_Name_EN") + "'");
			}
		}
		List<Map<String, String>> qlist = querylist(sql.toString());

		System.out.println("sql测试：" + sql);

		if (qlist != null && qlist.size() > 0) {
			return qlist.get(0);
		}
		return null;
	}

	public Map getUrlReportMapping(Map requestUrlmap) {
		String requestUrlMapping = requestUrlmap.get("requestUrlMapping") + "";
		String requestType1 = requestUrlmap.get("requestType1") + "";
		String requestType2 = requestUrlmap.get("requestType2") + "";
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT table_url FROM url_report_mapping t WHERE t.request_url = '" + requestUrlMapping
				+ "' AND t.request_type1 = '" + requestType1 + "' AND t.request_type2 = '" + requestType2 + "'");
		List<Map> result = querylist(sb.toString());
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public List<Map<String, String>> getReportFeldsQuery(Map<String, String> map) {
		StringBuffer sql = new StringBuffer("select * from z_report_table_field_query where 1=1 ");
		if (map != null) {
			if (map.get("report_ID") != null && !"".equals(map.get("report_ID") + "")) {
				sql.append(" and report_ID=" + map.get("report_ID") + "");
			}
			if (map.get("report_Name_EN") != null && !"".equals(map.get("report_Name_EN") + "")) {
				sql.append(" and report_Name_EN='" + map.get("report_Name_EN") + "'");
			}
			if (map.get("field_Name_EN") != null && !"".equals(map.get("field_Name_EN") + "")) {
				sql.append(" and field_Name_EN='" + map.get("field_Name_EN") + "'");
			}
		}
		return querylist(sql.toString());
	}

	public List<Map<String, String>> getReportDataBySql(String sql) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dt = sdf.format(new Date());

		int allbreaknum = 0;
		int allbreaktime = 0;
		if (sql.contains("wenJianTongJi")) {
			List<Map<String, String>> resultfile = querylist(sql);// 总文件个数和大小
			for (Map p : resultfile) {
				String provstr = p.get("prov_id").toString();
				if (!provstr.equals("all")) {
					String datasql = "SELECT  dt,time_span,file_num FROM nsd_count_file_num_size n WHERE data_type = 'ac' AND dt = '"
							+ dt + "' AND n.prov_id = '" // AND n.file_num > '0'
							+ provstr + "' ORDER BY time_span";
					List<Map> timespans = querylist(datasql);
//					if(timespans.size() == 0){
// 						StringBuilder sb = new StringBuilder();
//						sb.append("SELECT dt, MAX(time_span) time_span FROM nsd_count_file_num_size n WHERE data_type = 'ac' AND dt = '"+dt+"'  AND n.file_num = '0'  AND n.prov_id = '"+provstr+"' ");
//						timespans =  querylist(sb.toString());
//						Map zerop = new HashMap();
//						zerop.put("dt",dt);
//						zerop.put("time_span","00:00");
// 						timespans.add(0, zerop);
// 					} 
					Map numtimes = getTimeSpanMinute(timespans);
					p.put("breaknum", numtimes.get("breaknum"));
					p.put("breaktime", numtimes.get("breaktime"));
					allbreaknum += Integer.parseInt(numtimes.get("breaknum") + "");
					allbreaktime += Integer.parseInt(numtimes.get("breaktime") + "");

				}
			}
			resultfile.get(0).put("breaknum", allbreaknum + "");
			resultfile.get(0).put("breaktime", allbreaktime + "");
			return resultfile;
		} else {
			return querylist(sql);
		}
	}

	@Override
	public Boolean checkJurExist(String roleId, String key) {
		StringBuilder sb = new StringBuilder();

		if (roleId.equals("1501793717970")) {
			return true;
		} else {
			sb.append(
					"SELECT t2.jur_key FROM ums_role_jur t,ums_jur t2 WHERE t.jur_id = t2.jur_id  AND t2.jur_key = '");
			sb.append(key).append("' AND t.role_id = '").append(roleId).append("'");
			List<Map> result = querylist(sb.toString());
			if (result.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public Boolean checkInterFaceExist(String roleId, String url) {
		StringBuilder sb = new StringBuilder();

		if (roleId.equals("1501793717970")) {
			return true;
		} else {
			sb.append("SELECT t1.id  FROM  ums_role_menu t1,  ums_interface t2  WHERE t1.role_id = '");
			sb.append(roleId).append("'  AND t1.menu_id = t2.menu_id   AND t2.interface_address LIKE '%");
			sb.append(url);
			sb.append("%' UNION ALL SELECT id FROM ums_white_list WHERE url LIKE '%");
			sb.append(url).append("%'");
			List<Map> result = querylist(sb.toString());
			if (result.size() > 0) {
				return true;
			} else {
				return false;
			}
		}

	}

	public List reportCrmUserCount(String sql) {
		List<Map> result = querylist(sql);
		return result;
	}

	/***
	 * crm_user_count的head
	 */
	@SuppressWarnings("unchecked")
	public List reportCrmUserCountHead(String sql) {
		List<Map> result = querylist(sql);
		return result;
	}

	// 统计分钟时间间隔
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public Map getTimeSpanMinute(List<Map> timespans) {

		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd HH:mm");
		String today = timespans.get(0).get("dt") + " ";
		Date d1;
		Date d2;
		String str1 = null;
		String str2 = null;
		int breaknum = 0;
		int breaktime = 0;
// 		int flag=0;
		try {
			ArrayList nofile = new ArrayList();
			ArrayList file = new ArrayList();// dt,time_span,file_num
			Boolean isadd = false;
			for (int i = 0; i < timespans.size(); i++) {
				if (Integer.parseInt(timespans.get(i).get("file_num").toString()) == 0 && i == timespans.size() - 1) {
					nofile.add(timespans.get(i).get("time_span").toString());
					file.clear();
					isadd = true;
				} else if (Integer.parseInt(timespans.get(i).get("file_num").toString()) == 0) {
					nofile.add(timespans.get(i).get("time_span").toString());
					file.clear();
					isadd = false;
				} else if (Integer.parseInt(timespans.get(i).get("file_num").toString()) != 0) {
					file.add(timespans.get(i).get("time_span").toString());
					isadd = true;
				}
				if (isadd && nofile.size() != 0) {
					Boolean a = false;
					String min = nofile.get(0) + "";
					if ("00:00".equals(min)) {
						str1 = today + "00:00";
						if (file.size() == 0) {
							a = true;
							str2 = today + nofile.get(nofile.size() - 1) + "";
						} else {
							str2 = today + file.get(0) + "";
						}
					} else {
						str1 = today + nofile.get(0) + "";
						if (file.size() == 0) {
							a = true;
							str2 = today + nofile.get(nofile.size() - 1) + "";
						} else {
							str2 = today + file.get(0) + "";
						}
					}
					d1 = simpleFormat.parse(str1);
					d2 = simpleFormat.parse(str2);
					long from = d1.getTime();
					long to = d2.getTime();
					int minutes = (int) ((to - from) / (60000)); // 转为分钟
					if (a) {
						breaknum++;
						breaktime += minutes;
					} else {
						if ("00:00".equals(min)) {
							if (minutes > 10) {
								breaknum++;
								breaktime += minutes - 10;
							}
						} else {
							if (minutes >= 10) {
								breaknum++;
								breaktime += minutes;
							}
						}
					}
					nofile.clear();
				}
			}
// 			for(int i=0;i<timespans.size()-1;i++){
// 			   String min=timespans.get(0).get("time_span")+"";
// 			   if(!"00:00".equals(min) && flag == 0 ){
//				   str1 = today+"00:00";
//				   str2 = today+timespans.get(i).get("time_span")+"";
//				   i--;
//				   flag++;
// 			   }else{
// 				  str1 = today+timespans.get(i).get("time_span")+"";
// 				  str2 = today+timespans.get(i+1).get("time_span")+"";
// 			   }
// 			   d1 = simpleFormat.parse(str1);
// 			   d2 = simpleFormat.parse(str2);
// 	 		   long from = d1.getTime();
// 			   long to = d2.getTime();
// 			   int minutes = (int) ((to - from)/(60000)) ; // 转为分钟
// 			   if(minutes > 10 || (!"00:00".equals(min) && flag == 0 ) ){
//				   breaknum++;
//				   breaktime+=minutes-10;
//  			   }
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map resultp = new HashMap();
		resultp.put("breaknum", breaknum);
		resultp.put("breaktime", breaktime);
		return resultp;
	}

	// 导出断传的分省的次数、时长
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> exportProvBreakNumAndTime(Map map) {
		String provIdSql = "SELECT DISTINCT prov_id FROM nsd_count_file_num_size t ";
		String startTime = map.get("startTime") + "";
		String endTime = map.get("endTime") + "";
		List<Map<String, String>> provIds = querylist(provIdSql);
		List<Map> result = new ArrayList();
		for (Map prov : provIds) {
			String provId = prov.get("prov_id") + "";
			String dataSql = " SELECT  n.prov_id,n.prov_name,n.dt,n.time_span,n.file_num FROM nsd_count_file_num_size n WHERE data_type = 'ac' AND prov_id ='"
					+ provId + "' AND  dt >= '" + startTime + "' AND dt <='" + endTime
					+ "'  ORDER BY dt ASC,time_span ASC ";
			// AND n.file_num > '0'
			List<Map> OneProvDate = querylist(dataSql);
			List<Map> oneProvresultList = getTimeBreakTime(OneProvDate);
			result.addAll(oneProvresultList);
		}
		return result;
	}

	// 返回断传时间段 10 分钟
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getTimeBreakTime(List<Map> timespans) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd HH:mm");
		Date d1;
		Date d2;
		String str1 = null;
		String str2 = null;
		int flag = 0;
		int breaktime = 0;
		String today = timespans.get(0).get("dt") + " ";
		String prov_id = timespans.get(0).get("prov_id") + "";
		String prov_name = timespans.get(0).get("prov_name") + "";
		List<Map> result = new ArrayList<Map>();
		Map thistimeBreakp = new HashMap();

		try {
			ArrayList nofile = new ArrayList();
			ArrayList file = new ArrayList();
			Boolean addMap = false;
			Boolean isadd = false;
			for (int i = 0; i < timespans.size(); i++) {
				thistimeBreakp = new HashMap();
				thistimeBreakp.put("prov_id", prov_id);
				thistimeBreakp.put("prov_name", prov_name);
				addMap = false;
				if (Integer.parseInt(timespans.get(i).get("file_num").toString()) == 0 && i == timespans.size() - 1) {
					nofile.add(timespans.get(i).get("time_span").toString());
					file.clear();
					isadd = true;
				} else if (Integer.parseInt(timespans.get(i).get("file_num").toString()) == 0) {
					nofile.add(timespans.get(i).get("time_span").toString());
					file.clear();
					isadd = false;
				} else if (Integer.parseInt(timespans.get(i).get("file_num").toString()) != 0) {
					file.add(timespans.get(i).get("time_span").toString());
					isadd = true;
				}
				if (isadd && nofile.size() != 0) {
					Boolean a = false;
					String min = nofile.get(0) + "";
					if ("00:00".equals(min)) {
						str1 = today + "00:00";
						if (file.size() == 0) {
							a = true;
							str2 = today + nofile.get(nofile.size() - 1) + "";
						} else {
							str2 = today + file.get(0) + "";
						}
					} else {
						str1 = today + nofile.get(0) + "";
						if (file.size() == 0) {
							a = true;
							str2 = today + nofile.get(nofile.size() - 1) + "";
						} else {
							str2 = today + file.get(0) + "";
						}
					}
					d1 = simpleFormat.parse(str1);
					d2 = simpleFormat.parse(str2);
					long from = d1.getTime();
					long to = d2.getTime();
					long startimelong = from;// 加上10分钟
					Date startDate = new Date(startimelong);
					String startTime = simpleFormat.format(startDate);
					if ("00:00".equals(min)) {
						long startimelong2 = from + 600000;// 加上10分钟
						Date startDate2 = new Date(startimelong2);
						String startTime2 = simpleFormat.format(startDate2);
						thistimeBreakp.put("breakStartTime", getFormatDate(startTime2).substring(11, 16));
					} else {
						thistimeBreakp.put("breakStartTime", getFormatDate(startTime).substring(11, 16));
					}
					thistimeBreakp.put("breakEndTime", getFormatDate(str2).substring(11, 16));
					thistimeBreakp.put("breakDate", getFormatDate(str2).substring(0, 10));
					int minutes = (int) ((to - from) / (60000)); // 转为分钟
					breaktime = 0;
					if (a) {
						breaktime += minutes;
						addMap = true;
					} else {
						if ("00:00".equals(min)) {
							if (minutes > 10) {
								breaktime += minutes - 10;
								addMap = true;
							}
						} else {
							if (minutes >= 10) {
								breaktime += minutes;
								addMap = true;
							}
						}
					}
					nofile.clear();
					thistimeBreakp.put("breakTimeLength", breaktime);
				}
				if (isadd && addMap)
					result.add(thistimeBreakp);
//				else if(!isadd || nofile.size() == 1) {
//	 				result.add(thistimeBreakp);
//	 			}
			}

//  			for(int i=0;i<timespans.size()-1;i++){
// 				Map thistimeBreakp = new HashMap();
// 				int breaktime = 0;
// 			   String min=timespans.get(0).get("time_span")+"";
// 			   
// 			   String data1 = timespans.get(i).get("dt")+"";
// 			   String data2 = timespans.get(i+1).get("dt")+"";
//
// 			   if(!"00:00".equals(min) && flag == 0 ){
//				   str1 = data1+" "+"00:00";
//				   str2 = data1+" "+timespans.get(i).get("time_span")+"";
//				   i--;
//				   flag++;
// 			   }else{
// 				  str1 = data1+" "+timespans.get(i).get("time_span")+"";
// 				  str2 = data2+" "+timespans.get(i+1).get("time_span")+"";
// 			   }
// 			   d1 = simpleFormat.parse(str1);
// 			   d2 = simpleFormat.parse(str2);
// 	 		   long from = d1.getTime();
// 			   long to = d2.getTime();
// 			   int minutes = (int) ((to - from)/(60000)) ; // 转为分钟
// 			   if(minutes > 10 || (!"00:00".equals(min) && flag == 0 ) ){
// 				  thistimeBreakp.put("prov_id",prov_id);
// 				  thistimeBreakp.put("prov_name",prov_name);
//  				  breaktime = minutes-10;
// 				  thistimeBreakp.put("breakTimeLength",breaktime);
// 				  long startimelong = from + 600000;//加上10分钟
//  				  Date startDate = new Date(startimelong);
// 				  String startTime = simpleFormat.format(startDate);
// 				  
// 				  thistimeBreakp.put("breakStartTime",getFormatDate(startTime).substring(11, 16));
//   				  thistimeBreakp.put("breakEndTime",getFormatDate(str2).substring(11, 16));
//   				  thistimeBreakp.put("breakDate",getFormatDate(str2).substring(0, 10));
//
//   				  if(!data1.equals(data2)){
//   					  String time1 = str1;
//   					  String time2 = data1+" "+"24:00";
//   					  String time3 = str2;
//   					  long minute1 = simpleFormat.parse(time1).getTime();
//   					  long minute2 = simpleFormat.parse(time2).getTime();
//   					  long minute3 = simpleFormat.parse(time3).getTime();
//   	 			      int minutefirst = (int) ((minute2 - minute1)/(60000)) ; // 转为分钟
//   	 			      int minutesecond = (int) ((minute3 - minute2)/(60000)) ; // 转为分钟
//
//   	 			      if(minutefirst > 10){
//	   	 				thistimeBreakp = new HashMap();
//    	 			    thistimeBreakp.put("prov_id",prov_id);
//   	 			    	thistimeBreakp.put("prov_name",prov_name);
//   	 			    	breaktime = minutefirst-10;
//   	 			    	thistimeBreakp.put("breakTimeLength",breaktime);
//   	 			    	long minutefirstlong = minute1 + 600000;//加上10分钟
//   	 			    	startDate = new Date(minutefirstlong);
//   	 			    	startTime = simpleFormat.format(startDate);
//   	 			    	thistimeBreakp.put("breakStartTime",getFormatDate(startTime).substring(11, 16));
//   	   				  	thistimeBreakp.put("breakEndTime",getFormatDate(time2).substring(11, 16));
//     				    thistimeBreakp.put("breakDate",getFormatDate(startTime).substring(0, 10));
//    	   				  	
//   	   				  	
//     	   				result.add(thistimeBreakp);
//    	 			   }
//   	 			       
//	   	 			  if(minutesecond > 10){
//	   	 				    
//	   	 				    thistimeBreakp = new HashMap();
// 	 	 			    	thistimeBreakp.put("prov_id",prov_id);
//	 	 			    	thistimeBreakp.put("prov_name",prov_name);
//	 	 			    	breaktime = minutesecond-10;
//	 	 			    	thistimeBreakp.put("breakTimeLength",breaktime);
//	 	 			    	long minutesecondlong = minute2 + 600000;//加上10分钟
//	 	 			    	startDate = new Date(minutesecondlong);
//	 	 			    	startTime = simpleFormat.format(startDate);
//	 	 			    	thistimeBreakp.put("breakStartTime",getFormatDate(startTime).substring(11, 16));
//	 	   				  	thistimeBreakp.put("breakEndTime",getFormatDate(time3).substring(11, 16));
//	     				    thistimeBreakp.put("breakDate",getFormatDate(startTime).substring(0, 10));
// 	   	   				    result.add(thistimeBreakp);
//	  	 			   }
//   	 			      
//     				 }else{
//    	   				  result.add(thistimeBreakp);
//    		      }
//  				  continue;
//   			   }
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 格式化日期
	public static String getFormatDate(String date) {
		StringBuffer sb = new StringBuffer(date);
		sb.insert(4, "-");
		sb.insert(7, "-");
		return sb.toString();
	}

	/**
	 * 按小时的四个值统计
	 */
	@SuppressWarnings({ "unchecked", "unchecked", "rawtypes" })
	public List<Map> report_oth_fix_monitor_count(Map p) {
		StringBuilder sb = new StringBuilder();
		String dt = p.get("dt") + "";
		String fixType = p.get("fixType").toString();
		String[] type = fixType.split("_");
		if (fixType.endsWith("ALL")) { // 不区分类别
			if (null != type[0]) {
				sb.append("SELECT  dt,  prov_id, SUM(t.count) count,  ROUND(SUM(t.size), 3) size ");
				if ("FIX".equals(type[0])) {
					sb.append(",'固网汇总' data_ch,'FIX_ALL' data_en ");
				} else {
					sb.append(",'移动汇总' data_ch,'OTH_ALL' data_en ");
				}
				sb.append(" FROM  oth_fix_file_count t  WHERE t.data_en LIKE '" + type[0] + "_%' ")
						.append(" AND t.dt = '" + dt + "'  AND t.typ = 'b'  GROUP BY dt,  prov_id ");
			}
		} else {// 分类别
			sb.append("SELECT  dt, prov_id, data_en,t.data_ch, SUM(t.count) count,  ROUND(SUM(t.size),3) size ")
					.append("FROM oth_fix_file_count t WHERE t.data_en = '" + fixType + "'  AND t.dt = '" + dt + "' ")
					.append("AND t.typ = 'b' GROUP BY dt, prov_id, data_en, data_ch ");
		}
		List<Map> resultfile = querylist(sb.toString());// 总文件个数和大小
		String provstr = null;
		Long breaknumall = 0L;
		Long breaktimeall = 0L;
		for (Map provps : resultfile) {
			provstr = provps.get("prov_id").toString();
			if (fixType.endsWith("ALL")) { // 不区分类型
				Long breaknum4 = 0L;
				Long breaktime4 = 0L;
				if ("FIX".equals(type[0])) {
					String[] fixtype = { "FIX_CDR", "FIX_DDR", "FIX_MDR", "FIX_VDR" };
					for (int i = 0; i < fixtype.length; i++) {
						StringBuilder datasql = new StringBuilder();
						datasql.append(
								"SELECT * FROM ( SELECT t.dt,t.prov_id,t.data_en,t.data_ch,t.count,t.size,t.last_hour FROM oth_fix_file_count t WHERE t.dt = '"
										+ dt + "' AND t.prov_id = '");
						datasql.append(provstr).append("' AND t.data_en = '" + fixtype[i]
								+ "' and count >0 AND t.typ='b')a ORDER BY last_hour");
						List<Map> timespans = querylist(datasql.toString());
						if (timespans.size() == 0) {
						} else {
							Map numtimes = getTimeSpanHour(timespans);
							breaknum4 += Long.parseLong(numtimes.get("breaknum") + "");
							breaktime4 += Long.parseLong(numtimes.get("breaktime") + "");
						}
					} // for
				} else if ("OTH".equals(type[0])) {
					String[] othtype = { "OTH_CDR", "OTH_DDR", "OTH_MDR", "OTH_VDR" };
					for (int i = 0; i < othtype.length; i++) {
						StringBuilder datasql = new StringBuilder();
						datasql.append(
								"SELECT * FROM ( SELECT t.dt,t.prov_id,t.data_en,t.data_ch,t.count,t.size,t.last_hour FROM oth_fix_file_count t WHERE t.dt = '"
										+ dt + "' AND t.prov_id = '");
						datasql.append(provstr).append("' AND t.data_en = '" + othtype[i]
								+ "' and count >0  AND t.typ='b')a ORDER BY last_hour");
						List<Map> timespans = querylist(datasql.toString());
						if (timespans.size() == 0) {
						} else {
							Map numtimes = getTimeSpanHour(timespans);
							breaknum4 += Long.parseLong(numtimes.get("breaknum") + "");
							breaktime4 += Long.parseLong(numtimes.get("breaktime") + "");
						}
					} // for
				} // else
				provps.put("breaknum", breaknum4);
				provps.put("breaktime", breaktime4);
				breaknumall += breaknum4;
				breaktimeall += breaktime4;

			} else { // 区分类型
				StringBuilder datasql2 = new StringBuilder();
				datasql2.append(
						"SELECT * FROM ( SELECT t.dt,t.prov_id,t.data_en,t.data_ch,t.count,t.size,t.last_hour FROM oth_fix_file_count t WHERE t.dt = '"
								+ dt + "' AND t.prov_id = '")
						.append(provstr)
						.append("' AND t.data_en = '" + fixType + "' and count >0 AND t.typ='b')a ORDER BY last_hour");
				List<Map> timespans = querylist(datasql2.toString());
				Map numtimes = getTimeSpanHour(timespans);
				provps.put("breaknum", numtimes.get("breaknum"));
				provps.put("breaktime", numtimes.get("breaktime"));
				breaknumall += Long.parseLong(numtimes.get("breaknum") + "");
				breaktimeall += Long.parseLong(numtimes.get("breaktime") + "");
			} // else
		}

		for (int j = resultfile.size() - 1; j >= 0; j--) {
			String provId = resultfile.get(j).get("prov_id") + "";
			if ("all".equals(provId)) {
				resultfile.get(j).put("breaknum", breaknumall);
				resultfile.get(j).put("breaktime", breaktimeall);
				break;
			}
		}
		return resultfile;
	}

	// 按小时统计
	public static Map getTimeSpanHour(List<Map> hourspans) {

		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd HH");
		String today = hourspans.get(0).get("dt") + " ";
		Date d1;
		Date d2;
		String str1 = null;
		String str2 = null;
		int breaknum = 0;
		int breaktime = 0;
		int flag = 0;
		try {
			for (int i = 0; i < hourspans.size() - 1; i++) {
				String min = hourspans.get(0).get("last_hour") + "";
				if (!"00".equals(min) && flag == 0) {
					str1 = today + "00";
					str2 = today + hourspans.get(i).get("last_hour") + "";
					i--;
					flag++;
				} else {
					str1 = today + hourspans.get(i).get("last_hour") + "";
					str2 = today + hourspans.get(i + 1).get("last_hour") + "";
				}
				d1 = simpleFormat.parse(str1);
				d2 = simpleFormat.parse(str2);
				long from = d1.getTime();
				long to = d2.getTime();
				int hour = (int) ((to - from) / (3600000)); // 转为小时
				if (hour > 1 || (!"00".equals(min) && flag == 0)) {
					breaknum++;
					breaktime += hour - 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map resultp = new HashMap();
		resultp.put("breaknum", breaknum);
		resultp.put("breaktime", breaktime);
		return resultp;
	}

	/**
	 * 导出
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> export_report_oth_fix_monitor_count(Map map) {

		String provIdSql = "SELECT DISTINCT prov_id FROM oth_fix_file_count t  ";
		String startTime = map.get("startTime") + "";
		String endTime = map.get("endTime") + "";
		String dataType = map.get("dataType") + "";

		List<Map<String, String>> provIds = querylist(provIdSql);
		List<Map> result = new ArrayList();
		for (Map prov : provIds) {
			String provId = prov.get("prov_id") + "";
			if (dataType.endsWith("ALL")) {
				String[] types = dataType.split("_");
				String[] othfixtype = new String[4];
				if (null != types[0]) {
					if ("FIX".equals(types[0])) {
						othfixtype = new String[] { "FIX_CDR", "FIX_DDR", "FIX_MDR", "FIX_VDR" };
					} else {
						othfixtype = new String[] { "OTH_CDR", "OTH_DDR", "OTH_MDR", "OTH_VDR" };
					}
					for (int i = 0; i < othfixtype.length; i++) {
						String dataSql = " SELECT  t.dt, t.prov_id,t2.prov_name, t.data_en, t.data_ch, t.last_hour FROM oth_fix_file_count t  LEFT JOIN prov_code t2 ON t.prov_id = t2.oth_prov_id   WHERE t.dt >=  '"
								+ startTime + "'  AND t.dt <= '" + endTime + "' AND t.prov_id = '" + provId
								+ "'  AND t.data_en = '" + othfixtype[i]
								+ "' and  t.count > 0  AND t.typ = 'b' ORDER BY dt ASC,last_hour ASC";
						List<Map> OneProvDate = querylist(dataSql);
						if (OneProvDate.size() != 0) {
							List<Map> oneProvresultList = getTimeBreakHourTime(OneProvDate);
							result.addAll(oneProvresultList);
						}
					}
				}
			} else {
				String dataSql = " SELECT  t.dt, t.prov_id,t2.prov_name, t.data_en, t.data_ch, t.last_hour FROM oth_fix_file_count t  LEFT JOIN prov_code t2 ON t.prov_id = t2.oth_prov_id   WHERE t.dt >=  '"
						+ startTime + "'  AND t.dt <= '" + endTime + "' AND t.prov_id = '" + provId
						+ "'  AND t.data_en = '" + dataType
						+ "' and  t.count > 0  AND t.typ = 'b' ORDER BY dt ASC,last_hour ASC";
				List<Map> OneProvDate = querylist(dataSql);
				System.out.println("OneProvDate-----" + OneProvDate.size());
				if (OneProvDate.size() != 0) {
					List<Map> oneProvresultList = getTimeBreakHourTime(OneProvDate);
					result.addAll(oneProvresultList);
				}

			}
		}
		System.out.println("---------" + result.size());
		return result;
	}

	// 返回断传时间段小时
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getTimeBreakHourTime(List<Map> timespans) {

		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd HH");
		Date d1;
		Date d2;
		String str1 = null;
		String str2 = null;
		int flag = 0;
		String prov_id = timespans.get(0).get("prov_id") + "";
		String data_en = timespans.get(0).get("data_en") + "";
		String data_ch = timespans.get(0).get("data_ch") + "";
		String prov_name = timespans.get(0).get("prov_name") + "";
		if ("all".equals(prov_id)) {
			prov_name = "全国";
		}
		List<Map> result = new ArrayList<Map>();
		try {
			String min = timespans.get(0).get("last_hour") + "";
			for (int i = 0; i < timespans.size() - 1; i++) {
				Map thistimeBreakp = new HashMap();
				int breaktime = 0;

				String data1 = timespans.get(i).get("dt") + "";
				String data2 = timespans.get(i + 1).get("dt") + "";

				if (!"00".equals(min) && flag == 0) {
					str1 = data1 + " " + "00";
					str2 = data1 + " " + timespans.get(i).get("last_hour") + "";
					i--;
					flag++;
				} else {
					str1 = data1 + " " + timespans.get(i).get("last_hour") + "";
					str2 = data2 + " " + timespans.get(i + 1).get("last_hour") + "";
				}
				if (i == 66) {
					System.out.println(1);
				}
				d1 = simpleFormat.parse(str1);
				d2 = simpleFormat.parse(str2);
				long from = d1.getTime();
				long to = d2.getTime();
				int hours = (int) ((to - from) / (3600000)); // 转为小时

				if (hours > 1 || (!"00".equals(min) && flag == 0)) {
					thistimeBreakp.put("prov_id", prov_id);
					thistimeBreakp.put("prov_name", prov_name);
					thistimeBreakp.put("data_en", data_en);
					thistimeBreakp.put("data_ch", data_ch);
					breaktime = hours - 1;
					thistimeBreakp.put("breakTimeLength", breaktime);
					long starttimelong = from + 3600000;// 加1小时
					Date startDate = new Date(starttimelong);
					String startTime = simpleFormat.format(startDate);
					thistimeBreakp.put("breakStartTime", getFormatDate(startTime).substring(11, 13) + ":00");
					thistimeBreakp.put("breakEndTime", getFormatDate(str2).substring(11, 13) + ":00");
					thistimeBreakp.put("breakDate", getFormatDate(str2).substring(0, 10));

					if (!data1.equals(data2)) {
						String time1 = str1;
						String time2 = data1 + " " + "24";
						String time3 = str2;
						long minute1 = simpleFormat.parse(time1).getTime();
						long minute2 = simpleFormat.parse(time2).getTime();
						long minute3 = simpleFormat.parse(time3).getTime();
						int hoursfirst = (int) ((minute2 - minute1) / (3600000)); // 转为小时
						int hourssecond = (int) ((minute3 - minute2) / (3600000));// 转为小时

						if (hoursfirst > 1) {
							thistimeBreakp = new HashMap();
							thistimeBreakp.put("prov_id", prov_id);
							thistimeBreakp.put("prov_name", prov_name);
							thistimeBreakp.put("data_en", data_en);
							thistimeBreakp.put("data_ch", data_ch);
							breaktime = hoursfirst - 1;
							thistimeBreakp.put("breakTimeLength", breaktime);
							long hoursfirstlong = minute1 + 3600000;// 加1小时
							startDate = new Date(hoursfirstlong);
							startTime = simpleFormat.format(startDate);
							thistimeBreakp.put("breakStartTime", getFormatDate(startTime).substring(11, 13) + ":00");
							thistimeBreakp.put("breakEndTime", getFormatDate(time2).substring(11, 13) + ":00");
							thistimeBreakp.put("breakDate", getFormatDate(startTime).substring(0, 10));
							result.add(thistimeBreakp);
						}
						if (hourssecond > 1) {
							thistimeBreakp = new HashMap();
							thistimeBreakp.put("prov_id", prov_id);
							thistimeBreakp.put("prov_name", prov_name);
							thistimeBreakp.put("data_en", data_en);
							thistimeBreakp.put("data_ch", data_ch);
							breaktime = hourssecond - 1;
							thistimeBreakp.put("breakTimeLength", breaktime);
							long hourssecondlong = minute2 + 3600000;// 加1小时
							startDate = new Date(hourssecondlong);
							startTime = simpleFormat.format(startDate);
							thistimeBreakp.put("breakStartTime", getFormatDate(startTime).substring(11, 13) + ":00");
							thistimeBreakp.put("breakEndTime", getFormatDate(time3).substring(11, 13) + ":00");
							thistimeBreakp.put("breakDate", getFormatDate(startTime).substring(0, 10));
							result.add(thistimeBreakp);
						}
					} else {
						result.add(thistimeBreakp);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getUrlFoledMapping(Map<String, String> map) {
		StringBuffer sql = new StringBuffer(
				"select TABLE_NAME,COLUMN_NAME,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,IS_NULLABLE,COLUMN_COMMENT from information_schema.COLUMNS where 1=1 ");
		if (map != null) {
			if (map.get("tableName") != null && !"".equals(map.get("tableName") + "")) {
				sql.append(" and TABLE_NAME='" + map.get("tableName") + "'");
			}
			return querylist(sql.toString());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getUrlFileds(Map<String, String> map, String eg_nm) {
		FiledProcFilter instance = new FiledProcFilter();
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("tableName", map.get("tableName"));
		List<Map<String, String>> urlFoledMapping = getUrlFoledMapping(map2);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < urlFoledMapping.size(); i++) {
			Map<String, String> map3 = urlFoledMapping.get(i);
			list.add(map3.get("COLUMN_NAME"));
		}
		instance.setFiledName(list);
		String dt = instance.getdt();
		String tb = instance.getBhb();
		StringBuffer sql = new StringBuffer("SELECT " + eg_nm + " from  ");
		if (map != null) {
			if (map.get("tableName") != null && !"".equals(map.get("tableName") + "")) {
				sql.append(map.get("tableName") + " where 1 = 1 ");
				if (map.get("hiveName") != null && !"".equals(map.get("hiveName") + "")) {
					sql.append(" and " + tb + "='" + map.get("hiveName") + "'");
				}
				if (map.get("dt") != null && !"".equals("dt")) {
					String[] dts = map.get("dt").split("-");
					sql.append(" and " + dt + " >= '" + dts[0] + "' AND " + dt + " <= '" + dts[1] + "'");
				}
				sql.append(" group by " + eg_nm);
			}
			return querylist(sql.toString());
		}
		return null;
	}

	@Override
	public List<Map<String, String>> getProvCodeName() {
		StringBuffer sql = new StringBuffer("select crm_prov_id prov_id,prov_name,oth_prov_id from prov_code_new");
		return querylist(sql.toString());
	}

	@Override
	public List<Map<String, String>> getCityCodeName() {
		StringBuffer sql = new StringBuffer("select latn_id,latn_name from city_code");
		return querylist(sql.toString());
	}

	@Override
	public void maintainDS(String table_name) {
		try {
			String db = "bssadt";
//			String db = "yungongsi";
			Session session = getCurrentSession();
			Set<String> c = new HashSet<String>();
			Set<String> d = new HashSet<String>();
			Set<String> e = new HashSet<String>();
			if (table_name == null) {
				String sql = "select table_name from information_schema.`TABLES` where table_schema='" + db
						+ "' and table_type <> 'VIEW' group by table_name";
				List<Map<String, String>> reportDataBySql = getReportDataBySql(sql);
				for (int i = 0; i < reportDataBySql.size(); i++) {
					Map<String, String> a = reportDataBySql.get(i);
					table_name = a.get("table_name");
					insert(table_name, c, d, e);
				}
			} else if ("drop".equals(table_name)) {
				String sql = "DELETE FROM ds_datasource_table where 1 = 1 and status=0";
				session.createSQLQuery(sql.toString()).executeUpdate();
				sql = "DELETE FROM ds_datasource_field where 1 = 1 and status=0";
				session.createSQLQuery(sql.toString()).executeUpdate();
			} else {
				String sql = "";
				sql = "select id from ds_datasource_table where 1 = 1 and table_nm='" + table_name + "'";
				List<Map<String, String>> r1 = getReportDataBySql(sql);
				if (r1.size() != 0) {
					for (int i = 0; i < r1.size(); i++) {
						String id = String.valueOf(r1.get(i).get("id"));
						sql = "update ds_datasource_table set status=0 where 1 = 1 and id=" + id;
						session.createSQLQuery(sql.toString()).executeUpdate();
						sql = "update ds_datasource_field set status=0 where 1 = 1 and table_id=" + id;
						session.createSQLQuery(sql.toString()).executeUpdate();
					}
				}
				insert(table_name, c, d, e);
			}
			if (c.size() > 0) {
				System.out.println("这些表已经录入: " + c.toString());
			}
			if (d.size() > 0) {
				System.out.println("这些表有hive表但无数据: " + d.toString());
			}
			if (e.size() > 0) {
				System.out.println("新录入的表是: " + e.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void insert(String table_name, Set<String> c, Set<String> d, Set<String> e) {
		try {
			String db = "bssadt";
//			String db = "yungongsi";
			Session session = getCurrentSession();
			String sql = "";
			sql = " select column_name field_code,column_name field_name,data_type field_type,character_maximum_length field_len"
					+ " from information_schema.columns " + " where table_schema = '" + db + "' and extra = ''"
					+ " and table_name = '" + table_name + "' and column_name not in ('id')";

			FiledProcFilter instance = new FiledProcFilter();
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("tableName", table_name);
			List<Map<String, String>> urlFoledMapping = getUrlFoledMapping(map2);
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < urlFoledMapping.size(); i++) {
				Map<String, String> map3 = urlFoledMapping.get(i);
				list.add(map3.get("COLUMN_NAME"));
			}
			instance.setFiledName(list);
			String dt = instance.getdt();
			String tb = instance.getBhb();

			List<Map<String, String>> report1 = getReportDataBySql(sql);
			List<String> dataList = getDataList(report1, "field_code");
			int index = dataList.indexOf(tb);
			// 是否存在有效的信息.
			sql = "select * from ds_datasource_table where 1 = 1 and table_nm='" + table_name + "' and status = 1";
			List<Map<String, String>> report2 = getReportDataBySql(sql);

			if (report2.size() > 0) {// 有不插入
				c.add(table_name);
			} else {// 无进行写入信息
				if (index != -1) {
					sql = "select " + tb + " from " + table_name + " group by " + tb;
					List<String> dataList2 = getDataList(getReportDataBySql(sql), tb);
					if (dataList2.size() == 0) {
						sql = "insert into ds_datasource_table (table_nm,hive_nm,status)values ";
						sql += "('" + table_name + "','',1)";
						int num = session.createSQLQuery(sql.toString()).executeUpdate();
						if (num != 0) {
							sql = "select id from ds_datasource_table where status = 1 and  table_nm='" + table_name
									+ "'";
							List<Map<String, String>> users = querylist(sql);
							String id = String.valueOf(users.get(0).get("id"));

							String sql4 = "insert into ds_datasource_field (table_id,field_code,field_name,filed_type,field_len,status)values ";
							for (int ii = 0; ii < report1.size(); ii++) {
								if (ii != 0)
									sql4 += ",";
								Map<String, String> b = report1.get(ii);
								sql4 += "(" + id + ",'" + b.get("field_code") + "','" + b.get("field_name") + "','"
										+ b.get("field_type") + "','"
										+ String.valueOf((b.get("field_len") == null ? "" : b.get("field_len")))
										+ "',1)";
							}
							num = session.createSQLQuery(sql4.toString()).executeUpdate();
							e.add(table_name);
						}
						d.add(table_name);
					} else {
						e.add(table_name);
						for (int j = 0; j < dataList2.size(); j++) {
							String sql1 = "insert into ds_datasource_table (table_nm,hive_nm,status)values ";
							sql1 += "('" + table_name + "','" + dataList2.get(j) + "',1)";
							int num = session.createSQLQuery(sql1.toString()).executeUpdate();
							e.add(table_name);
							if (num != 0) {
								sql = "select id from ds_datasource_table where status = 1 and table_nm='" + table_name
										+ "' and hive_nm='" + dataList2.get(j) + "'";
								List<Map<String, String>> users = querylist(sql);
								String id = String.valueOf(users.get(0).get("id"));

								String sql4 = "insert into ds_datasource_field (table_id,field_code,field_name,filed_type,field_len,status)values ";
								for (int ii = 0; ii < report1.size(); ii++) {
									if (ii != 0)
										sql4 += ",";
									Map<String, String> b = report1.get(ii);
									sql4 += "(" + id + ",'" + b.get("field_code") + "','" + b.get("field_name") + "','"
											+ b.get("field_type") + "','"
											+ String.valueOf((b.get("field_len") == null ? "" : b.get("field_len")))
											+ "',1)";
								}
								num = session.createSQLQuery(sql4.toString()).executeUpdate();
							}
						}
					}
				} else {
					sql = "insert into ds_datasource_table (table_nm,hive_nm,status)values ";
					sql += "('" + table_name + "','',1)";
					int num = session.createSQLQuery(sql.toString()).executeUpdate();
					if (num != 0) {
						sql = "select id from ds_datasource_table where status = 1 and  table_nm='" + table_name + "'";
						List<Map<String, String>> users = querylist(sql);
						String id = String.valueOf(users.get(0).get("id"));

						String sql4 = "insert into ds_datasource_field (table_id,field_code,field_name,filed_type,field_len,status)values ";
						for (int ii = 0; ii < report1.size(); ii++) {
							if (ii != 0)
								sql4 += ",";
							Map<String, String> b = report1.get(ii);
							sql4 += "(" + id + ",'" + b.get("field_code") + "','" + b.get("field_name") + "','"
									+ b.get("field_type") + "','"
									+ String.valueOf((b.get("field_len") == null ? "" : b.get("field_len"))) + "',1)";
						}
						num = session.createSQLQuery(sql4.toString()).executeUpdate();
						e.add(table_name);
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public List<String> getDataList(List<Map<String, String>> list, String field_code) {
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> a = list.get(i);
			l.add(a.get(field_code));
		}
		return l;
	}

	@Override
	public List<Map<String, String>> getDirectFiledMapping(Map<String, String> map) {
		StringBuffer sql = new StringBuffer(
				"select b.field_code,b.field_name,b.filed_as_Map,b.filed_type,a.typechart from (select * from ds_datasource_table where 1 = 1 ");
		if (map != null) {// table_nm='' and hive_nm=''
			if (map.get("tableName") != null && !"".equals(map.get("tableName") + "")) {
				sql.append(" and table_nm='" + map.get("tableName") + "'");
			}
			if (map.get("hiveName") != null && !"".equals(map.get("hiveName") + "")) {
				sql.append(" and hive_nm='" + map.get("hiveName") + "'");
			}
			sql.append(
					") a,ds_datasource_field b where a.id = b.table_id and a.`status`=1 and b.`status`=1 ORDER BY b.sort");
			return querylist(sql.toString());
		}
		return null;
	}
}
