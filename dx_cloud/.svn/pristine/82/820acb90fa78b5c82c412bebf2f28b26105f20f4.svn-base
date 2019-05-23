package com.dw.dao;

import java.util.List;
import java.util.Map;

import com.dw.model.Organization;

/**
 * 组织机构管理
 * @author hutianlong
 *
 */
public interface IOrgDao {
	/**
	 * 按条件查询组织机构列表
	 * @param org
	 * @return
	 */
	public List<Map<String,String>> getOrgList(Organization org);
	/**
	 * 根据条件查询数据所有子节点
	 * @param org
	 * @return
	 */
	public List<Map<String,String>> getOrgTreeList(Organization org);
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
}
