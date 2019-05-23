package com.dw.servce.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.dao.IOrgDao;
import com.dw.model.Organization;
import com.dw.servce.IOrgService;

@Service("orgService")
@Transactional
public class OrgServiceImpl implements IOrgService{
	
	@Autowired
	private IOrgDao orgDao;

	@Override
	public List<Map<String, String>> getOrgList(Organization org) {
		return orgDao.getOrgList(org);
	}

	@Override
	public boolean saveOrUpOrg(Organization org) {
		return orgDao.saveOrUpOrg(org);
	}

	@Override 
	public boolean delOrgs(String ids) {
		return orgDao.delOrgs(ids);
	}

	@Override
	public List<Map<String, String>> getOrgTreeList(Organization org) {
		return orgDao.getOrgTreeList(org);
	}
	
}
