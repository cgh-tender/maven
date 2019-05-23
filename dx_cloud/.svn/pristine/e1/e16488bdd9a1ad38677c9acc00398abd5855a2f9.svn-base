package com.dw.servce.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.dao.IUserDAO;
import com.dw.model.Menu;
import com.dw.model.Role;
import com.dw.model.User;
import com.dw.servce.IUserService;
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDAO adminDAO;
	public List<Map<String, String>> getUserList(User am) {
		return adminDAO.getUserList(am);
	}
	public boolean saveOrUpdateUser(User am) {
		return adminDAO.saveOrUpdateUser(am );
	}
	public boolean delUser(String ids) {
		return adminDAO.delUser(ids);
	}
	public List<Map<String, String>> getUsers(String loginName,String password) {
		return adminDAO.getUsers(loginName,password);
	}
	@Override
	public List<Map<String, String>> getMenuTreeList(User user, Menu menu) {
		// TODO Auto-generated method stub
		return adminDAO.getMenuTreeList(user,menu);
	}

	public List<Map<String, String>>  getUserListByMd5Name(User user){
		return adminDAO.getUserListByMd5Name(user);
	}
	@Override
	public int updateUser(User user) {
 		return adminDAO.updateUser(user);
	}
	
	public int updateUserRole(User user){
		return adminDAO.updateUserRole(user);
	}


}
