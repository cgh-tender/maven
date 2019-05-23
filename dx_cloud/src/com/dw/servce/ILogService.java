package com.dw.servce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dw.model.Log;

public interface ILogService {

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
	 * @param logname
	 * @param jSESSIONID 
	 * @param  
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, Object>> getLogin(String logname, String log_user_ID, String jSESSIONID);


	/**
	 * 进行修改当前登录用户的日志信息
	 * @param logmessage
	 * @return
	 */
	public int upLogin(HashMap<String, String> logmessage);


	/**
	 * 用户登录信息进行写入
	 * @param logmessage
	 */
	public void addLogin(HashMap<String, String> logmessage);


	/**
	 * 用户登录 异常登录状态进行修改
	 * @param username
	 */
	public void upType(String username,String JSESSIONID);


	/**
	 * 获取模块 id
	 * @param mETHOD
	 * @return
	 */
	public String getModuleCode(String mETHOD);


	/**
	 * 修改增加 log文件中的 module, 方法, 下载 等记录
	 * @param logMessing
	 */
	public void upLogMethod(HashMap<String, String> logMessing);
	

}
