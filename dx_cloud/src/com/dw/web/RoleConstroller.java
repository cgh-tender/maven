package com.dw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.security.util.Length;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dw.common.AdminSession;
import com.dw.common.AdminSessionUtil;
import com.dw.model.CityCode;
import com.dw.model.Indepent;
import com.dw.model.InterFace;
import com.dw.model.Jur;
import com.dw.model.JurType;
import com.dw.model.Menu;
import com.dw.model.Menu_jur;
import com.dw.model.ResultMessage;
import com.dw.model.ResultMessage2;
import com.dw.model.Role;
import com.dw.model.RoleJur;
import com.dw.model.RoleMenu;
import com.dw.model.RootMenu;
import com.dw.model.SystemInfo;
import com.dw.model.User;
import com.dw.model.InterFaceObj;
import com.dw.servce.IRoleMenuService;
import com.dw.servce.IRoleService;
import com.dw.servce.IUserService;
import com.dw.servce.RootMenuService;
import com.dw.servce.SystemInfoService;
import com.dw.util.BaseTreeGrid;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.sun.swing.internal.plaf.basic.resources.basic;

@Controller  
public class RoleConstroller {
	@Autowired
	IRoleService roleService;
	@Autowired
	IUserService userSer;
	@Autowired
	IRoleMenuService  roleMenuService;
	@Autowired
	SystemInfoService systemInfoService ; 

