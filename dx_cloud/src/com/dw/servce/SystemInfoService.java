package com.dw.servce;

import com.dw.model.SystemInfo;

public interface SystemInfoService {

	
	/**
 	 * 添加系统信息
	 * @param systemInfo
	 * @return
	 */
	
	public SystemInfo addSystemInfo(SystemInfo systemInfo);
	
	/**
	 * 
	 * 查询系统信息
	 */
	public SystemInfo getSystemInfo();
	
	/**
	 * 
	 * 修改信息
	 * 
	 */
	
	public Boolean updateSystemInfo(SystemInfo systemInfo);

}
