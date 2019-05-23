package com.dw.web;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dw.model.ResultMessage;
import com.dw.model.ResultMessage2;
import com.dw.model.ResultMessage3;
import com.dw.model.Role;
import com.dw.model.RootMenu;
import com.dw.model.SystemInfo;
import com.dw.servce.IUserService;
import com.dw.servce.SystemInfoService;
import com.google.gson.Gson;


@Controller  
public class SystemInfoConstroller {

	@Autowired
	SystemInfoService systemInfoService ;
	
	@SuppressWarnings({ "unused", "static-access" })
	/**
	 *  系统信息添加
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/systemmanagement/addsystem", produces = "text/html;charset=UTF-8")
	public void  addMenu(HttpServletRequest request,HttpServletResponse response, @RequestParam("system") String system) {
 		
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
 				  JSONObject obj = new JSONObject().fromObject(system);
 				  SystemInfo systemInfo=(SystemInfo)JSONObject.toBean(obj, SystemInfo.class);
 				  
 				  SystemInfo resultsystemInfo = systemInfoService.addSystemInfo(systemInfo);
 				  if(resultsystemInfo!=null){
 					 result.setCode("1");
 					 result.setMsg("添加成功");
 					 List<SystemInfo> resultlist = new ArrayList();
 					 resultlist.add(resultsystemInfo);
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
	 * 系统信息列表
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/systemmanagement/systemlist",produces="text/html;charset=UTF-8")
    public void getRoleList(HttpServletRequest request,HttpServletResponse response)   {
    	 Gson json=new Gson(); 
    	 boolean jsonP = false;
    	 ResultMessage3 result = new ResultMessage3();
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
 			  SystemInfo systemInfo = systemInfoService.getSystemInfo();
  			 
 			  if(systemInfo.getIndex_title()==null){
 				 systemInfo.setIndex_title("");
 			  }
 			  if(systemInfo.getLogin_copyrigh() ==null){
 				 systemInfo.setLogin_copyrigh("");
 			  }
 			  if(systemInfo.getLogin_desc() == null){
 				 systemInfo.setLogin_desc("");
 			  }
 			  if(systemInfo.getLogin_name() == null){
 				 systemInfo.setLogin_name("");
 			  }
 			  if(systemInfo.getReserve_message01()== null){
 				 systemInfo.setReserve_message01("");
 			  }
 			  if(systemInfo.getReserve_message02() == null){
 				 systemInfo.setReserve_message02("");
 			  }
 			  if(systemInfo.getReserve_message03() == null){
 				 systemInfo.setReserve_message03("");
 			  }
 			  if(systemInfo.getReserve_message04() == null ){
 				 systemInfo.setReserve_message04("");
 			  }
 			 if(systemInfo.getReserve_message05() == null ){
 				 systemInfo.setReserve_message05("");
 			  }
			 result.setCode("1");
			 result.setMsg("查询成功");
			 result.setData(systemInfo);
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
	 * 系统信息修改
	 * @param request
	 * @param response
	 * @param orgEntity
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/systemmanagement/updatesystem", produces = "text/html;charset=UTF-8")
	public void  UpdateRole(HttpServletRequest request,HttpServletResponse response, @RequestParam("syslist") String syslist) {
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
 				  JSONObject obj = new JSONObject().fromObject(syslist);
 				  SystemInfo systemInfo=(SystemInfo)JSONObject.toBean(obj, SystemInfo.class);
    			  Boolean flag =  systemInfoService.updateSystemInfo(systemInfo);
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
	
}
