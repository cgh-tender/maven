package com.dw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

  import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dw.common.AdminSession;
import com.dw.common.AdminSessionUtil;
import com.dw.interceptor.MySessionContext;
import com.dw.model.Menu;
import com.dw.model.Organization;
import com.dw.model.ResultMessage;
import com.dw.model.Role;
import com.dw.model.RootMenu;
import com.dw.servce.IRoleService;
import com.dw.servce.MenuService;
import com.dw.servce.RootMenuService;
import com.dw.util.BaseTreeGrid;
import com.google.gson.Gson;

/**
 * 可视化系统独自菜单管理
 * 
 * @author hutianlong
 * 
 */
@Controller
public class RootMenuConstroller {

	@Autowired
	private MenuService menuSer;

	@Autowired
	IRoleService roleService;
	@Autowired
	RootMenuService rootMenuService;

	/**
	 * 获取菜单列列表
	 * 
	 * @param request
	 * @param response
	 */
 	@SuppressWarnings({ "unused", "static-access" })
	/**
	 * 菜单修改
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/rootmenu/editmenu", produces = "text/html;charset=UTF-8")
	public void  UpdateMenu(HttpServletRequest request,HttpServletResponse response, @RequestParam("menu") String menu) {
 		ResultMessage result = new ResultMessage();
 		Gson json = new Gson();
 		boolean jsonP = false;
		String cb = request.getParameter("audit");
		if (cb != null) {
			jsonP = true;
			response.setContentType("text/javascript");
		} else {
			response.setContentType("application/x-json");
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			if (jsonP) {
				out.write(cb + "(");
			}
			try {
 				  JSONObject obj = new JSONObject().fromObject(menu);
 				  RootMenu rootmenu=(RootMenu)JSONObject.toBean(obj, RootMenu.class);
 				  SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 				 String create_time = sdf.format(new Date());
  				  rootmenu.setUpdate_time(create_time); 
  				  
  				 if(checkMenudesc(rootmenu,"edit") ){ // true: 已存在 false: 不存在 
 					 result.setCode("2");
 					 result.setMsg("菜单描述已存在，不可重复添加！");
  				 }else{
 					 Boolean flag = rootMenuService.UpdateMenu(rootmenu);
 	 				  if(flag){
 	 					 result.setCode("1");
 	 					 result.setMsg("修改成功");
 	 				  }else{
 	 					 result.setCode("2");
 	 					 result.setMsg("修改失败,未知错误");
 	 				  }
  				 }
    			} catch (Exception e) {
   			     result.setCode("2");
				 result.setMsg("未知错误");
				e.printStackTrace();
			}
			
			if (jsonP) {
				out.write(");");
			}
		out.print(json.toJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

 	
 	
 	@SuppressWarnings({ "unused", "static-access" })
	/**
	 * 菜单添加
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/rootmenu/addmenu", produces = "text/html;charset=UTF-8")
	public void  addMenu(HttpServletRequest request,HttpServletResponse response, @RequestParam("menu") String menu) {
 		ResultMessage result = new ResultMessage();
 		Gson json = new Gson();
 		boolean jsonP = false;
		String cb = request.getParameter("audit");
		if (cb != null) {
			jsonP = true;
			response.setContentType("text/javascript");
		} else {
			response.setContentType("application/x-json");
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			if (jsonP) {
				out.write(cb + "(");
			}
			try {
 				  JSONObject obj = new JSONObject().fromObject(menu);
 				  RootMenu rootmenu=(RootMenu)JSONObject.toBean(obj, RootMenu.class);
 				  if(rootmenu.getMenu_id_parent() == null || rootmenu.getMenu_id_parent() == "" || "".equals(rootmenu.getMenu_id_parent())){
 					 rootmenu.setMenu_level("1");
 				  }else{
 					  RootMenu parent = new RootMenu();//临时
 					  parent.setMenu_id(rootmenu.getMenu_id_parent());
  					  RootMenu parentmenu = (RootMenu) rootMenuService.getMenuList(parent).get(0);// 查询出的父菜单
  					  rootmenu.setMenu_level(String.valueOf((Integer.parseInt(parentmenu.getMenu_level())+1)));
 				  }
  				 rootmenu.setMenu_id(String.valueOf(Calendar.getInstance().getTimeInMillis()));
   				 SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 				 String create_time = sdf.format(new Date());
 				 rootmenu.setCreate_time(create_time); 
 				 rootmenu.setUpdate_time(create_time); 
 				 
 				 if(checkMenudesc(rootmenu,"add")){ // true: 已存在 false: 不存在 
 					 result.setCode("2");
 					 result.setMsg("菜单描述已存在，不可重复添加！");
   				 }else{
   					 
    				  RootMenu resultrootmenu = rootMenuService.addMenu(rootmenu);
    				  if(resultrootmenu!=null){
    					 result.setCode("1");
    					 result.setMsg("添加成功");
    					 List<RootMenu> resultlist = new ArrayList();
    					 resultlist.add(resultrootmenu);
    					 result.setData(resultlist);
    				  }else{
    					 result.setCode("2");
    					 result.setMsg("添加失败,未知错误");
    				  }
    				}
    			} catch (Exception e) {
   			     result.setCode("2");
				 result.setMsg("未知错误");
				e.printStackTrace();
			}
 			if (jsonP) {
				out.write(");");
			}
			out.print(json.toJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
 	/*
 	 * 核查menu_desc 是否 存在，保证唯一性
 	 * 
 	 */
 	
