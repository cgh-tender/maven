package com.dw.servce;

import java.util.List;
import java.util.Map;

import com.dw.model.Menu;
import com.dw.model.RootMenu;

public interface RootMenuService {
	/**
	 * 获取功能菜单列表
	 * @param menu
	 * @return
	 */
	 public List  getMenuList(RootMenu rootmenu);
	 /**
	  * 添加或修改功能菜单
	  * @param menu
	  * @return
	  */
	 public boolean UpdateMenu(RootMenu rootmenu);
	 /**
	  * 删除功能菜单
	  * @param menuIds
	  * @return
	  */
	 public boolean deleteMenu(String menuId);
	 
	 /**
	  * 添加功能菜单
	  * @param menuIds
	  * @return
	  */
	 public RootMenu addMenu(RootMenu rootmenu);
	 
	 /**
	  * 通过desc 查询结果是否存在
	  * 
	  * 
	  */
	 
	 public List<RootMenu>  getMenuByDesc(RootMenu rootmenu);
}
