package com.dw.dao;

import java.util.List;
import java.util.Map;

import com.dw.model.Menu;
import com.dw.model.Role;
import com.dw.model.User;
/**
 * 用户管理
 * @author hutianlong
 *
 */
public interface IUserDAO {

	/**
	 * 获取后台用户列表
	 * 
	 * @return
	 */
	List<Map<String, String>> getUserList(User am);

	/**
	 * 编辑
	 * 
	 * @param am
	 */
	boolean saveOrUpdateUser(User am);

	/**
	 * 删除用户
	 * 
	 * @param am
	 */
	boolean delUser(String ids);
	/**
	 * 获取用户信息
	 */
	List<Map<String,String>> getUsers(String loginName,String password);

	/**
	 * MenuTree
	 * @param user
	 * @param menu
	 * @param role
	 * @return
	 */
	List<Map<String, String>> getMenuTreeList(User user, Menu menu);
	
	/***
	 * 
	 * 通过加密的用户名查询该用户
	 * 
	 */
	List<Map<String, String>>  getUserListByMd5Name(User user);
	
	

	/**
	 * 通过用户名更新
	 */
	public int updateUser(User user);
	
	public int updateUserRole(User user);

	
}
