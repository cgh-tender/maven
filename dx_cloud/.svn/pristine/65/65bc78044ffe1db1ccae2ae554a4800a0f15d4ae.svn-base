package com.dw.servce.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.dao.MenuDao;
import com.dw.model.Menu;
import com.dw.servce.MenuService;
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	@Override
	public List<Map<String, String>> getMenuList(Menu menu) {
		return menuDao.getMenuList(menu);
	}

	@Override
	public boolean saveOrUpdateMenu(Menu menu) {
		return menuDao.saveOrUpdateMenu(menu);
	}

	@Override
	public boolean deleteMen(String menuIds) {
		return menuDao.deleteMen(menuIds);
	}
}