 	@SuppressWarnings("unchecked")
	public  Boolean checkMenudesc( RootMenu rootmenu,String type ){
 		
 		RootMenu newrootmenu = new RootMenu();
 		newrootmenu.setMenu_desc(rootmenu.getMenu_desc().trim());
 		List<RootMenu> result =  rootMenuService.getMenuByDesc(newrootmenu);
 		
 		if(result.size() == 0){
 			return false;
 		}
 		
 		if(type.equals("add")){
  			if(result.size() > 0){
 	 			return true;
 	 		}
 		}else{
 			
   			for(RootMenu menu : result){
 				if(menu.getMenu_id().equals(rootmenu.getMenu_id())){
 					return false;
 				}
 			}
 			return true;
 		}
		return null;
   	}
 	
 	
 	
 	
 	
	/**
	 * 菜单删除
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/rootmenu/deletemenu", produces = "text/html;charset=UTF-8")
	public void delRootMenu(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("menu_id") String menu_id) {
		Gson json = new Gson();
		ResultMessage result = new ResultMessage();
		boolean jsonP = false;
		String cb = request.getParameter("audit");
		if (cb != null) {
			jsonP = true;
			response.setContentType("text/javascript");
		} else {
			response.setContentType("application/x-json");
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (jsonP) {
				out.write(cb + "(");
			}
			
			if(checkHaveChildMenu(menu_id)){
				result.setCode("2");
				result.setMsg("存在子菜单不能删除！");
 			}else{
 				Boolean flag = rootMenuService.deleteMenu(menu_id);
 				if(flag){
					result.setCode("1");
					result.setMsg("删除成功");
				}else{
					result.setCode("2");
					result.setMsg("删除失败");
				}
 			}
 		} catch (Exception e) {
			result.setCode("2");
			result.setMsg("删除失败");
			e.printStackTrace();
		}
		if (jsonP) {
			out.write(");");
		}
		out.print(json.toJson(result));
 	}
	
	/**
	 * 
	 * 判断下面是否有子菜单
	 * 
	 */
 	public Boolean checkHaveChildMenu(String menu_id){// true：有 |false 没有
		
		RootMenu rootMenu = new RootMenu();
		rootMenu.setMenu_id_parent(menu_id);
		List<RootMenu> rs = rootMenuService.getMenuList(rootMenu);
		
		if(rs.size() > 0 ){
			return true;
		}else{
			return false;
 		}
 	}
	

	/***
	 * 新写 加载所有该用户角色查询相关菜单
	 * 
	 */

