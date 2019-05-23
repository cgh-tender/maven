package com.dw.servce.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.common.LogManage;
import com.dw.dao.ILogDao;
import com.dw.model.Log;
import com.dw.servce.ILogService;

@Service("logService")
public class LogServiceImpl implements ILogService {
	private static LogManage loger=new LogManage("logger", "caozuo");
	@Autowired
	private ILogDao logDao;
	/**
	 * 通过表明创建日志表
	 */
	public void createLogTable(String tableName){
		logDao.createLogTable(tableName);
	}

	/**
	 * 重写该方法,动态插入日志记录(动态表) 
	 */
	public void saveEntity(Log t) {
		logDao.saveEntity(t);
		}
	
	/**
	 * 查询最近指定月份数的日志
	 */
	public List<Log> findNearestLogs(int n){
		return null;}

	/**
	 * 查询日志中是否有登录的信息
	 */
	@Override
	public List<Map<String, Object>> getLogin(String logname, String log_user_ID, String jSESSIONID) {
		return logDao.getLogin(logname,log_user_ID,jSESSIONID);
	}

	@Override
	public int upLogin(HashMap<String, String> logmessage) {
		return logDao.upLogin(logmessage);
	}

	@Override
	public void addLogin(HashMap<String, String> logmessage) {
		logDao.addLogin(logmessage);
	}

	@Override
	public void upType(String username,String JSESSIONID) {
		logDao.upType(username,JSESSIONID);
	}

	@Override
	public String getModuleCode(String mETHOD) {
		return logDao.getModuleCode(mETHOD);
	}

	@Override
	public void upLogMethod(HashMap<String, String> logMessing) {
		 logDao.upLogMethod(logMessing);
	}

}
