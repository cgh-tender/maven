package com.dw.dao;

import java.util.List;
import java.util.Map;

import com.dw.model.CityCode;
import com.dw.model.InterFace;
import com.dw.model.Jur;
import com.dw.model.Menu;
import com.dw.model.Role;
import com.dw.model.RoleJur;
/**
 * 角色管理
 * @author hutianlong
 *
 */
public interface IRoleDao {
	/**
	 * 获取角色列表
	 * @param role
	 * @return
	 */
	public List<Role> getRoleList(Role role);
	/**
	 * 编辑、新增
	 * @param role
	 * @return
	 */
	public boolean UpdateRole(Role role);
	
	/**
	 * 删除角色、
	 * @param ids
	 * @return
	 */
	public boolean delRole(String role_id);
	
	
	/***
	 * 
	 * 通过角色id 获得菜单详情
	 * 
	 * 
	 */
	public List<Map<String,String>> getRoleList(String roleid,String menulevel,String menuParentId,String type);
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	 public Role addRole(Role role);

	 
	 /**
		 * 通过相关条件查询出菜单页面对应的权限
		 * @return
		 */
		
		List<Jur> getJurBymenuId(String menu_id,String role_id,String jur_type,String jur_parent_id);
		
    /**
     * 查询该菜单下的所有
     * 		
     */
	
	/***
	 * 
	 * 添加权限	
	 * @param jur
	 * @return
	 */
		
	 public Jur addJur(Jur jur);

	 
	 /**
		 * 删除权限
		 */
 		
	public Boolean delJur(String jur_id);
	
	public Boolean UpdateJur(Jur fromjur);
 
	
	/**
	 * 
	 * 
	 * 删除角色下的权限
	 */
	public Boolean  delRoleJur(String role_id,String menu_id);
	/**
	 * 添加角色权限表
	 * 
	 */
	public Boolean addRoleJur(RoleJur rolejur);
	
	
	/**
	 * 
	 * 添加接口
	 * 
	 */
	public Boolean addInterface(InterFace infa);
	
	
	/**
	 * 通过菜单Id查找对应接口
	 * 
	 */
	
	public List<InterFace> getInterfaceAddressByMenuId (Menu me);
	
	
	/**
	 * 
	 * 判断级联下是否子菜单
	 * 
	 */
	
	public Boolean checkCascadeChild(String jurId);
	

	/**
	 * 
	 * 查询省分、地市
	 * 
	 */
	public List <Map> jurcitylist(CityCode cityCode);
	
	
	public Boolean deleteInterFace(String mumeId);
	
	/**
	 * 
	 * 通过menu_id,role_id 获得权限
	 * 
	 */
	public List<Map> getJurDetailByMenuIdAndRoleId(String menu_id,String role_id,String jur_type,String jur_parent_id);
	
	
}
