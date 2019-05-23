package com.dw.servce;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IReportService {
	/**
	 * 获取稽核报表表头信息
	 * 
	 * @return
	 */
	public List<Map<String, String>> getReportTableHeadList(Map<String, String> map);

	/**
	 * 获取稽核报表执行sql
	 * 
	 * @param reportNameEN
	 * @return
	 */
	public Map<String, String> getReportRunSql(Map<String, String> map);

	/**
	 * 获取稽核报表查询字段
	 * 
	 * @param reportNameEN
	 * @return
	 */
	public List<Map<String, String>> getReportFeldsQuery(Map<String, String> map);

	/**
	 * 根据sql文件获取报表数据
	 * 
	 * @param sql
	 * @return
	 */
	public List getReportDataBySql(String sql);

	/***
	 * 通过key，roleid 判断是否存在
	 */
	public Boolean checkJurExist(String roleId, String key);

	/**
	 * 通过key值判断是否存在非法权限
	 */
	public Boolean checkInterFaceExist(String roleId, String url);

	/**
	 * crm_user_count
	 */
	public List reportCrmUserCount(Map pKeyValue);

	/***
	 * crm_user_count的head
	 */
	public List reportCrmUserCountHead(Map pKeyValue);

	/**
	 * 断传次数，时间段导出
	 */
	public List<Map> exportProvBreakNumAndTime(Map p);

	/**
	 * 按小时的四个值统计
	 */
	public List<Map> report_oth_fix_monitor_count(Map p);

	/**
	 * 导出
	 */
	public List<Map> export_report_oth_fix_monitor_count(Map p);

	/**
	 * 
	 * 请求路径映射
	 */
	public Map getUrlReportMapping(Map requestUrlmap);

	/**
	 * 
	 * @param 请求字段
	 * @return
	 */
	public List<String> getUrlFiledMapping(Map<String, String> requestUrlmap);

	/**
	 * 
	 * @param eg_nm 
	 * @param 请求空值率字段 特定
	 * @return
	 */
	public List<String> getUrlFileds(Map<String, String> requestUrlmap, String eg_nm);

	/**
	 * 获取省份编码
	 * 
	 * @return
	 */
	public List<Map<String, String>> getProvCodeName();

	/**
	 * 获取地市编码
	 * 
	 * @return
	 */
	public List<Map<String, String>> getCityCodeName();

	/**
	 * 源表维护
	 * 
	 * @param table_name
	 */
	public void maintainDS(String table_name);

	/**
	 * 
	 * @param Direct 请求字段
	 * @return
	 */
	public List<Map<String, String>> getDirectFiledMapping(Map<String, String> requestUrlmap);
}
