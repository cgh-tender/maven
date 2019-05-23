package com.dw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dw.model.Log;

public interface ILogDao {
/**
 * 保存日志
 * @param log
 */
	void saveEntity(Log log);


	/**
	 * 通过表明创建日志表
	 */
	public void createLogTable(String tableName);

	/**
	 * 查询最近指定月份数的日志
	 */
	public List<Log> findNearestLogs(int i);


	/**
	 * 查询日志中是否有登录的信息
	 * @param logname
	 * @param log_user_ID 
	 * @param jSESSIONID 
	 * @return
	 */
	public List<Map<String, Object>> getLogin(String logname, String log_user_ID, String jSESSIONID);


	/**
	 * 修改当前登录用户的登录信息
	 * @param logmessage
	 * @return
	 */
	public int upLogin(HashMap<String, String> logmessage);


	/**
	 * 用户登录信息写入
	 * @param logmessage
	 */
	public void addLogin(HashMap<String, String> logmessage);


	/**
	 * 用户登录状态进行修改
	 * @param username
	 */
	public void upType(String username,String JSESSIONID);


	public String getModuleCode(String mETHOD);


	/**
	 * 新增方法 mode 的用户操作记录
	 * @param logMessing
	 */
	public void upLogMethod(HashMap<String, String> logMessing);
	

}
