package com.dw.servce;

import java.util.List;
import java.util.Map;

import com.dw.model.Organization;

public interface IOrgService {
	/**
	 * 组织机构列表
	 * @param org
	 * @return
	 */
	public List<Map<String,String>> getOrgList(Organization org);
	/**
	 * 添加、修改
	 * @param org
	 * @return
	 */
	public boolean saveOrUpOrg(Organization org);
	
	/**
	 * 删除
	 * @param org
	 * @return
	 */
	public boolean delOrgs(String ids);
	/**
	 * 根据条件查询数据所有子节点
	 * @param org
	 * @return
	 */
	public List<Map<String,String>> getOrgTreeList(Organization org);
}
