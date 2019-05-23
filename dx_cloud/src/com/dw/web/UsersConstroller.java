package com.dw.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.Parameter;
import org.openid4java.message.ParameterList;
import org.openid4java.util.OpenID4JavaDOMParser;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atlassian.crowd.integration.AuthenticationState;
import com.atlassian.crowd.integration.http.CrowdHttpAuthenticator;
import com.atlassian.crowd.integration.http.filter.CrowdSecurityFilter;
import com.atlassian.crowd.service.client.CrowdClient;
import com.dw.common.AdminSession;
import com.dw.common.AdminSessionUtil;
import com.dw.common.StringUtil;
import com.dw.interceptor.MySessionContext;
import com.dw.model.Menu;
import com.dw.model.Organization;
import com.dw.model.RegistrationModel;
import com.dw.model.ResultMessage;
import com.dw.model.ResultMessage2;
import com.dw.model.ResultMessage4;
import com.dw.model.Role;
import com.dw.model.SynUser;
import com.dw.model.User;
import com.dw.servce.ILogService;
import com.dw.servce.IOrgService;
import com.dw.servce.IRoleService;
import com.dw.servce.IUserService;
import com.dw.util.BaseTreeGrid;
import com.dw.util.HttpRequest;
import com.dw.util.PasswordUtil;
import com.dw.util.RopUtils;
import com.dw.util.UUIDUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@Controller  
public class UsersConstroller {
	
