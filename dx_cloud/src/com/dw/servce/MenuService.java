package com.dw.servce;

import java.util.List;
import java.util.Map;

import com.dw.model.Menu;

public interface MenuService {
	/**
	 * 获取功能菜单列表
	 * @param menu
	 * @return
	 */
	 public List<Map<String,String>> getMenuList(Menu menu);
	 /**
	  * 添加或修改功能菜单
	  * @param menu
	  * @return
	  */
	 public boolean saveOrUpdateMenu(Menu menu);
	 /**
	  * 删除功能菜单
	  * @param menuIds
	  * @return
	  */
	 public boolean deleteMen(String menuIds);
}
