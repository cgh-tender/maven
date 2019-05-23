package com.dw.servce.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.dw.common.FieldPublicFilter;
import com.dw.dao.IExportDao;
import com.dw.dao.IReportDao;
import com.dw.model.ExportMessage;
import com.dw.servce.IExportService;
import com.dw.util.TableHeadPropertiesUtils;

@Service("exportService")
@Transactional
public class ExportServiceImpl implements IExportService {
	private FieldPublicFilter instance = null;
	private static final Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);
	@Autowired
	private IExportDao exportDao;
	@Autowired
	private IReportDao reportDao;
	
	/**
	 * 测试接口
	 */
	@Override
	public void test(ExportMessage message) {
//		List<Map<String, Object>> test = exportDao.test(message);
//		message.setHeader(test);
	}
	
	/**
	 * 获取 FieldPublicFilter 并且初始化
	 */
	@Override
	public FieldPublicFilter getFieldPublicFilter() {
		try {
			if (instance == null) {
				instance = FieldPublicFilter.getInstance();
				try {
					HashMap<String, String> pv = instance.getPv();
					HashMap<String, String> ct = instance.getCt();
					if (pv == null || pv.size() == 0) {
						instance.setPv(reportDao.getProvCodeName());
						logger.info("create prov list!");
					}
					if (ct == null || ct.size() == 0) {
						instance.setCt(reportDao.getCityCodeName());
						logger.info("create city list!");
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return instance;
	}
	
	@Override
	public ExportMessage getFieldHead(ExportMessage message) throws Exception {
		try {
			getFieldPublicFilter();
			TableHeadPropertiesUtils tableHeadinstance = new TableHeadPropertiesUtils();
			String methodName = message.getMethod();
			// get Head
			String propertyValue = tableHeadinstance.getPropertyValue(methodName);
			if(instance.getEncoding(propertyValue).equals("ISO-8859-1")) {
				propertyValue = new String(propertyValue.getBytes("ISO-8859-1"),"UTF-8").toString();
			}
			List<Map<String,Object>> fieldMap = getFieldMap(propertyValue);
			message.setHeader(fieldMap);
			String sql = upSqlReplace(message);
			try {
//			reInvoke("getFieldHeadHibenate", message,new Class[]{ExportMessage.class, String.class}, new Object[]{message,sql});
			String total = (String) reInvoke("getCount",message,new Class[]{ExportMessage.class,String.class}, new Object[]{message, sql});
			HashMap<String,Object> req = message.getReq();
			req.put("total", total);
			message.setReq(req);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return message;
	}
	
	@Override
	public List<Map<String, Object>> getData(ExportMessage message) throws Exception{
		List<Map<String, Object>> data = null;
		try {
			String sql = upSqlReplace(message);
			data = exportDao.getData(message, sql);
			message.setData(data);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return data;
	}
	
	private List<Map<String, Object>> getFieldMap(String propertyValue) throws Exception{
		List<Map<String, Object>> listFieldMaps = new ArrayList<Map<String,Object>>();
		try {
			System.out.println(propertyValue);
			String[] fields = propertyValue.split("\\|");
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			for (int i = 0; i < fields.length; i++) {
				String filed = fields[i].trim();
				fieldMap.put("field", filed);
				fieldMap.put("title", filed);
				fieldMap.put("visible", true);
				fieldMap.put("sortable", true);
				listFieldMaps.add(fieldMap);
				fieldMap = new HashMap<String, Object>();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return listFieldMaps;
	}

	@Override
	public ExportMessage getCount(ExportMessage message) throws Exception {
		String count = "";
		String sql = "";
		try {
			sql = upSqlReplace(message);
			count = exportDao.getCount(message, sql);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("total", count);
			message.setReq(map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return message;
	}

	@Override
	public ExportMessage getFieldHeadHibenate(ExportMessage message) throws Exception {
		try {
			String sql = "";
			sql = upSqlReplace(message);
			exportDao.getFieldHeadHibenate(message, sql);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return message;
	}
	
	private String upSqlReplace(ExportMessage message) throws Exception{
		FieldPublicFilter instance = getFieldPublicFilter();
		String sql = "";
		try {
			Map<String, Object> reqMap = message.getReq();
			sql = getCacheSql(message);
			if(reqMap.get("dt") != null && reqMap.get("dt").toString().indexOf("-") != -1) {
				String[] dt = reqMap.get("dt").toString().split("-");
				sql = sql.replaceAll("\\$\\{star_dts\\}", dt[0].trim());
				sql = sql.replaceAll("\\$\\{end_dts\\}", dt[1].trim());
			}
			
			if (reqMap.get("prov_id") != null && reqMap.get("city_id") == null) {//不分地市的情况
				String provid = (String) reqMap.get("prov_id");
				if (provid.equals("pvct")) {
				} else if (provid.equals("allpv")) {
					sql += " and prov_id = 'allpv'";
				}else {
					sql += " and prov_id = '"+provid+"'";
				}
			}else if(reqMap.get("city_id") != null){//分地市的情况
				String provid = (String) reqMap.get("prov_id");
				String cityid = (String) reqMap.get("city_id");
				if(provid == null || cityid == null) {
					logger.info("请进行传入对应的 省份编码 和 地市编码");
					message.setCode(0);
					message.setMsg("请进行传入对应的 省份编码 和 地市编码");
				}else {
					if (provid.equals("pvct") && cityid.equals("allct")) {// 全部 + 全省 (n + 31 + 1) * dt
						// 由于 sql 已经把 全部 + 省份 + 地市的 已经写好了 不用进行操作
					} else if (provid.equals("allpv") && cityid.equals("allct")) { // 全国1 (汇总) * dt
						sql += " and prov_id = 'allpv' and city_id = 'allct'";
					} else if (!provid.equals("allpv") && cityid.equals("allct")) { // 省份 + 全省 ( 1(省份汇总) + n(地市) ) * dt
						sql += " and prov_id = '"+provid+"'";
					} else if (!cityid.equals("allct")) { // 省份 + 地市 1(单地市) * dt
						sql += " and city_id = '"+cityid+"'";
					}
				}
			}
			if(!message.getOrder().equals("")) {
				sql = "select * from ("+sql+") a " + message.getOrder() + " ";
			}
			List<String> bFexport = instance.getBFexport();
			for (String codeString : reqMap.keySet()) {
				if(!bFexport.contains(codeString)) {
					if(!reqMap.get(codeString).toString().toLowerCase().equals("all"))sql += " and "+codeString+" = '"+reqMap.get(codeString)+"'";
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sql;
	}
	
	private Object reInvoke(String methodName,ExportMessage message,Class[] classes,Object[] o)throws Exception{
		Object invokeMethod = null;
		try {
			Method method = ReflectionUtils.findMethod(exportDao.getClass(), methodName, classes);
	        invokeMethod = ReflectionUtils.invokeMethod(method, exportDao, o);
		} catch (Exception e) {
			logger.error(e.getMessage());
			message.setMsg(e.getMessage());
			message.setCode(0);
		}
		return invokeMethod;
    }
	public String getCacheSql(ExportMessage message) {
//		String sql = instance.getCacheSqlMap(message);
		String sql = "";
		String methodName = message.getMethod();
		try {
			sql = (String) reInvoke(methodName, message,new Class[]{ExportMessage.class}, new Object[]{message});
//			instance.setCacheSqlMap(message, sql);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.info("没有找到这个sql 方法 请确认");
		}
		return sql;
	}

	@Override
	public void findExport(ExportMessage message) throws Exception {
		try {
			getFieldPublicFilter();
			TableHeadPropertiesUtils tableHeadinstance = new TableHeadPropertiesUtils();
			String methodName = message.getMethod();
			// get Head
			String propertyValue = tableHeadinstance.getPropertyValue(methodName);
			if(instance.getEncoding(propertyValue).equals("ISO-8859-1")) {
				propertyValue = new String(propertyValue.getBytes("ISO-8859-1"),"UTF-8").toString();
			}
			List<Map<String,Object>> fieldMap = getFieldMap(propertyValue);
			message.setHeader(fieldMap);
			String sql = upSqlReplace(message);
			List<Map<String,Object>> data = exportDao.getData(message, sql);
			message.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