	/**
	 * 角色列表
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/rolemanagement/rolelist",produces="text/html;charset=UTF-8")
    public void getRoleList(HttpServletRequest request,HttpServletResponse response)   {
    	 Gson json=new Gson(); 
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
			 Role roler = new Role();
			 List<Role> rlist = roleService.getRoleList(roler);
			 for(Role r : rlist){
				if(r.getCreate_time() == null){
					r.setCreate_time("");
				}
				if(r.getRole_desc() == null){
					r.setRole_desc("");
				}
				if(r.getRole_index() == null){
					r.setRole_index(-1);
				}
				if(r.getRole_name()==null){
					r.setRole_name("");
				}
				if(r.getUpdate_time() == null){
					r.setUpdate_time("");
				}
			 }
 			 
			 result.setCode("1");
			 result.setMsg("查询成功");
			 result.setData(rlist);
 	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
 		 }  catch (Exception e) {
			 result.setCode("2");
			 result.setMsg("查询失败");
  			 e.printStackTrace();
 		}
		 out.print(json.toJson(result));
      }
	 
	
	
	/**
	 * 角色修改
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/rolemanagement/editrole", produces = "text/html;charset=UTF-8")
	public void  UpdateRole(HttpServletRequest request,HttpServletResponse response, @RequestParam("role") String role) {
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
 				  JSONObject obj = new JSONObject().fromObject(role);
 				 Role fromrole=(Role)JSONObject.toBean(obj, Role.class);
 				 SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 				 String create_time = sdf.format(new Date());
 				 fromrole.setUpdate_time(create_time); 
 				 if("1501793717970".equals(fromrole.getRole_id()) || "1501793717971".equals(fromrole.getRole_id()) ){
 					 result.setCode("2");
	 			     result.setMsg("管理员不允许修改");
 				 }else{
 					  Boolean flag =  roleService.UpdateRole(fromrole);
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
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@SuppressWarnings({ "unused", "static-access" })
	/**
	 * 角色添加
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/rolemanagement/addrole", produces = "text/html;charset=UTF-8")
	public void  addRole(HttpServletRequest request,HttpServletResponse response, @RequestParam("role") String role) {
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
 				  JSONObject obj = new JSONObject().fromObject(role);
 				  Role fromrole=(Role)JSONObject.toBean(obj, Role.class);
  				 fromrole.setRole_id(String.valueOf(Calendar.getInstance().getTimeInMillis()));
   				 SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 				 String create_time = sdf.format(new Date());
 				 fromrole.setCreate_time(create_time); 
 				 fromrole.setUpdate_time(create_time);
 				 if( null == fromrole.getRole_index() ){
 					fromrole.setRole_index(100);
 				 }
   				 Role resultrole = roleService.addRole(fromrole);
 				  if(resultrole!=null){
 					 result.setCode("1");
 					 result.setMsg("添加成功");
 					 List<Role> resultlist = new ArrayList();
 					 resultlist.add(resultrole);
 					 result.setData(resultlist);
 				  }else{
 					 result.setCode("2");
 					 result.setMsg("添加失败,未知错误");
 				  }
   			} catch (Exception e) {
   			     result.setCode("2");
				 result.setMsg("未知错误");
				e.printStackTrace();
			}
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * 角色删除
	 */
	 
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/rolemanagement/deleterole", produces = "text/html;charset=UTF-8")
	public void delRootMenu(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("role_id") String role_id) {
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
 			
			
			
			Boolean flag = roleService.delRole(role_id);
			if(flag){
				result.setCode("1");
				result.setMsg("删除成功");
			}else{
				result.setCode("2");
				result.setMsg("删除失败");
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
	 * 菜单列表展示
	 * 
	 */
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/rolemanagement/roledetail",produces="text/html;charset=UTF-8")
    public void getRoleDetails(HttpServletRequest request,HttpServletResponse response,@RequestParam("role") String role)   {
    	AdminSession  sessionuser = getUserSessionUtil.getUserSession(request);// 通过session取值
    	String userRole = sessionuser.getRole();
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
 				  JSONObject obj = new JSONObject().fromObject(role);
 				  Role fromrole=(Role)JSONObject.toBean(obj, Role.class);
 				  
 				  if(fromrole.getRole_id() == null || fromrole.getRole_id() == ""){
 					 result.setCode("2");
 					 result.setMsg("角色Id为空,查询失败");
 				  }else{
  					  String menulevel = "1";
 		 			  String menuParentId = null;
// 		 			List<RootMenu> allmenulist = getMenuList("1501793717970", menulevel,menuParentId,"3");//所有菜单
	 				List<RootMenu> allmenulist = getMenuList(userRole, menulevel,menuParentId,"3");// 当前用记所有的菜单
 		 			List<RootMenu> resultlist = getMenuList(fromrole.getRole_id(), menulevel,menuParentId,"3");//已有菜单
	 			
  		 			  allmenulist = addMenuCheck(allmenulist,resultlist);//菜单是否选中
   	 	 			  result.setCode("1");
 		 			  result.setMsg("查询成功");
 		 			  result.setData(allmenulist);
  				  }
     			} catch (Exception e) {
   			     result.setCode("2");
				 result.setMsg("未知错误");
				e.printStackTrace();
			}
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
     }
	
	// 菜单查询的封装
		public List<RootMenu>   getMenuList(String roleid, String menulevel,String menuParentId,String endLevel) {
			List<RootMenu> resultlist = new ArrayList<RootMenu>();
			List<RootMenu> roleMenuListlist = roleService.getRoleList(roleid,menulevel, menuParentId,"role");
		
			menulevel = String.valueOf(Integer.valueOf(menulevel) + 1);
			for (int i = 0; i < roleMenuListlist.size(); i++) {
				RootMenu rootmenu = new RootMenu();
				rootmenu = roleMenuListlist.get(i);

				if(rootmenu.getMenu_id_parent().equals("1537089708125")) {
					rootmenu.getMenu_id();
				}
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
				if (rootmenu.getMenu_check() == null) {
					rootmenu.setMenu_check(0);
				}
				if(endLevel.equals(rootmenu.getMenu_level())){ //当到该等级结束时，不执行子菜单查询
					resultlist.add(rootmenu);

				}else{
					List<RootMenu> roleMenuListcrlist = roleService.getRoleList(roleid,menulevel, rootmenu.getMenu_id(),"role");
					if (roleMenuListcrlist.size() > 0) {
						rootmenu.setChild_list(getMenuList(roleid, menulevel,rootmenu.getMenu_id(),endLevel));
					}
					resultlist.add(rootmenu);
				}
	 		
			}
			ArrayList<RootMenu> req = new ArrayList<RootMenu>();
			for (int i = 0; i < resultlist.size(); i++) {
				try {
					req.add((RootMenu) resultlist.get(i).clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
			return req;
		}
	
	
		
		
		/**
		 * 
		 * 开发者某个菜单权限展示
		 * 
		 */
		
		@ResponseBody
	    @RequestMapping(method = RequestMethod.GET,value = "/jurmanagement/jurlist",produces="text/html;charset=UTF-8")
	    public void getJurDetails(HttpServletRequest request,HttpServletResponse response,@RequestParam("menu_id") String menu_id)   {
	    	
			ResultMessage2 result = new ResultMessage2();
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
 	 	 			  
					  AdminSession  sessionuser = getUserSessionUtil.getUserSession(request);// 通过session取值
					  String roleid = sessionuser.getRole();
  					  if(menu_id==null || menu_id == ""){
						  result.setCode("2");
						  result.setMsg("菜单Id为空，查询失败！");
					  }else{
						  JurType  jurType = getJurDetail(menu_id.replace("'",""),roleid,null,null,"1");
						  result.setCode("1");
				 		  result.setMsg("查询成功");
				 		  result.setData(jurType); 
					  }
  	    			} catch (Exception e) {
	   			     result.setCode("2");
					 result.setMsg("未知错误");
					e.printStackTrace();
				}
				out.print(json.toJson(result));
				if (jsonP) {
					out.write(");");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	     }
		
		
		
		
		/**
		 * 
		 * 开发者某个菜单权限展示
		 * 
		 */
		
		/*@ResponseBody
	    @RequestMapping(method = RequestMethod.GET,value = "/jurmanagement/jurdetail",produces="text/html;charset=UTF-8")
	    public void jurdetail(HttpServletRequest request,HttpServletResponse response,@RequestParam("menu_id") String menu_id,@RequestParam("role_id") String role_id)   {
	    	
			ResultMessage2 result = new ResultMessage2();
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
 	 	 			  
   					  if(menu_id==null || menu_id == "" || role_id == null || role_id == ""){
						  result.setCode("2");
						  result.setMsg("参数为空，查询失败！");
					  }else{
						  JurType  jurType = getJurDetail(menu_id.replace("'",""),role_id,null,null,"1");
						  result.setCode("1");
				 		  result.setMsg("查询成功");
				 		  result.setData(jurType); 
					  }
  	    			} catch (Exception e) {
	   			     result.setCode("2");
					 result.setMsg("未知错误");
					e.printStackTrace();
				}
				out.print(json.toJson(result));
				if (jsonP) {
					out.write(");");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	     }	*/
		
		
 		
		//当前菜单的权页面展示
		
	public JurType getJurDetail(String menu_id, String role_id,
			Integer jur_type, String jur_parent_id,String interfaceFlag) {

		JurType jurtype = new JurType();

		if (jur_type == null && jur_parent_id == null) {// 求当前页面的所有即独立和级联

			// 先求出该角色下独立的List
 			List<Jur> rolefixedlist = roleService.getJurBymenuId(menu_id, role_id,"'0','3','4','5','6','7'",jur_parent_id);
  		   // 先求出该菜单下独立的List
 			List<Jur> menufixedlist = roleService.getJurBymenuId(menu_id, null,"'0','3','4','5','6','7'",jur_parent_id);
  			menufixedlist = addJur_Checked(rolefixedlist,menufixedlist);
  			//根据jur_type区分
  			
   			List<Jur> result0 = new ArrayList<Jur>();
  			List<Jur> result3 = new ArrayList<Jur>();
  			List<Jur> result4 = new ArrayList<Jur>();
  			List<Jur> result5 = new ArrayList<Jur>();
  			List<Jur> result6 = new ArrayList<Jur>();
  			List<Jur> result7 = new ArrayList<Jur>();
  			
   			for(Jur jur :menufixedlist){
   				if(jur.getJur_type() == 0){
  					result0.add(jur);
  				}else if(jur.getJur_type() == 3){
  					result3.add(jur);
  				}else if(jur.getJur_type() == 4){
  					result4.add(jur);
  				}else if(jur.getJur_type() == 5){
  					result5.add(jur);
  				}else if(jur.getJur_type() == 6){
  					result6.add(jur);
  				}else if(jur.getJur_type() == 7){
  					result7.add(jur);
  				}
  			}
  			
   			jurtype.setFixed(result0);
   			jurtype.setIndepent1(result3);
   			jurtype.setIndepent2(result4);
   			jurtype.setIndepent3(result5);
   			jurtype.setIndepent4(result6);
   			jurtype.setIndepent5(result7);
 
  			// 求角色级联的一级
 			List<Jur> rolecascadelist1 = roleService.getJurBymenuId(menu_id,role_id, "'1'", jur_parent_id);
  		   // 求菜单级联的一级
 			List<Jur> menucascadelist1 = roleService.getJurBymenuId(menu_id,null,"'1'", jur_parent_id);
  			menucascadelist1 = addJur_Checked(rolecascadelist1, menucascadelist1);
 			for (Jur jur : menucascadelist1) {
   				List<Jur> rolecascadelist2 = roleService.getJurBymenuId(menu_id,role_id,"'2'", jur.getJur_id());// 角色求级联的二级
 				List<Jur> menucascadelist2 = roleService.getJurBymenuId(menu_id,null,"'2'", jur.getJur_id());// 菜单求级联的二级
  				menucascadelist2 = addJur_Checked(rolecascadelist2,menucascadelist2);
   				jur.setChild(menucascadelist2);
 			} 
  			jurtype.setCascade(menucascadelist1);
 		} 
 		//接口
 		if("1".equals(interfaceFlag)){
			String []infa = getInterfaceAddressByMenuId(menu_id);
	 		jurtype.setInterfacem(infa);
		}
  		return jurtype;
	}
	
	// 通过MenuId 查询 接口数据
	public String[] getInterfaceAddressByMenuId(String menu_id){
 		Menu me  = new Menu();
		me.setMenuId(menu_id);
 		List<InterFace> interfaces = roleService.getInterfaceAddressByMenuId(me);
 		String []infa = new String[interfaces.size()]; 
		for(int i=0;i<interfaces.size();i++){
			infa[i] = interfaces.get(i).getInterface_address()+"";
		}
  		return infa;
	}
	
	
	// 添加菜单下的check
	
	public List<RootMenu> addMenuCheck(List<RootMenu> allmenulist,List<RootMenu>  resultlist){
		Boolean flag = false;
		for(RootMenu allmenu:allmenulist){//总进行第一次遍历
			for (RootMenu havemenu:resultlist) {//进行第一次便利
				if(havemenu.getMenu_id().equals(allmenu.getMenu_id())) {//当两个menu_id相同的时候进行对比
					allmenu.setMenu_check(1);
					flag = true;
					if(havemenu.getChild_list().size() > 0) {
						addMenuCheck(allmenu.getChild_list(), havemenu.getChild_list());
					}
				}
			}
 		}
//		for(RootMenu allmenu:allmenulist){
//			if(resultlist.size() != 0){
//				for(RootMenu havemenu:resultlist){
//					if(havemenu.getMenu_id().equals(allmenu.getMenu_id())){
//						allmenu.setMenu_check(1);
//						flag = true;
//						break;
//					}else{
// 						if(havemenu.getChild_list().size() > 0){
//							addMenuCheck( allmenulist, havemenu.getChild_list());
//						}
//					}
//				}	
//			}
//      		addMenuCheck(allmenu.getChild_list(), resultlist);
// 		}
 		return allmenulist;
	}
	
 	
	// 添加 菜单下的 jur_checked
	public List<Jur> addJur_Checked(List<Jur> rolelist, List<Jur> menulist) {
 		for (Jur menujur : menulist) {
			Boolean flag = false;
			if (rolelist.size() == 0) {
				menujur.setJur_checked(0);
			} else {
				for (Jur rolejur : rolelist) {
					if (menujur.getJur_id() == rolejur.getJur_id()) {
						menujur.setJur_checked(1);
						flag = true;
						break;
					} else {
						menujur.setJur_checked(0);
					}
				}
			}
			if (!flag) {
				menujur.setJur_checked(0);
			}
 		}
		return menulist;
	}
	
 	// 权限添加 
 	
 	@SuppressWarnings({ "unused", "static-access" })
	/**
	 * 角色添加
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/jurmanagement/addjur", produces = "text/html;charset=UTF-8")
	public void  addJur(HttpServletRequest request,HttpServletResponse response, @RequestParam("jurobj") String jurobj) {
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
 				  
				JSONArray jsonArray = JSONArray.fromObject(jurobj);
		         
		        List<Jur> fromlist = jsonArray.toList(jsonArray,Jur.class);
		        
		        List<Jur> resultlist = new ArrayList<Jur>();
		        
		        for(Jur jur : fromlist){
		        	
 		        	 jur.setJur_id(String.valueOf(Calendar.getInstance().getTimeInMillis())+uuid5());
	   				 SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 				 String create_time = sdf.format(new Date());
	 				 jur.setCreate_time(create_time); 
	 				 jur.setUpdate_time(create_time); 
 	 				 Jur resultjur = roleService.addJur(jur);
 	 				 resultlist.add(resultjur);
		        }
   				  if(resultlist!=null){
 					 result.setCode("1");
 					 result.setMsg("添加成功");
  					 result.setData(resultlist);
 				  }else{
 					 result.setCode("2");
 					 result.setMsg("添加失败,未知错误");
 				  }
   			} catch (Exception e) {
   			     result.setCode("2");
				 result.setMsg("未知错误");
				 e.printStackTrace();
			}
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
 	
 	
 	/**
	 * 角色删除
	 */
	 
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/jurmanagement/deletejur", produces = "text/html;charset=UTF-8")
	public void delJur(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("jur_id") String jur_id) {
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
			
			JSONArray jurIds = JSONArray.fromObject(jur_id);
			String deletejurids="'";
 			if(jurIds.size() == 0){
 				result.setCode("2");
				result.setMsg("参数为空，删除失败");
 			}else{
				for(int i=0;i<jurIds.size();i++){
					String jurid = jurIds.get(i).toString();
					if(checkCascadeChild(jurid)){
						result.setCode("2");
						result.setMsg("存在级联有子菜单，删除失败");
						break;
					}else{
 						if(i == jurIds.size()-1){
	 						deletejurids+=jurid+"'";
						}else{
							deletejurids+=jurid+"','";
	 					}
					}
  				}
				if("2".equals(result.getCode())){
					//存在级联有子菜单，删除失败
				}else{
					Boolean flag = roleService.delJur(deletejurids);
					if(flag){
						result.setCode("1");
						result.setMsg("删除成功");
					}else{
						result.setCode("2");
						result.setMsg("删除失败");
					}
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
	 * 判断级联下的是否有子按钮
	 * 
	 */
	
	public Boolean checkCascadeChild(String jurId){
		Boolean result = roleService.checkCascadeChild(jurId);
		return result;
	}
	
 	
	
	/**
	 * 权限修改
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/jurmanagement/editjur", produces = "text/html;charset=UTF-8")
	public void  UpdateJur(HttpServletRequest request,HttpServletResponse response, @RequestParam("jurobj") String jurobj) {
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
 				  JSONObject obj = new JSONObject().fromObject(jurobj);
 				 Jur fromjur=(Jur)JSONObject.toBean(obj, Jur.class);
 				 SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 				 String create_time = sdf.format(new Date());
 				 fromjur.setUpdate_time(create_time); 
   				 Boolean flag =  roleService.UpdateJur(fromjur);
 				  if(flag){
 					 result.setCode("1");
 					 result.setMsg("修改成功");
 				  }else{
 					 result.setCode("2");
 					 result.setMsg("修改失败,未知错误");
 				  }
   			} catch (Exception e) {
   			     result.setCode("2");
				 result.setMsg("未知错误");
				e.printStackTrace();
			}
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
 	 
	@SuppressWarnings({ "unused", "static-access", "unchecked" })
	/**
	 * 整个保存权限修改结果
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/jurmanagement/savejur", produces = "text/html;charset=UTF-8")
	public void  saveJur(HttpServletRequest request,HttpServletResponse response, @RequestParam("jur") String jur) {
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
				
				Boolean addflag = false;
  				JSONObject obj = new JSONObject().fromObject(jur);
				RoleJur fromrolejur=(RoleJur)JSONObject.toBean(obj, RoleJur.class);
  	 			String role_id = fromrolejur.getRole_id();
  	 			String menu_id = fromrolejur.getMenu_id();
   	 			JSONArray jsonArray = JSONArray.fromObject(fromrolejur.getJur_data());
  	 			List<Jur> jurdatas = jsonArray.toList(jsonArray,Jur.class);
     	        Boolean flag = roleService.delRoleJur(role_id,menu_id); //先删除该角色的该菜单下的所有权限
  	        	for(Jur jurdetail: jurdatas){
  	        		if(jurdetail.getJur_checked() == 1){
  	        			RoleJur rolejur = new RoleJur();
  	        			rolejur.setRole_id(role_id);
  	        			rolejur.setJur_id(jurdetail.getJur_id());
  	        			rolejur.setMenu_id(menu_id);
  	        			roleService.addRoleJur(rolejur);
  	        		}
	        		addflag = true;
    	        }
  	        	 result.setCode("1");
  				 result.setMsg("保存成功");
    			} catch (Exception e) {
   			     result.setCode("2");
				 result.setMsg("未知错误");
				 e.printStackTrace();
			}
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 加载当前页面的权限列表
	 * @param request
	 * @param response
	 * @param menu_id
	 */
	
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/pagemanagement/jurlist",produces="text/html;charset=UTF-8")
    public void getjurlist(HttpServletRequest request,HttpServletResponse response,@RequestParam("menu_id") String menu_id)   {
    	
		ResultMessage2 result = new ResultMessage2();
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
	 	 			  
				AdminSession  sessionuser = getUserSessionUtil.getUserSession(request);// 通过session取值
				String roleid = sessionuser.getRole();
		 	    //JurType  jurType = getJurDetail(menu_id,roleid,null,null,"0");
				JurType jurType = new JurType();
 		 	    List<Map> jurDetailList = roleService.getJurDetailByMenuIdAndRoleId(menu_id,roleid,"'0','3','4','5','6','7'",null);
				
		 	    
		 	    List<Map> result0 = new ArrayList<Map>();
	  			List<Map> result3 = new ArrayList<Map>();
	  			List<Map> result4 = new ArrayList<Map>();
	  			List<Map> result5 = new ArrayList<Map>();
	  			List<Map> result6 = new ArrayList<Map>();
	  			List<Map> result7 = new ArrayList<Map>();
 	   			for(Map p :jurDetailList){
 	   				String jurTypep = p.get("jur_type")+"";
 	   				if("0".equals(jurTypep)){
 	   					p.remove("jur_type");
 	   					p.remove("jur_id");
 	  					result0.add(p);
	  				}else if("3".equals(jurTypep)){
 	   					p.remove("jur_type");
 	   					p.remove("jur_id");
  	  					result3.add(p);
	  				}else if("4".equals(jurTypep)){
 	   					p.remove("jur_type");
 	   					p.remove("jur_id");
  	  					result4.add(p);
	  				}else if("5".equals(jurTypep)){
 	   					p.remove("jur_type");
 	   					p.remove("jur_id");
  	  					result5.add(p);
	  				}else if("6".equals(jurTypep)){
 	   					p.remove("jur_type");
 	   					p.remove("jur_id");
  	  					result6.add(p);
	  				}else if("7".equals(jurTypep)){
 	   					p.remove("jur_type");
 	   					p.remove("jur_id");
  	  					result7.add(p);
	  				}
	  			}
 	   			jurType.setFixed(result0);
 	   			jurType.setIndepent1(result3);
 	   			jurType.setIndepent2(result4);
 	   			jurType.setIndepent3(result5);
 	   			jurType.setIndepent4(result6);
 	   			jurType.setIndepent5(result7);
		 	    
	   		    // 求角色级联的一级
	 			List<Map> rolecascadelist1 = roleService.getJurDetailByMenuIdAndRoleId(menu_id,roleid, "'1'", null);
	 			for(Map p : rolecascadelist1){
	 				String jurId = p.get("jur_id")+"";
	 				p.remove("jur_type");
	   				p.remove("jur_id");
  	 				List<Map> childcascadelist =  roleService.getJurDetailByMenuIdAndRoleId(menu_id,roleid, "'2'", jurId);
	 				p.put("child", childcascadelist);
 	 			}
	 			jurType.setCascade(rolecascadelist1);
 	 	 		result.setCode("1");
	 		    result.setMsg("查询成功");
	 			result.setData(jurType);
    			} catch (Exception e) {
   			     result.setCode("2");
				 result.setMsg("未知错误");
				e.printStackTrace();
			}
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
     }
	
	
	
	@SuppressWarnings({ "unused", "static-access" })
	/**
	 * 接口地址添加
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/jurmanagement/addinterface", produces = "text/html;charset=UTF-8")
	public void  addInterFace(HttpServletRequest request,HttpServletResponse response, @RequestParam("interface") String interFaceObj) {
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
				 Boolean flag = false;
				 JSONObject jsonObject = JSONObject.fromObject(interFaceObj);
 				 InterFaceObj interfaces =(InterFaceObj)JSONObject.toBean(jsonObject, InterFaceObj.class);
 				 String mumeId = interfaces.getMenu_id();
 				 String [] intefaces = interfaces.getInterarray();
 				 if(mumeId == null || mumeId =="" || "".equals(mumeId) ||intefaces == null ||intefaces.length ==0   ){
					 result.setCode("2");
 					 result.setMsg("添加失败,必要参数为空");
				 }else{
  					 //删除菜单下原有的接口
 					 Boolean deleteResult = roleService.deleteInterFace(mumeId);
 					for(int i=0;i<intefaces.length;i++){
						String infa = intefaces[i];
 						InterFace Infa = new InterFace();
						Infa.setMenu_id(mumeId);
						Infa.setInterface_address(infa);
					    flag = roleService.addInterface(Infa);
 					}
					if(flag){
						result.setCode("1");
	 					result.setMsg("添加成功");
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
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询省分、地市
	 * 
	 */
 	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/jurmanagement/jurcitylist",produces="text/html;charset=UTF-8")
    public void getjurCity(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "prov_id",required = false) String prov_id)  {
    	
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
	 	 		CityCode cityCode = new CityCode();
	 	 		
				if(null == prov_id || "".equals(prov_id) || "null".equals(prov_id) || "" == prov_id){
					//
				}else{
					cityCode.setProv_id(prov_id);
				}
				List<Map> citylist = roleService.jurcitylist(cityCode); 
 	 	 		result.setCode("1");
	 		    result.setMsg("查询成功");
	 			result.setData(citylist);
    			} catch (Exception e) {
   			     result.setCode("2");
				 result.setMsg("未知错误");
				 e.printStackTrace();
			}
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
     }

	
	
	@SuppressWarnings({ "unused", "static-access" })
	/**
	 * 修改角色下的菜单
 	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/jurmanagement/savejurmenu", produces = "text/html;charset=UTF-8")
	public void  savejurmenu(HttpServletRequest request,HttpServletResponse response, @RequestParam("menu_jur") String menu_jur) {
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
  				 Boolean flag = false;
				 JSONObject jsonObject = JSONObject.fromObject(menu_jur);
				 Menu_jur menujurs =(Menu_jur)JSONObject.toBean(jsonObject, Menu_jur.class);
 				 String role_id = menujurs.getRole_id();
  				JSONArray jsonArray = JSONArray.fromObject(menujurs.getRole_check_menu());
 		        List<RoleMenu> menujur = jsonArray.toList(jsonArray,RoleMenu.class);
   				 if(role_id == null || role_id =="" || "".equals(role_id) || menujur == null ||menujur.size() == 0   ){
					 result.setCode("2");
 					 result.setMsg("添加失败,缺少必要参数");
				 }else{
					 for(RoleMenu rm : menujur){
						 rm.setRoleId(role_id);
						 List<Map<String, String>> menuResult =  roleMenuService.getRoleMenuList(rm);
						 if(menuResult.size() == 0 && "1".equals(rm.getMenu_check()) ){ //当不存在时并且选中时添加
							 rm.setMenuId(rm.getMenu_id()+"");
							 roleMenuService.saveOrUpdateRoleMenu(rm);
						 }else if(menuResult.size() != 0 && "0".equals(rm.getMenu_check())){//存在未选中时删除
							 roleMenuService.delRoleMenuByRoleIdAndMenuId(rm);
						 }
 						 flag = true;
					 }
 					if(flag){
						result.setCode("1");
	 					result.setMsg("添加成功");
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
			out.print(json.toJson(result));
			if (jsonP) {
				out.write(");");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 菜单，角色下的权限信息
	
	
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/jurmanagement/jurdetaillist", produces = "text/html;charset=UTF-8")
	public void getjurdetaillist(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("menu_id") String menu_id,
			@RequestParam("role_id") String role_id) {
		ResultMessage2 result = new ResultMessage2();
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
 			try {
 				if (null == menu_id || "" == menu_id || null == role_id || "" == role_id ) {
 					result.setCode("2");
					result.setMsg("参数存在空值，查询失败");
 					
				} else {
					String roleid = role_id;
 					JurType jurType = getJurDetail(menu_id, roleid, null, null,"0");
 					result.setCode("1");
 					result.setMsg("查询成功");
 					result.setData(jurType);
				}
 			} catch (Exception e) {
				result.setCode("2");
				result.setMsg("未知错误");
				e.printStackTrace();
			}
			out.print(json.toJson(result));
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String uuid5(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		uuid = uuid.substring(uuid.length()-5);
		return uuid;
	}
 	
}
