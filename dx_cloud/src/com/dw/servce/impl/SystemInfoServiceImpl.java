package com.dw.servce.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.dao.RootMenuDao;
import com.dw.dao.SystemInfoDao;
import com.dw.model.SystemInfo;
import com.dw.servce.SystemInfoService;



@Service("SystemInfoService")
@Transactional
public class SystemInfoServiceImpl implements SystemInfoService {

	
	
	@Autowired
	private SystemInfoDao systemInfoDao;
	
	@Override
	public SystemInfo addSystemInfo(SystemInfo systemInfo) {
  		return systemInfoDao.addSystemInfo(systemInfo);
	}

	@Override
	public SystemInfo getSystemInfo() {
 		return systemInfoDao.getSystemInfo();
	}

	@Override
	public Boolean updateSystemInfo(SystemInfo systemInfo) {
 		return systemInfoDao.updateSystemInfo(systemInfo);
	}

}
