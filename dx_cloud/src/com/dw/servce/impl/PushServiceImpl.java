package com.dw.servce.impl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.dao.PushDao;
import com.dw.model.PushMenu;
import com.dw.servce.PushService;

@Service("PushService")
@Transactional
public class PushServiceImpl implements PushService {
	
	@Autowired
	PushDao pushDao;

	@Override
	public int delReal(PushMenu menu) {
		int code;
		try {
			code = pushDao.delReal(menu);
		}catch (ServiceException e) {
			code = 0;
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public int NondelReal(PushMenu menu) {
		int code;
		try {
			code = pushDao.NondelReal(menu);
		}catch (ServiceException e) {
			code = 0;
			e.printStackTrace();
		}
		return code;
	}
	
	@Override
	public int fail(PushMenu menu) {
		int code;
		try {
			code = pushDao.fail(menu);
		}catch (ServiceException e) {
			code = 0;
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public int availableProc(PushMenu menu) {
		int code;
		try {
			code = pushDao.availableProc(menu);
		}catch (ServiceException e) {
			code = 0;
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public int failProc(PushMenu menu) {
		int code;
		try {
			code = pushDao.failProc(menu);
		}catch (ServiceException e) {
			code = 0;
			e.printStackTrace();
		}
		return code;
	}

}
