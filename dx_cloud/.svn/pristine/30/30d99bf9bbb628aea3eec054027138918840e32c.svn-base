package com.dw.servce.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.dao.TestCloudDao;
import com.dw.model.testTree;
import com.dw.servce.TestCloudService;

@Service("TestCloudService")
@Transactional
public class TestCloudServiceImpl implements TestCloudService {

	@Autowired
	private TestCloudDao testCloudDao;
	
	@Override
	public List<testTree> getorgantree(String unit_id) {
		return testCloudDao.addSystemInfo(unit_id);
	}

	@Override
	public List<Map<String, String>> getuntreated(String unit_id) {
		return testCloudDao.getuntreated(unit_id);
	}

	@Override
	public List<Map<String, String>> getcount(String unit_id) {
		return testCloudDao.getcount(unit_id);
	}

}
