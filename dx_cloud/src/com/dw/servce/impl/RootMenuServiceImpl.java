package com.dw.servce.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.dao.MenuDao;
import com.dw.dao.RootMenuDao;
import com.dw.model.Menu;
import com.dw.model.RootMenu;
import com.dw.servce.MenuService;
import com.dw.servce.RootMenuService;
@Service("RootmenuService")
@Transactional
public class RootMenuServiceImpl implements RootMenuService {

	@Autowired
	private RootMenuDao rootmenuDao;
	
	@Override
	 public List  getMenuList(RootMenu rootmenu){
  		return rootmenuDao.getRootMenuList(rootmenu);
 	}

	@Override
	public boolean UpdateMenu(RootMenu rootmenu) {
		return rootmenuDao.UpdateMenu(rootmenu);
	}

	@Override
	 public boolean deleteMenu(String menuId){
		return rootmenuDao.deleteMenu(menuId);
	}

	@Override
	public RootMenu addMenu(RootMenu rootmenu) {
 		return rootmenuDao.addMenu(rootmenu);
	}

	 
	 /**
	  * 通过desc 查询结果是否存在
	  * 
	  * 
	  */
	 
	 public  List<RootMenu>  getMenuByDesc(RootMenu rootmenu){
		 return rootmenuDao.getMenuByDesc(rootmenu);
	 }
}