	@ResponseBody
	@RequestMapping( value = "/onload/loadList", produces = "text/html;charset=UTF-8")
	public void getRoleMenuDetail(HttpServletRequest request,
			HttpServletResponse response)  {
		Gson json = new Gson();
		Role roler = new Role();
		boolean jsonP = false;
		ResultMessage result = new ResultMessage();
		String cb = request.getParameter("audit");
		if (cb != null) {
			jsonP = true;
			response.setContentType("text/javascript");
		} else {
			response.setContentType("application/x-json");
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (jsonP) {
				out.write(cb + "(");
			}
			AdminSession  sessionuser = getUserSessionUtil.getUserSession(request);
 			String roleid = sessionuser.getRole();
			if (roleid != null) {
				String menulevel = "1";
				String menuParentId = null;
				List<RootMenu> resultlist = getMenuList(roleid, menulevel,menuParentId,"3");
				if (resultlist.size() > 0) {
					result.setCode("1");
					result.setMsg("角色菜单查询成功！");
					result.setData(resultlist);
				} else {
					result.setCode("2");
					result.setMsg("角色菜单查询失败！");
					result.setData(new ArrayList());
				}
			} else {
				result.setCode("2");
				result.setMsg("角色菜单查询失败！");
				result.setData(new ArrayList());
			}
		} catch (Exception e) {
 			result.setCode("2");
			result.setMsg("角色菜单查询失败！");
			result.setData(new ArrayList());
			e.printStackTrace();
		}
		out.print(json.toJson(result));
		if (jsonP) {
			out.write(");");
		}
	}

	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/rootmenu/menulist", produces = "text/html;charset=UTF-8")
	public void getRoleMenuDetailByRoot(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "menu_id", required = false) String menu_id)
			throws IOException {
		Gson json = new Gson();
		Role roler = new Role();
		boolean jsonP = false;
		ResultMessage result = new ResultMessage();
		// String menu_id = request.getParameter("menu_id");
		String cb = request.getParameter("audit");
		if (cb != null) {
			jsonP = true;
			response.setContentType("text/javascript");
		} else {
			response.setContentType("application/x-json");
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			if (jsonP) {
				out.write(cb + "(");
			}
			AdminSession  sessionuser = getUserSessionUtil.getUserSession(request);// 通过session取值
			String roleid = sessionuser.getRole();
			String menulevel = "";
			String menuParentId = null;
			if (roleid != null) {
 				if (menu_id == null) {
					menulevel = "1";
					List<RootMenu> resultlist = getMenuList(roleid, menulevel,menuParentId,"4");
					if (resultlist.size() > 0) {
						result.setCode("1");
						result.setMsg("角色菜单查询成功！");
						result.setData(resultlist);
					} else if(resultlist.size() ==0 ){
						result.setCode("1");
						result.setMsg("角色菜单查询成功，但无数据！");
						result.setData(new ArrayList());
					}else {
						result.setCode("2");
						result.setMsg("角色菜单查询失败！");
						result.setData(new ArrayList());
					}
 				} else {//当menu_id 不等于null
					RootMenu rootmenu = new RootMenu();
					rootmenu.setMenu_id(menu_id);
 					List<RootMenu> resultlist =  rootMenuService.getMenuList(rootmenu);
 					
 					for(RootMenu rm : resultlist){
 						
 						if (rm.getChild_list() == null) {
 							rm.setChild_list(new ArrayList());
 						}
 						if (rm.getCreate_time() == null) {
 							rm.setCreate_time("");
 						}
 						if (rm.getMenu_desc() == null) {
 							rm.setMenu_desc("");
 						}
 						if (rm.getMenu_icon_code() == null) {
 							rm.setMenu_icon_code("");
 						}
 						if (rm.getMenu_id() == null) {
 							rm.setMenu_id("");
 						}
 						if (rm.getMenu_id_parent() == null) {
 							rm.setMenu_id_parent("");
 						}
 						if (rm.getMenu_index() == null) {
 							rm.setMenu_index(-1);
 						}
 						if (rm.getMenu_level() == null) {
 							rm.setMenu_level("");
 						}
 						if (rm.getMenu_name() == null) {
 							rm.setMenu_name("");
 						}
 						if (rm.getUpdate_time() == null) {
 							rm.setUpdate_time("");
 						}
 						if (rm.getMenu_url() == null) {
 							rm.setMenu_url("");
 						}
  					}
 					
 					
					if (resultlist.size() > 0) {
						result.setCode("1");
						result.setMsg("角色菜单查询成功！");
						result.setData(resultlist);
					} else if(resultlist.size() ==0 ){
						result.setCode("1");
						result.setMsg("角色菜单查询成功，但无数据！");
						result.setData(new ArrayList());
					}else {
						result.setCode("2");
						result.setMsg("角色菜单查询失败！");
						result.setData(new ArrayList());
					}
 				}
			} else {
				result.setCode("2");
				result.setMsg("角色菜单查询失败！");
				result.setData(new ArrayList());
			}
		} catch (Exception e) {
			out = response.getWriter();
			result.setCode("2");
			result.setMsg("角色菜单查询失败！");
			result.setData(new ArrayList());
			e.printStackTrace();
		}
		out.print(json.toJson(result));
		if (jsonP) {
			out.write(");");
		}
	}

	/***
	 * 通过menu_id 查询改菜单信息
	 * 
	 * @param menu_id
	 * @return
	 */

	public RootMenu getRootMenuDetail(String menu_id) {

		RootMenu RootMenu = new RootMenu();
		RootMenu.setMenu_id(menu_id);
		RootMenu resultRootMenu = (RootMenu) rootMenuService.getMenuList(
				RootMenu).get(0);
		return resultRootMenu;
	}

	// 菜单查询的封装
	public List<RootMenu> getMenuList(String roleid, String menulevel,String menuParentId,String endLevel) {
		List<RootMenu> resultlist = new ArrayList<RootMenu>();
		List<RootMenu> roleMenuListlist = roleService.getRoleList(roleid,
				menulevel, menuParentId,"menu");
		menulevel = String.valueOf(Integer.valueOf(menulevel) + 1);
		for (RootMenu rootmenu : roleMenuListlist) {
			// 下级菜单
			if (rootmenu.getChild_list() == null) {
				rootmenu.setChild_list(new ArrayList());
			}
			if (rootmenu.getCreate_time() == null) {
				rootmenu.setCreate_time("");
			}
			if (rootmenu.getMenu_desc() == null) {
				rootmenu.setMenu_desc("");
			}
			if (rootmenu.getMenu_icon_code() == null) {
				rootmenu.setMenu_icon_code("");
			}
			if (rootmenu.getMenu_id() == null) {
				rootmenu.setMenu_id("");
			}
			if (rootmenu.getMenu_id_parent() == null) {
				rootmenu.setMenu_id_parent("");
			}
			if (rootmenu.getMenu_index() == null) {
				rootmenu.setMenu_index(-1);
			}
			if (rootmenu.getMenu_level() == null) {
				rootmenu.setMenu_level("");
			}
			if (rootmenu.getMenu_name() == null) {
				rootmenu.setMenu_name("");
			}
			if (rootmenu.getUpdate_time() == null) {
				rootmenu.setUpdate_time("");
			}
			if (rootmenu.getMenu_url() == null) {
				rootmenu.setMenu_url("");
			}
			if(endLevel.equals(rootmenu.getMenu_level())){ //当到该等级结束时，不执行子菜单查询
				resultlist.add(rootmenu);

			}else{
				List<RootMenu> roleMenuListcrlist = roleService.getRoleList(roleid,menulevel, rootmenu.getMenu_id(),"menu");
				if (roleMenuListcrlist.size() > 0) {
					rootmenu.setChild_list(getMenuList(roleid, menulevel,rootmenu.getMenu_id(),endLevel));
				}
				resultlist.add(rootmenu);
			}
 		}
		return resultlist;
	}
}