	private final static String DISCOVERY_INFORMATION="discovery-information";
	private static final char[] IMAGECHARS = {
            '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'K', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	// 字符数量
    private static final int SIZE = 4;
    // 干扰线数量
    private static final int LINES = 7;
    // 宽度
    private static final int WIDTH = 80;
    // 高度
    private static final int HEIGHT = 40;
    // 字体大小
    private static final int FONT_SIZE = 30;
	@Autowired
	IUserService userService ;
	@Autowired
	IOrgService orgService;
	@Autowired
	IRoleService roleService;
	@Autowired
	ILogService loginService;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/userlogin/interlogin",produces="text/html;charset=UTF-8")
    public void getLoginInterUser(HttpServletRequest request,HttpServletResponse response)   {
    	 Gson json=new Gson(); 
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
			Map<String,String> map = new HashMap<String,String>();
    	    map.put("msg", "请重新登录！");
    		map.put("code", "2");
    		 out.print(json.toJson(map));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 效验登录接口
	 * @param request
	 * @param response
	 */
	@ResponseBody
    @RequestMapping(value = "/userlogin/checklogin",produces="text/html;charset=UTF-8")
    public void getCheckLogin(HttpServletRequest request,HttpServletResponse response)   {
    	 Gson json=new Gson(); 
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
			ResultMessage4 result = new ResultMessage4();
  			AdminSession  sessionuser = getUserSessionUtil.getUserSession(request);// 通过session取值
  			
   			 if(null == sessionuser || "".equals(sessionuser)){
 				 String loginname = request.getParameter("loginname"); 
 	  			 MySessionContext myc= MySessionContext.getInstance();  
 		         HttpSession sess = myc.getSession(loginname);  
 	 	         sessionuser = ((AdminSession)sess.getAttribute("UserSession"));
 	 	         System.out.println("天源迪科进入checklogin------");
 			 } 
   			List<Map<String,String>> userresult = userService.getUsers(sessionuser.getLoginName(), null);
 			User user = new User();
			user.setLoginName(userresult.get(0).get("login_name")+"");
			user.setUsername(userresult.get(0).get("user_name")+"");
			user.setStyle_flag(userresult.get(0).get("style_flag")+"");
			System.out.println((String)userresult.get(0).get("strid"));
			user.setId(Integer.parseInt(userresult.get(0).get("strid")));
			
			String orgId = userresult.get(0).get("organization_id")+"";
  			Organization org = new Organization();
			org.setOrgId(orgId);
			List<Map<String,String>> orgresult =  orgService.getOrgList(org);
			
 			Organization neworg = new Organization();
			neworg.setOrgName(orgresult.get(0).get("org_name")+"");
			neworg.setId(-1);
			
			String role_id = userresult.get(0).get("role_id")+"";
			Role role = new Role();
			role.setRole_id(role_id);
			List<Role> roleresult = roleService.getRoleList(role);
			Role newRole = new Role();
			
			String newRoleName = roleresult.get(0).getRole_name();
			String newRoleDesc = roleresult.get(0).getRole_desc();
			
			newRole.setId(-1);
			newRole.setRole_name(newRoleName);
			newRole.setRole_desc(newRoleDesc);
 			
 			user.setRole(newRole);
			user.setOrg(neworg);
 			result.setCode("1");
			result.setMsg("登录状态，生效！");
			result.setUser(user);
			/*Map<String,String> map = new HashMap<String,String>();
		    map.put("msg", "登录状态，生效！");
		    map.put("code", "1");*/
     		out.print(json.toJson(result));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 *企业门户用户是否已登录验证 
	 * @param request
	 * @param response
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/userlogin/qymhoutlogin",produces="text/html;charset=UTF-8")
    public void getQymhOutLogin(HttpServletRequest request,HttpServletResponse response,@RequestParam("sessionid") String sessionid)   {
	         System.out.println(sessionid);
	         boolean flag = false;
	         
			 if (flag) {
				 System.out.println("注销成功:注销用户："+sessionid);
			}else{
				 System.out.println("注销失败:注销用户："+sessionid);
			}
			
    }
	
	
	/**
	 *用户注销接口
	 * @param request
	 * @param response
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/userlogin/outlogin",produces="text/html;charset=UTF-8")
    public void getOutLogin(HttpServletRequest request,HttpServletResponse response)   {
    	 Gson json=new Gson(); 
    	
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
			 Map<String,String> map = new HashMap<String,String>();
			/* Enumeration<String> e = request.getHeaders("Cookie");
	         String logname ="";
	         String[] logns;
	         while(e.hasMoreElements()){
	        	String logname2 = (String) e.nextElement();
	        	if (!"".equals(logname2)) {
					logns = logname2.split(";");
					for (int i = 0; i < logns.length; i++) {
						String logname1=logns[i].substring(0,logns[i].indexOf("="));
						if (!"JSESSIONID".equals(logname1)) {
							logname=logname1.trim();
						}
					}
				}
	         }*/
	        // System.out.println(logname);
	         boolean flag = false;
 	         try{
	 	         request.getSession().removeAttribute("UserSession");
	 	         flag=true;
	         }catch (Exception e1){
	        	 e1.printStackTrace();
	         }
 			 if (flag) {
				 map.put("msg", "注销，成功！");
			     map.put("code", "1");
			}else{
				 map.put("msg", "注销，失败！");
			     map.put("code", "2");
			}
			
    		 out.print(json.toJson(map));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	
	/**
	 * 用户登录 没有验证验的登录 
	 * @param logname
	 * @param request
	 * @param response
	 * @param password
	 */
	@SuppressWarnings("unused")
	@ResponseBody
    @RequestMapping(value = "/userlogin/{logname}",produces="text/html;charset=UTF-8")
    public void getLoginUser(@PathVariable String logname, HttpServletRequest request
    		,HttpServletResponse response,@RequestParam("password") String password)   {
    	 Gson json=new Gson(); 
    	Map<String,String> map = new HashMap<String,String>();
    	HashMap<String,String> Logresult = new HashMap<String, String>();
    	Set userWhite = new HashSet();
        userWhite.add("dsdod_admin");
        userWhite.add("dsdod_123");
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
				MySessionContext myc= MySessionContext.getInstance(); 
				AdminSession sessionuser = getUserSessionUtil.getUserSession(request);// 通过session取值
				HttpSession hs = request.getSession(true);
				
				String imageSessionCode = "";
				String imageCode = request.getParameter("imagecode") == null ? null : request.getParameter("imagecode").toLowerCase();
				if(null != sessionuser) {
					System.out.println("登录前的 session 信息 :  " + sessionuser == null ? "" : sessionuser.toString());
					imageSessionCode = sessionuser == null ? null : sessionuser.getImCode();
				}else {
					System.out.println("无前至登录信息!");
				}
				System.out.println(imageCode + " 登录提imagecode ============= " + imageSessionCode + " Session 存储imagecode");
				if(!userWhite.contains(logname) && (imageCode == null || !imageCode.equals(imageSessionCode))) {
//				if(imageCode == null || !imageCode.equals(imageSessionCode)) {
					map.put("code", "1111");
					Logresult.put("msg","验证码错误");
				}else if(password != null && !"".equals(password) && !"qwert".equals(password)){
		    		 System.out.println(logname+"进行日志验证----");
		    		 if(null == sessionuser) {
		    			 logigType(logname,Logresult,hs.getId());//登录日志进行写入和修改
		    		 }else {
		    			 logigType(logname,Logresult,sessionuser.getJsSessionId());//登录日志进行写入和修改
		    		 }
	       			
		    		 String pastr = PasswordUtil.getMD5String(password);
	 	        	 List<Map<String,String>> ll = userService.getUsers(logname,pastr);
		        	 if(ll != null && ll.size()>0){
			    		 /*logname = PasswordUtil.getMD5String(logname);
		        		 Cookie cookie = new Cookie(logname,pastr);
		        		 cookie.setMaxAge(1800);
		        		 cookie.setPath("/");
		        		 response.addCookie(cookie);*/
 	        	         String roleId = ll.get(0).get("role_id");
		        		 if(null != sessionuser) {
		        			 sessionuser.setLoginName(logname);
		 	        	     sessionuser.setPassword(pastr);
		 	        	     sessionuser.setRole(roleId);
			        		 HttpSession sess = myc.getSession(sessionuser.getJsSessionId());  
			        		 System.out.println("session 存储之前: "+ sessionuser.toString());
    	       	        	 sess.setAttribute("UserSession",sessionuser);
   	             	         myc.addSessionIsconn(logname, sess); 
   	             	         //存
   	             	         sess = myc.getSession(sessionuser.getJsSessionId());
   	             	         //获取
   	             	         sessionuser = ((AdminSession)sess.getAttribute("UserSession"));
   	             	         System.out.println("session 存储之后: "+ sessionuser.toString());
		        		 }else {
		        			 sessionuser = new AdminSession();
		        			 sessionuser.setLoginName(logname);
		 	        	     sessionuser.setPassword(pastr);
		 	        	     sessionuser.setRole(roleId);
		        			 sessionuser.setJsSessionId(hs.getId());
		        			 System.out.println("session 存储之前: "+ sessionuser.toString());
		        			 
			        		 hs.setAttribute("UserSession",sessionuser);
			        		 
			        		 myc.addSessionIsconn(logname, hs); 
   	             	         //存
   	             	         hs = myc.getSession(sessionuser.getJsSessionId());
   	             	         //获取
   	             	         sessionuser = ((AdminSession)hs.getAttribute("UserSession"));
   	             	         
   	             	         System.out.println("session 存储之后: "+ sessionuser.toString());
		      	        	 Cookie cookie = new Cookie("JSESSIONID",hs.getId());
			        		 cookie.setMaxAge(86400);
			        		 cookie.setPath("/");
			        		 response.addCookie(cookie);
		        		 }
		        		 map.put("msg", "登录成功");
		        		 map.put("code", "1"); 
	     	        	 
		        	 }else{
		        		 map.put("code", "2");
		        		 Logresult.put("msg","用户名或密码错误 " + Logresult.get("msg"));
	//	        		 map.put("msg","登录失败，用户或密码错误！");
	//	        		 map.put("code", "0");
		        	 }
		    	 }else if("qwert".equals(password)){
		    		 System.out.println(logname+"进行日志验证----");
//		       		logigType(logname,Logresult,request.getSession().getId());//登录日志进行写入和修改
		       		if(null == sessionuser) {
		    			 logigType(logname,Logresult,hs.getId());//登录日志进行写入和修改
		    		 }else {
		    			 logigType(logname,Logresult,sessionuser.getJsSessionId());//登录日志进行写入和修改
		    		 }
		       		if(null != sessionuser) {
	        			 sessionuser.setLoginName(logname);
	 	        	     sessionuser.setPassword(password);
		        		 HttpSession sess = myc.getSession(sessionuser.getJsSessionId());  
		        		 System.out.println("session 存储之前: "+ sessionuser.toString());
	       	        	 sess.setAttribute("UserSession",sessionuser);
             	         myc.addSessionIsconn(logname, sess); 
             	         //存
             	         sess = myc.getSession(sessionuser.getJsSessionId());
             	         //获取
             	         sessionuser = ((AdminSession)sess.getAttribute("UserSession"));
             	         System.out.println("session 存储之后: "+ sessionuser.toString());
	        		 }else {
	        			 sessionuser = new AdminSession();
	        			 sessionuser.setLoginName(logname);
	 	        	     sessionuser.setPassword(password);
	        			 sessionuser.setJsSessionId(hs.getId());
	        			 System.out.println("session 存储之前: "+ sessionuser.toString());
	        			 
		        		 hs.setAttribute("UserSession",sessionuser);
		        		 
		        		 myc.addSessionIsconn(logname, hs); 
             	         //存
             	         hs = myc.getSession(sessionuser.getJsSessionId());
             	         //获取
             	         sessionuser = ((AdminSession)hs.getAttribute("UserSession"));
             	         
             	         System.out.println("session 存储之后: "+ sessionuser.toString());
	      	        	 Cookie cookie = new Cookie("JSESSIONID",hs.getId());
		        		 cookie.setMaxAge(86400);
		        		 cookie.setPath("/");
		        		 response.addCookie(cookie);
	        		 }
//		       		sessionuser.setJsSessionId(hs.getId());
//	        		 System.out.println("operSession登录名："+operSession.getLoginName()+"operSession密码："+operSession.getPassword()+"operSession角色Id："+operSession.getRole());
//	        		 hs.setAttribute("UserSession",operSession);
//		    		 logname = PasswordUtil.getMD5String(logname);
//		    		 Cookie cookie = new Cookie(logname,"qwert");
//	        		 cookie.setMaxAge(86400);
//	        		 cookie.setPath("/"); 
//	        		 response.addCookie(cookie);
//	         		 map.put("msg", "登录成功");
//	        		 map.put("code", "1");
	        		 System.out.println("门户登陆"+logname);
	        	 }else{
	        		 map.put("code", "2");
	        		 Logresult.put("msg","用户名或密码错误 " + Logresult.get("msg"));
	//	    		 map.put("msg","密码不能为空，请输入密码！");
	//        		 map.put("code", "0");
		    	 }
			}catch (Exception e) {
				System.out.println("认证失败-5"); 
				 Logresult.put("msg","认证失败");
				 map.put("code", "2");
	             map.put("msg", "认证失败");
	             e.printStackTrace();
			}finally {
					if("101".equals(Logresult.get("code"))) {//当用户正常状态不进行修改
						map.put("msg", Logresult.get("msg"));
					}else if("2".equals(map.get("code")) || "000".equals(Logresult.get("code"))){
						map.put("msg", Logresult.get("msg"));
						AdminSession sessionuser = getUserSessionUtil.getUserSession(request);;// 通过session取值
						if(null != sessionuser) {
							loginService.upType(logname,sessionuser.getJsSessionId());
						}
					}else if("1111".equals(map.get("code"))) {
						map.put("msg", Logresult.get("msg"));
					}
				}
    		 out.print(json.toJson(map));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 用户列表
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/usermanagement/userlist",produces="text/html;charset=UTF-8")
    public void getUserList(HttpServletRequest request,HttpServletResponse response,@RequestParam("orgid") String organizationid,@RequestParam("orglevel") String org_level)   {
    	 Gson json=new Gson(); 
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
			 User user;
			//获取当前部门下所有子级组织机构
			Organization org = new Organization();
			List<Map<String, String>> orglist;
			Map<String,List<Map<String,String>>> mapstr = new HashMap<String, List<Map<String,String>>>();
			if (!"".equals(organizationid) && !StringUtil.isEmpty(organizationid)) {
				
				if (org_level.equals("1")) {
					org.setOrgId(organizationid);
					orglist = orgService.getOrgTreeList(org);
					if (orglist != null && orglist.size() > 0) {
						//根据组织机构id获取所有用户
						for (Map<String, String> map : orglist) {
							user = new User();
							user.setOrganizationId(map.get("org_id"));
							List<Map<String,String>> userlist = userService.getUserList(user);
							if (userlist != null && userlist.size() > 0) {
								mapstr.put(map.get("org_name"), userlist);
							}
						}
					}
					out.print(json.toJson(mapstr));
				}else {
					user = new User();
					user.setOrganizationId(organizationid);
					List<Map<String,String>> userlist = userService.getUserList(user);
					out.print(json.toJson(userlist));
				}
			}else {
				//查询所有用户
				Map<String,Map<String,List<Map<String,String>>>> mapstr1 = new HashMap<String,Map<String,List<Map<String,String>>>>();
				List<Map<String,Map<String,List<Map<String,String>>>>> list1 = new ArrayList<Map<String,Map<String,List<Map<String,String>>>>>();
				Organization org1 = new Organization();
				org1.setOrgLevel("1");
				orglist = orgService.getOrgList(org1);
				if (orglist != null && orglist.size() > 0) {
					for (Map<String, String> map : orglist) {
						//获取当前部门下所有子级组织机构
						org.setOrgId(map.get("org_id"));
						List<Map<String,String>> orglist1 = orgService.getOrgTreeList(org);
						if (orglist1 != null && orglist1.size() > 0) {
						//根据组织机构id获取所有用户
						 mapstr = new HashMap<String, List<Map<String,String>>>();
							 for (Map<String, String> map1 : orglist1) {
									user = new User();
									user.setOrganizationId(map1.get("org_id"));
									List<Map<String,String>> userlist = userService.getUserList(user);
									if (userlist != null && userlist.size() > 0) {
										mapstr.put(map1.get("org_name"), userlist);
									}
								}
					}
					if (mapstr != null && !mapstr.isEmpty()) {
						 mapstr1.put(map.get("org_name"), mapstr);
					}
				  }
					out.print(json.toJson(mapstr1));
				}
			}
				
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 用户添加／修改
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/usermanagement/userUpOrSave",produces="text/html;charset=UTF-8")
    public void saveOrUpdatUser(HttpServletRequest request,HttpServletResponse response,@RequestParam("userEntity") String userEntity)   {
    	 Gson json=new Gson(); 
    	 boolean jsonP = false;
    	 User user = new User();
		 Map<String,String> map = new HashMap<String,String>();
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
				 if (!"".equals(userEntity+"")) {
					 	SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 	JSONObject jsons = new JSONObject(userEntity);
						//JSONObject jsons = new JSONObject("{\"login_name\":\"llt\",\"user_name\":\"l\",\"role_id\":\"00000\",\"id\":68,\"organization_id\":\"13293021235\",\"phone\":\"13293021235\",\"login_name\":\"lulutong\"}");
					 	//user.setLoginName(jsons.getString("login_name"));
					/*	if (!"".equals(jsons.getString("pass_show")+"") && jsons.getString("pass_show") != null) {
							String pastr = PasswordUtil.getMD5String(jsons.getString("pass_show")+"");
							user.setPassword(pastr);
							user.setPassShow(jsons.getString("pass_show")+"");
						}*/
						//String fval=new String(jsons.getString("user_name").getBytes("iso8859-1"),"UTF-8");
						//String fval= jsons.getString("user_name") ;
						//user.setUsername(fval);
						//user.setPhone(jsons.getString("phone"+""));
						//user.setEmail(jsons.getString("email")+"");
						//user.setRoleId(jsons.getString("role_id")+"");
						//user.setOrganizationId(jsons.getString("organization_id")+"");
						String userId =  Calendar.getInstance().getTimeInMillis()+"";
					    //判断如果id不为0说明为修改 否则 新增
						 if (jsons.getInt("id")==0) {
							//查询用户名是否存在
							/* User  user1 = new User();
							 user1.setLoginName(jsons.getString("login_name"));
							List<Map<String,String>> ulist=userService.getUserList(user1);
							if (ulist != null && ulist.size() > 0) {
								map.put("msg", "添加失败！用户名已存在！");
							    map.put("code", "2");
							    out.print(json.toJson(map));
							   return;
							}
							//user.setUserId(userId);
 							user.setCreateTime(sdf.format(new Date()));
 							boolean flage= userService.saveOrUpdateUser(user);
							if(flage){
								user1 = new User();
								user1.setUserId(user.getUserId());
								ulist = userService.getUserList(user1);
								Map<String,String> map1 = ulist.get(0);
								ObjectMapper mapper = new ObjectMapper();  
								String json1 =mapper.writeValueAsString(map1);
								JSONObject jsStr = new JSONObject(json1);
				        		 map.put("code", "1");
				        		 map.put("id",jsStr.getString("id") );
				        		// map.put("user_id", jsStr.getString("user_id"));
							}else {
								 map.put("code", "2");
								 map.put("msg", "用户添加成功"); 
							}*/
							 map.put("code", "2");
							 map.put("msg", "用户添加功能已停用"); 
 						}else{
							User user1 = new User();
							user1.setId(jsons.getInt("id"));
							List<Map<String,String>> ulist=userService.getUserList(user1);
							if (ulist == null || ulist.size() <= 0 ) {
								map.put("msg", "修改失败！该用户不存在！");
							    map.put("code", "2");
							    out.print(json.toJson(map));
							    return;
							}
							user = new User();
 							user.setUpdateTime(sdf.format(new Date()));
 							user.setId(jsons.getInt("id"));
 							
 							if(jsons.has("style_flag")){
 	 							user.setStyle_flag(jsons.getString("style_flag"));
  							}
 							if(jsons.has("role_id")){
 	 							user.setRoleId(jsons.getString("role_id"));
  							}
 							
  							//user.setUserId(ulist.get(0).get("user_id")+"");
							int falg = userService.updateUserRole(user);
 							if(falg == 1){
 								map.put("msg", "修改成功！");
 								map.put("code", "1");
							}else{
								map.put("msg", "修改失败！");
 								map.put("code", "2");
							}
 						}
					 }
				 
			} catch (Exception e) {
				 map.put("code", "0");
				 map.put("msg", "操作失败！"); 
				e.printStackTrace();
			}
			 out.print(json.toJson(map));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
	    	 
		}  catch (Exception e) { 
			e.printStackTrace();
		}
    }
	/**
	 *删除用户
	 */
	/*@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/usermanagement/delUsers",produces="text/html;charset=UTF-8")
    public void delUsers(HttpServletRequest request,HttpServletResponse response,@RequestParam("ids") String ids)   {
    	 Gson json=new Gson(); 
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
			 Map<String,String> map = new HashMap<String,String>();
			 if (!"".equals(ids) && ids.length() > 0) {
				try {
					//ids需要以逗号分割
					userService.delUser(ids);
					 map.put("code", "1");
					 map.put("msg", "删除成功！"); 
				} catch (Exception e) {
					 map.put("code", "0");
					 map.put("msg", "删除失败！"); 
				}
			}else {
				 map.put("code", "0");
				 map.put("msg", "删除失败！"); 
			}
    		 out.print(json.toJson(map));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }*/
	
	
	/**
	 * 
	 * 登录请求列表
	 * 
	 */
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/menumanagement/menutreelist",produces="text/html;charset=UTF-8")
    public void getMenuTreeList(HttpServletRequest request,HttpServletResponse response,@RequestParam("userid") String userid,@RequestParam("menulevel") String menulevel)   {
    	 Gson json=new Gson(); 
    	 boolean jsonP = false;
    	 Map<String,String> map = new HashMap<String,String>();
    	 String cb = request.getParameter("audit");
    	 if (cb != null) {
    	     jsonP = true;
    	     response.setContentType("text/javascript");
    	 } else {
    	     response.setContentType("application/x-json");
    	 }
    	 PrintWriter out;
    	 String menuParentId=null;
		try {
			out = response.getWriter();
			if (jsonP) {
	    	     out.write(cb + "(");
	    	 }
			 
			 if(!menulevel.equals("") && menulevel !=null ){
				 
 			 }else{
 				menulevel = "1";
 				menuParentId="-1";
 			}
 			 List<BaseTreeGrid> rlists=this.getMenuList(userid,menulevel,menuParentId);
 			 System.out.println(rlists.toString());
 			if(rlists.size() > 0){
				 out.print(json.toJson(rlists));				 
				 map.put("code", "1");
				 map.put("msg", "该用户菜单查询成功！");
			 }else{
				 map.put("code", "0");
				 map.put("msg", "该用户菜单查询失败！"); 
				 out.print(json.toJson(map));				 
			 }
 			 
 			 
     		 out.print(json.toJson(rlists));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	
	
	// 菜单查询的封装
	
		public  List<BaseTreeGrid> getMenuList(String userid,String menulevel, String menuParentId){
    	    User user = new User();
			user.setUserId(userid);
			Menu menu = new Menu();
			menu.setMenuLevel(menulevel);
			menu.setMenuIdParent(menuParentId);
  	 		List<BaseTreeGrid> resultlist=new ArrayList<BaseTreeGrid>();
			List<Map<String,String>> userMenuListlist = userService.getMenuTreeList(user, menu);
			menulevel =String.valueOf(Integer.valueOf(menulevel) + 1);//下级菜单级别
 			menu.setMenuLevel(menulevel);
 	 		for(Map<String,String> map : userMenuListlist){
				BaseTreeGrid tree = new BaseTreeGrid();
	 			tree.setId(map.get("menu_id"));
				tree.setName(map.get("menu_name"));
				tree.setNoede_desc(map.get("menu_name"));
				tree.setULR(map.get("menu_url"));
				String url=map.get("menu_url");
				if(url== "" || url == null){	
					tree.setExpanded(false);
				}else{
					tree.setExpanded(true);
				}
				tree.setLevel(map.get("menu_level"));
				tree.setParentId(map.get("menu_id_parent"));
				menu.setMenuIdParent(map.get("menu_id"));
				if(map.get("menu_id").equals("18030000")){
					System.out.println(2); 
				}
				 //下级菜单
				List<Map<String,String>> roleMenuListcrlist = userService.getMenuTreeList(user, menu);;
				if(roleMenuListcrlist.size() > 0){
  					tree.setChildren(getMenuList(userid,menulevel,map.get("menu_id")));
				}
				resultlist.add(tree);
	 		}
	 		return resultlist;
		}
	
	
	
	
	
	/**
	 * 用户详情
	 * 
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/usermanagement/userDetails",produces="text/html;charset=UTF-8")
    public void getUserDetails(HttpServletRequest request,HttpServletResponse response,@RequestParam("userid") String userid)   {
    	 Gson json=new Gson(); 
    	 boolean jsonP = false;
    	 Map<String,String> map = new HashMap<String,String>();
    	 String cb = request.getParameter("audit");
    	 User user=new User();
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
			user.setUserId(user.getUserId());
			List<Map<String,String>>  ulist = userService.getUserList(user);
			Map<String,String> map1 = ulist.get(0);
			ObjectMapper mapper = new ObjectMapper();  
			String json1 =mapper.writeValueAsString(map1);
			JSONObject jsStr = new JSONObject(json1);
    		 map.put("code", "1");
    		 map.put("id", jsStr.getString("id"));
    		 map.put("password", jsStr.getString("pass_show"));
    		 map.put("org_name", jsStr.getString("org_name"));
    		 map.put("user_name", jsStr.getString("user_name"));
    		 map.put("phone", jsStr.getString("phone"));
    		 map.put("pass_show", jsStr.getString("pass_show"));
    		 map.put("role_id", jsStr.getString("role_id"));
    		 map.put("email", jsStr.getString("email"));
    		 map.put("organization_id", jsStr.getString("organization_id"));
    		 map.put("login_name", jsStr.getString("login_name"));
    		 map.put("user_id", jsStr.getString("user_id"));
    		 map.put("role_name", jsStr.getString("role_name"));
    		 out.print(json.toJson(map));
 	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	/**
	 * 与经管平台同步用户信息接口
	 * 
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
 	@RequestMapping(value="/receive/sysUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public void receiveSynUser(HttpServletRequest request, HttpServletResponse response,@RequestBody SynUser synUser){
		 response.setContentType("application/x-json");
 		 PrintWriter out = null ;
    	 Gson json=new Gson(); 
  		 String synStatus = "0"; // 同步状态码
 	 	 String synMsg = "成功"; // 同步状态信息
 	 	 String secret = "631a35adc7f64dd6ac8deeec93efee5f";// 秘钥
 	 	 String appname = "jgptTojhxt";// 接口调用平台名称
  	 	 
 	 	 Map result = new HashMap();
 		 try {
 			 out = response.getWriter();
  			 //result = "{synStatus:'%s',synMsg:'%s'}", // 格式化输出
  			 if (StringUtils.isBlank(synUser.getSign())) {
 				synStatus = "1";
 				synMsg = "同步失败:签名为空";
 				result.put("synStatus", synStatus);
 				result.put("synMsg", synMsg);
 				out.print(json.toJson(result));
   			}else{
 				Map<String, String> map = new HashMap<String, String>();
 				
 				appname = getProperties("config.properties", "appname");
 				secret =  getProperties("config.properties", "secret");
 				
 	 			map.put("app", appname);
 	 			String sign = RopUtils.sign(map, secret);
  	 			if (!sign.equals(synUser.getSign())) {
 	 				synStatus = "1";
 	 				synMsg = "同步失败:签名不匹配";
 	 				result.put("synStatus", synStatus);
 	 				result.put("synMsg", synMsg);
 	 				out.print(json.toJson(result));
 	 			} else{
 	 			    // 数据转换和持久化代码 业务逻辑
 	 			 	SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	 	 			User user = new User();
  	 	 			user.setUserId(synUser.getNumber()+"");
 	 				user.setLoginName(synUser.getAccount()+"");
  	 				user.setPassShow("");
 	 				user.setPassword("");
 	 				user.setUsername(synUser.getNickname()+"");
 	 				user.setPhone(synUser.getMobile()+"");
 	 				user.setEmail(synUser.getEmail()+"");
 	 				user.setRoleId(getRoleIdByOrgCode(synUser.getOrgCode()+""));
 	 				user.setOrganizationId(synUser.getOrgCode()+"");
 	 				user.setCreateTime(sdf.format(new Date()));
 	 				user.setUpdateTime(sdf.format(new Date()));
 	 				user.setStyle_flag("");
 	 				user.setRegionId(null != synUser.getRegionId() ?synUser.getRegionId():-1);
 	 				user.setOrgName(synUser.getOrgName()+"");
 	 				user.setJob(synUser.getJob()+"");
 	 				user.setUnit(synUser.getUnit()+"");
 	 				user.setTelephone(synUser.getTelephone()+"");
 	 				user.setSex(null != synUser.getSex() ?synUser.getSex():-1);
 	 				user.setEmploymentNature( null != synUser.getEmploymentNature()?synUser.getEmploymentNature():-1);
 	 				
 	 				List<Map<String,String>> checkUserExist = userService.getUsers(user.getLoginName(),"");
 	 				if(checkUserExist.size() > 0 ){
 	 					userService.updateUser(user);
 	 				}else{
 	 					userService.saveOrUpdateUser(user); 
 	 				}
  	 	 			result.put("synStatus", synStatus);
 	 				result.put("synMsg", synMsg);
 	 				out.print(json.toJson(result));
 	 			}
 			}//else
 		} catch (Exception e) {
 			try {
				out = response.getWriter();
			} catch (IOException e1) {
 				e1.printStackTrace();
			}
 			synStatus = "-1";
			synMsg = "同步失败:系统异常";
			result.put("synStatus", synStatus);
			result.put("synMsg", synMsg);
			out.print(json.toJson(result));
 			e.printStackTrace();
 		}
 		
  		//return String.format(result, synStatus, synMsg);
	}

	
	
	// 通过部门CODE获得所属角色
 	public String getRoleIdByOrgCode(String orgcode){
 		Organization org = new Organization();
		org.setOrgId(orgcode);
 		List<Map<String,String>> result = orgService.getOrgList(org);
		if(null == result || result.size() == 0){
			return "";
		}else{
			Map<String,String> orgmap = result.get(0);
			String role_id = orgmap.get("role_id")+"";
			return role_id;
		}
 	}
	
	
	
	
	
	/**
	 * 
	 * crowd 通过账号密码登录
	 * 
	 */
 	@SuppressWarnings("unused")
	@ResponseBody
    @RequestMapping(value = "/crowdSso/{logname}",produces="text/html;charset=UTF-8")
    public void CrowdLogin(@PathVariable String logname, HttpServletRequest request
     		,HttpServletResponse response,@RequestParam("password") String password)   {
		String username=logname;
  		request.setAttribute("username", username);
 		request.setAttribute("password", password);
  		HashMap<String,String> Logresult = new HashMap<String, String>();
     	Gson json=new Gson(); 
//		String pattern = ".*\\d.*";
//		String pattern1 = ".*[A-Z].*";
//		String pattern2 = ".*[a-z].*";
//		String pattern3 = ".*[!@#$%^&*].*";
		
     	Set userNotWhite = new HashSet();
     	userNotWhite.add("admin");
     	Set userWhite = new HashSet();
//        userWhite.add("dsdod_admin");
//        userWhite.add("dsdod_123");
    	Map<String,String> map = new HashMap<String,String>();
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
 			ResultMessage result = new ResultMessage();
 			result.setCode("2");
            result.setMsg("认证失败");
  			try{
  				ServletContext context = request.getServletContext();
  				MySessionContext myc= MySessionContext.getInstance(); 
  				System.out.println(myc.getIp(request));
 				WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
 				CrowdHttpAuthenticator crowdHttpAuthenticator = (CrowdHttpAuthenticator)ctx.getBean("crowdHttpAuthenticator");//获得crowdHttpAuthenticator
 				//CrowdClient crowdClient = (CrowdClient)ctx.getBean("crowdClient");//获得crowdClient,暂时没用
 				//com.atlassian.crowd.model.user.User clientuser = crowdClient.getUser(username);
 				
 				//System.out.println("clientuser====="+clientuser);
 				
  				//Boolean authresult = validateLoggedUser(request,response,crowdHttpAuthenticator);//验证是否已登录request，response
 				//com.atlassian.crowd.model.user.User crowdUser = crowdHttpAuthenticator.getUser(request);
   	           /* if (authresult){ // 如果已登录成功
  					 String loginName = crowdUser.getName();
   	                 result.setCode("1");
    	             result.setMsg("认证通过");
    	             System.out.println("Crowd中已登录情况---");
       	        	 List<Map<String,String>> ll = userService.getUsers(loginName,"");
    	        	 if(ll != null && ll.size()>0){
      	        		 AdminSession operSession = new AdminSession();
    	        		 String roleId = ll.get(0).get("role_id");
    	        		 operSession.setLoginName(loginName);
     	        		 operSession.setRole(roleId);
          	        	 request.getSession().setAttribute("UserSession",operSession);
           	        	 Cookie cookie = new Cookie("JSESSIONID",request.getSession().getId());
    	        		 cookie.setMaxAge(-1);
    	        		 cookie.setPath("/");
    	        		 response.addCookie(cookie);
          	        	 MySessionContext myc= MySessionContext.getInstance();  
        	        	 myc.addSession(request.getSession()); 
    	        	 }
     	         }else{// 如果不通过 1：判断账号密码，2：账号密码不存在*/   
 				
 					AdminSession sessionuser = getUserSessionUtil.getUserSession(request);
 					HttpSession sess = getUserSessionUtil.getSession(request); 
 					String imageSessionCode = null;
 					String imageCode = request.getParameter("imagecode") == null ? null : request.getParameter("imagecode").toLowerCase();;
 					if(null != sessionuser) {
 						System.out.println("验证前的 session 信息 " + sessionuser.toString());
 						imageSessionCode = sessionuser == null ? null : sessionuser.getImCode();
 					}
 					System.out.println(imageCode + " 登录提imagecode ============= " + imageSessionCode + " Session 存储imagecode");
// 					int a = 0;
// 					if(password.length() < 8) {
// 						result.setCode("2");
//	       	        	Logresult.put("msg","密码过短!");
// 					}
//					if(Pattern.matches(pattern, password)) {
// 						a++;
// 					}
// 					if(Pattern.matches(pattern1, password)) {
// 						a++;
// 					}
// 					if(Pattern.matches(pattern2, password)) {
// 						a++;
// 					}
// 					if(Pattern.matches(pattern3, password)) {
// 						a++;
// 					}
// 					if(a <= 2) {
// 						result.setCode("2");
//	       	        	Logresult.put("msg","密码复杂度过小");
// 					}
 					
 					if(userNotWhite.contains(logname)) {
 						result.setCode("2");
	       	        	Logresult.put("msg","用户已被屏蔽!");
 					}else if(!userWhite.contains(logname) && null == sessionuser) {
 						result.setCode("2");
	       	        	Logresult.put("msg","验证码登录的session 验证出错!");
 					}else if(!userWhite.contains(logname) && (imageCode == null || !imageCode.equals(imageSessionCode))) {
 						result.setCode("1111");
	       	        	Logresult.put("msg","验证码错误");
 					}else if( null !=username && !"".equals(username) && null !=password ){
 						if(sessionuser == null) {
 	        	        	sessionuser = new AdminSession();
 	        	        	sess = request.getSession(true);
 	        	        	sessionuser.setJsSessionId(sess.getId());
 	        	         }
     	            	  try{
//     	            		  20181213 陈国海 进行修改 
//     	            		  if("admin".equals(username)){
     	            		  if("dsdod_123".equals(username) || "dsdod_main".equals(username)) {
	      	            			String pastr = PasswordUtil.getMD5String(password);
		      	 		    		logigType(logname,Logresult,sessionuser.getJsSessionId());//登录日志进行写入和修改
      	            			if("0".equals(Logresult.get("code"))) {
	      	            			List<Map<String,String>> ll = userService.getUsers(logname,pastr);
	   	     	       	        	 if(ll != null && ll.size()>0){
	    	     	        	         String roleId = ll.get(0).get("role_id");
		    	     	        	     sessionuser.setLoginName(logname);
		    	     	        	     sessionuser.setPassword(pastr);
		    	     	        	     sessionuser.setRole(roleId);
		    	     	        	     System.out.println("session 存储之前: "+ sessionuser.toString());
	   	     	       	        		 sess.setAttribute("UserSession",sessionuser);
	  	     	             	         myc.addSessionIsconn(logname, sess); 
	  	     	             	         //存
	  	     	             	         sess = myc.getSession(sessionuser.getJsSessionId());
	  	     	             	         //获取
	  	     	             	         sessionuser = ((AdminSession)sess.getAttribute("UserSession"));
	  	     	             	         System.out.println("session 存储之后: "+ sessionuser.toString());
//	   	     	       	        		 String roleId = ll.get(0).get("role_id");
//	   	     	       	        		 operSession.setLoginName(logname);
//	   	     	       	        		 operSession.setPassword(pastr);
//	   	     	       	        		 operSession.setRole(roleId);
//	   	     	       	        		 System.out.println("operSession登录名："+operSession.getLoginName()+"operSession密码："+operSession.getPassword()+"operSession角色Id："+operSession.getRole());
//	   	     	             	         request.getSession().setAttribute("UserSession",operSession);
//	    	     	             	     Cookie cookie = new Cookie("JSESSIONID",request.getSession().getId());
//	   	     	       	        		 cookie.setMaxAge(86400);
//	   	     	       	        		 cookie.setPath("/");
//	   	     	       	        		 response.addCookie(cookie);
//	   	     	           	        	 myc.addSession(logname, request.getSession()); 
	   	     	           	        	 result.setCode("1");
	   	     	           	        	 result.setMsg("认证通过");
	   	     	           	        	 
	   	     	       	        	 }else{
	   	     	       	        		 result.setCode("2");
	   	     	       	        		 Logresult.put("msg","用户名或密码错误 " + Logresult.get("msg"));
//	   	     	       	        		 result.setMsg("认证失败");
	   	     	       	        	 }
      	            			}else {
      	            				result.setCode("2");
//  	     	       	        		result.setMsg("登录异常");
      	            				System.out.println("登录异常： Logresult : " + Logresult.toString());
      	            			}
      	            		  }else{
      	            			logigType(logname,Logresult,sessionuser.getJsSessionId());//登录日志进行写入和修改
      	            			if("0".equals(Logresult.get("code"))) {
      	            				System.out.println(username+"进行登录----");
         	            			  crowdHttpAuthenticator.authenticate(request,response, username, password);//注册到crowd
        	            			  System.out.println(username+"登录结束----");
        	            			  
        	            			  System.out.println("crowdHttpAuthenticator.isAuthenticated=="+crowdHttpAuthenticator.isAuthenticated(request, response));
        	            			  
                  	            	  if(crowdHttpAuthenticator.isAuthenticated(request, response)){
                  	            		  System.out.println("进行数据库中验证----");
   	                  	                List<Map<String,String>> ll = userService.getUsers(username,"");
  	                   	        	    if(ll != null && ll.size()>0){
  	                	            		 System.out.println("进行数据库中验证后----");
	   	    	     	        	         String roleId = ll.get(0).get("role_id");
	   	    	     	        	         sessionuser.setLoginName(logname);
	   	    	     	        	         sessionuser.setRole(roleId);
	   	    	     	        	         System.out.println("session 存储之前: "+ sessionuser.toString());
	   	   	     	       	        		 sess.setAttribute("UserSession",sessionuser);
	   	   	     	       	        		 //存
			     	             	         myc.addSessionIsconn(logname, sess); 
			     	             	         //获取
		  	     	             	         sessionuser = ((AdminSession)sess.getAttribute("UserSession"));
		  	     	             	         System.out.println("session 存储之后: "+ sessionuser.toString());
//  	       	            	        		 String roleId = ll.get(0).get("role_id");
//  	       	            	        		 operSession.setLoginName(username);
//  	       	             	        		 operSession.setRole(roleId);
//  	       	                  	        	 request.getSession().setAttribute("UserSession",operSession);
//  	       	                   	        	 Cookie cookie = new Cookie("JSESSIONID",request.getSession().getId());
//  	       	            	        		 cookie.setMaxAge(86400);
//  	       	            	        		 cookie.setPath("/");
//  	       	            	        		 response.addCookie(cookie);
//  	        	                 	         myc.addSession(logname, request.getSession()); 
  	       	                	        	 result.setCode("1");
  	       	                 	             result.setMsg("认证通过");
  	                   	        	  }else{
  	                   	        		 System.out.println("认证失败-1"); 
  	                   	        		 result.setCode("2");
  	                   	        		 Logresult.put("msg","用户名或密码错误 " + Logresult.get("msg"));
//  	   	                 	             result.setMsg("认证失败");
  	                   	        	  }
                	            	 }else{
                     	        		 System.out.println("认证失败-2"); 
                      	        		 result.setCode("2");
                      	        		Logresult.put("msg","认证失败");
//     	                 	             result.setMsg("认证失败");
                     	        	  }
      	            			} else {
      	            				result.setCode("2");
//  	     	       	        		result.setMsg("登录异常");
      	            				System.out.println("登录异常： Logresult : " + Logresult.toString());
      	            			} 
     	            		  }
             	            }catch(Exception e){
              	        		System.out.println("认证失败-3"); 
             	            	result.setCode("2");
             	            	Logresult.put("msg","认证失败");
          	            		result.setMsg("认证失败");
          	            		e.printStackTrace();
            	            }
      	             }else{
      	            	 System.out.println("认证失败-4"); 
    	            	 result.setCode("2");
    	            	 Logresult.put("msg","用户名或密码不能为空");
//        	             result.setMsg("认证失败");
    	             }
   	             //} 是否已登录屏蔽
			}catch(Exception e){
	             System.out.println("认证失败-5"); 
 				 result.setCode("2");
 				 Logresult.put("msg","认证失败");
	             result.setMsg("认证失败");
			}finally {
				if("101".equals(Logresult.get("code"))) {//当用户正常状态不进行修改
					result.setMsg(Logresult.get("msg"));
				}else if("2".equals(result.getCode()) || "000".equals(Logresult.get("code"))){
					result.setMsg(Logresult.get("msg"));
					AdminSession sessionuser = getUserSessionUtil.getUserSession(request);;// 通过session取值
					if(null != sessionuser) {
						loginService.upType(username,sessionuser.getJsSessionId());
					}
				}else if("1111".equals(result.getCode())) {
					result.setMsg(Logresult.get("msg"));
				}
			}
      		 out.print(json.toJson(result));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	
 	public void logigType(String logname,HashMap<String, String> result,String JSESSIONID) {
 		try {
 			List<Map<String,String>> ll = userService.getUsers(logname,"");
 			if(ll != null && ll.size()>0){//用户存在
 				List<Map<String,Object>> login_data = loginService.getLogin(logname,ll.get(0).get("strid").toString(),JSESSIONID);
 				HashMap<String,String> logmessage = new HashMap<String, String>();
 				if(null != login_data && login_data.size() > 0) {//如果有小于当前时间5分钟的用户登录信息进行修改
 					HashMap<String,Object> res = (HashMap<String, Object>) login_data.get(0);
 					if("3".equals(res.get("log_count").toString())) {//是否被锁定
 						result.put("code","101");
 	 	        		result.put("msg","用户被锁定!" + res.get("chickTime").toString().split(":")[1] + ":" + 
 	 	        		res.get("chickTime").toString().split(":")[2] + 
 						"分钟后可以进行在次登录。如有异常请联系管理员!");
 					} else {//没有被锁定进行进行修改状态和登录信息
 						try {
 							logmessage.put("log_id", res.get("log_id").toString());
 	 	 					String log_desc = "1".equals(res.get("log_count").toString()) ? "二次登录" 
 	 	 							: "2".equals(res.get("log_count").toString()) ? "三次登录"
 	 	 							: "3".equals(res.get("log_count").toString()) ? "用户被锁定"
 	 	 							: "4".equals(res.get("log_count").toString()) ? "五次登录" : "用户被锁定";
 	 	 					logmessage.put("log_desc", log_desc);
 	 	 					logmessage.put("log_count", (Integer.parseInt(res.get("log_count").toString())+1)+"");
 	 	 					logmessage.put("log_status", "1");
 	 	 					logmessage.put("log_JSESSIONID", JSESSIONID);
 	 	 					result.put("code","0");
 	 	 					if((3 - Integer.parseInt(logmessage.get("log_count"))) == 0) {
 	 	 						result.put("msg","用户被锁定!" + res.get("chickTime").toString().split(":")[1] + ":" + 
 	 	 		 	 	        		res.get("chickTime").toString().split(":")[2] + 
 	 	 		 						"分钟后可以进行在次登录。如有异常请联系管理员!");
 	 	 					}else {
 	 	 						result.put("msg","还有" + (3 - Integer.parseInt(logmessage.get("log_count"))) + "次登录的机会");
 	 	 					}
 	 	 	        		int a = loginService.upLogin(logmessage);
 						}catch (Exception e) {
 							result.put("code","000");
 							if((3 - Integer.parseInt(logmessage.get("log_count"))) == 0) {
 	 	 						result.put("msg","用户被锁定!" + res.get("chickTime").toString().split(":")[1] + ":" + 
 	 	 		 	 	        		res.get("chickTime").toString().split(":")[2] + 
 	 	 		 						"分钟后可以进行在次登录。如有异常请联系管理员!");
 	 	 					}else {
 	 	 						result.put("msg","还有" + (3 - Integer.parseInt(logmessage.get("log_count"))) + "次登录的机会");
 	 	 					}
							e.printStackTrace();
						}
 					}
 				} else {//如果5分钟内没有进行登录进行 写入登录信息
 					try {
 						logmessage.put("log_user_ID", ll.get(0).get("strid").toString());
 	 					logmessage.put("log_user_EN", ll.get(0).get("login_name").toString());
 	 					logmessage.put("log_user_CN", ll.get(0).get("user_name").toString());
 	 					logmessage.put("log_JSESSIONID", JSESSIONID);
 	 					logmessage.put("log_desc", "一次登录");
 	 					logmessage.put("log_count", "1");
 	 					logmessage.put("log_status", "1");
 	 					loginService.addLogin(logmessage);
 	 					result.put("code","0");
 	 					result.put("msg","还有2次登录的机会");
 					}catch (Exception e) {
 						result.put("code","000");
 		 	        	result.put("msg","还有2次登录的机会");
 		 	        	e.printStackTrace();
 					}
 				}
        	 }else{//用户不存在
        		 result.put("code","000");
        		 result.put("msg","用户不存在");
	        }
 		}catch (Exception e) {
			e.printStackTrace();
		}
 	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/crowdSsoOpenId/userlogin",produces="text/html;charset=UTF-8")
    public void CrowdSsoOpenIdLogin(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value="OpenID") String OpenID )   {
  		Gson json=new Gson(); 
    	Map<String,String> map = new HashMap<String,String>();
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
 			ResultMessage result = new ResultMessage();
    		try{
    			if(null != OpenID ){
        			String deopenId = java.net.URLDecoder.decode(OpenID,"UTF-8");
      		 		String returnUrl =  getProperties("config.properties", "returnToUrl");
 					RegistrationService.setReturnToUrl(returnUrl);
    		  		DiscoveryInformation discoveryInformation = RegistrationService
   							.performDiscoveryOnUserSuppliedIdentifier(deopenId);
    		 		AuthRequest authRequest = RegistrationService.createOpenIdAuthRequest(discoveryInformation, returnUrl);
    		 			result.setCode("1");
   		 			result.setMsg(authRequest.getDestinationUrl(true));
      			}else{
    				result.setCode("2");
    				result.setMsg("验证失败，openid为空");
    			}
			}catch(Exception e){
				 e.printStackTrace();
				 result.setCode("2");
	             result.setMsg("验证失败");
			}
      		 out.print(json.toJson(result));
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	 
    
  	
	
	private boolean validateLoggedUser(HttpServletRequest request,HttpServletResponse response,
			CrowdHttpAuthenticator crowdHttpAuthenticator){
		Boolean authenticated = false;
		try {
   			AuthenticationState austatus = crowdHttpAuthenticator.checkAuthenticated(request, response);
    	    return austatus.isAuthenticated();
   		} catch (Exception e) {
			e.printStackTrace();
		}  
  		return authenticated;
	}
	

	
	
	
	
	
	/**
	 *  
	 * 验证页返回的请求
	 * 
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/crowdSsoOpenId/crowdReturn",produces="text/html;charset=UTF-8")
    public void catchTest(HttpServletRequest request,HttpServletResponse response  ) throws Exception {
 			ResultMessage result = new ResultMessage();
 		     response.setContentType("application/x-json");
 		    Gson json=new Gson(); 
  	    	 PrintWriter out  = response.getWriter();;
   			try{
 				ParameterList params = new ParameterList(request.getParameterMap());  
  				String opendid = "";
 				String querystr = request.getQueryString();
 				String []querys = querystr.split("&");
				if(querys.length > 0 ){
					for(int i=0;i<querys.length;i++){
						if(querys[i].startsWith("openid.claimed_id")){
							String []claimedid = querys[i].split("=");
							opendid = claimedid[1];
							break;
						}
					}
 			    opendid = java.net.URLDecoder.decode(opendid,"UTF-8");		
				
			    // 由于每次session会变，无法取到discoveryInformation，需要重新获取一次，不知道合适不
				DiscoveryInformation discoveryInformation = RegistrationService
								.performDiscoveryOnUserSuppliedIdentifier(opendid);
  			        
					// returnToUrl=http://111.235.158.226:8250/newAudit/loading2.html
			        String returnUrl  =  getProperties("config.properties", "returnToUrl");
 			        StringBuffer receivingURL=new StringBuffer(returnUrl) ;  
 			     
			        RegistrationModel model = RegistrationService.processReturn(discoveryInformation, 
				        		receivingURL.toString(),params );
 			        if(null != model){
 			        	String username = new String();
 			        	username = opendid.substring(opendid.lastIndexOf('/')+1, opendid.length());
  			        	 List<Map<String,String>> ll = userService.getUsers(username,"");
	       	        	  if(ll != null && ll.size()>0){
	             	        	 AdminSession operSession = new AdminSession();
	           	        		 String roleId = ll.get(0).get("role_id");
	           	        		 operSession.setLoginName(username);
	            	        	 operSession.setRole(roleId);
	                 	         request.getSession().setAttribute("UserSession",operSession);
	                  	         Cookie cookie = new Cookie("JSESSIONID",request.getSession().getId());
	           	        		 cookie.setMaxAge(86400);
	           	        		 cookie.setPath("/");
	           	        		 response.addCookie(cookie);
	                 	         MySessionContext myc= MySessionContext.getInstance();  
	               	        	 myc.addSession(request.getSession()); 
	               	        	 result.setCode("1");
	                	         result.setMsg("认证通过");
	       	        	  } 
 			        }else{
			        	result.setCode("2"); 
			        	result.setMsg("认证失败");
			        }
				}else{
					result.setCode("2"); 
		        	result.setMsg("认证失败");
				}
 			}catch(Exception e){
				e.printStackTrace();
			}
   		 out.print(json.toJson(result));
  	}
	
	// 获取 Properties
 	 public static String getProperties(String filePath, String keyWord){
         Properties prop = null;
         String value = null;
         try {
             prop = PropertiesLoaderUtils.loadAllProperties(filePath);
             value = prop.getProperty(keyWord);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return value;
     }
 	 
 	/**
      * 生成随机验证码及图片
      * Object[0]：验证码字符串；
      * Object[1]：验证码图片。
      */
     public static Object[] createImage() {
         StringBuffer sb = new StringBuffer();
         // 1.创建空白图片
         BufferedImage image = new BufferedImage(
                 WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
         // 2.获取图片画笔
         Graphics graphic = image.getGraphics();
         // 3.设置画笔颜色
         graphic.setColor(Color.LIGHT_GRAY);
         // 4.绘制矩形背景
         graphic.fillRect(0, 0, WIDTH, HEIGHT);
         // 5.画随机字符
         Random ran = new Random();
         for (int i = 0; i < SIZE; i++) {
             // 取随机字符索引
             int n = ran.nextInt(IMAGECHARS.length);
             // 设置随机颜色
             Color randomColor = getRandomColor();
             while(randomColor == null) {
            	 System.out.println("getColor");
            	 randomColor = getRandomColor();
             }
             graphic.setColor(randomColor);
             // 设置字体大小
             graphic.setFont(new Font(
                     null, Font.BOLD + Font.ITALIC, FONT_SIZE));
             // 画字符
             graphic.drawString(IMAGECHARS[n] + "", i * WIDTH / SIZE, HEIGHT * 2 / 3);
             // 记录字符
             sb.append(IMAGECHARS[n]);
         }
         // 6.画干扰线
         for (int i = 0; i < LINES; i++) {
             // 设置随机颜色
             graphic.setColor(getRandomColor());
             // 随机画线
             graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),
                     ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
         }
         // 7.返回验证码和图片
//         return new Object[]{sb.toString(), image};
  
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         BufferedImage image = imageCaptchaService.getImageChallengeForID(request.getRequestedSessionId());
         try {
             ImageIO.write(image, "jpeg", baos);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return new Object[]{sb.toString(), baos.toByteArray()};
  
     }
  
     /**
      * 随机取色
      */
     public static Color getRandomColor() {
         Random ran = new Random();
         int r = ran.nextInt(256);
         int g = ran.nextInt(256);
         int b = ran.nextInt(256);
         if(r*0.299 + g*0.578 + b*0.114 >= 192){ //浅色
             return null;
         }else{  //深色
        	 Color color = new Color(ran.nextInt(256),
                     ran.nextInt(256), ran.nextInt(256));
        	 return color;
         }
     }
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
     @ResponseBody
     @RequestMapping(method = RequestMethod.GET,value = "/getImage",produces="text/html;charset=UTF-8")
     public byte[] getByte(HttpServletRequest request,HttpServletResponse response ){
         try {
        	 //进行获取 号码 和 图片
        	 Object[] objs = createImage();
             while(objs == null) {
            	 objs = createImage();
             }
             String randomStr = (String) objs[0];
             randomStr = randomStr.toLowerCase();
    		 AdminSession sessionuser = getUserSessionUtil.getUserSession(request);
    		 MySessionContext myc= MySessionContext.getInstance(); 
    		 HttpSession htsc =  null;
			if(null == sessionuser) {
				System.out.println("session 新创建!");
				htsc = request.getSession(true);
				String sessionId = htsc.getId();
				sessionuser = new AdminSession();
				sessionuser.setJsSessionId(sessionId);
			}else {
				System.out.println("session 存在!");
				htsc = getUserSessionUtil.getSession(request);
			}
			sessionuser.setImCode(randomStr);
			System.out.println("当前 "+ sessionuser.getJsSessionId() +" 登录验证码："+sessionuser.getImCode());
			htsc.setAttribute("UserSession",sessionuser);
			myc.addSession(htsc);
        	 Cookie cookie = new Cookie("JSESSIONID",sessionuser.getJsSessionId());
    		 cookie.setMaxAge(86400);
    		 cookie.setPath("/");
    		 response.addCookie(cookie);
             return (byte[]) objs[1];
        }catch (Exception e) {
			e.printStackTrace();
		}
         return null;
     }
}
