package com.dw.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dw.util.HttpRequest;
import com.google.gson.Gson;

/**
 * 菜单权限管理
 * @author htl
 *
 */
@Controller 
public class MenuConstroller {
	/**
	 * 获取当前菜单权限
	 * @param request
	 * @param response
	 * @param menuId
	 * @param roleId
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/menpower/menquery",produces="text/html;charset=UTF-8")
    public void getmenQuery(HttpServletRequest request,HttpServletResponse response,@RequestParam("menuid") String menuId,@RequestParam("roleid") String roleId)   {
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
			String s=HttpRequest.sendGet("http://10.37.31.24:8899/BDOP/auditRights/menuRequest", "roleId="+roleId+"&Pid="+menuId);
	        System.out.println(s);
    		 out.print(s);
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 获取当前菜单按钮列表
	 * @param request
	 * @param response
	 * @param menuId
	 * @param roleId
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/menpower/menbuttonquery",produces="text/html;charset=UTF-8")
    public void getmenButtonQuery(HttpServletRequest request,HttpServletResponse response,@RequestParam("menuid") String menuId,@RequestParam("roleid") String roleId)   {
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
			String s=HttpRequest.sendGet("http://10.37.31.24:8899/BDOP/auditRights/buttonRequset", "roleId="+roleId+"&Pid="+menuId);
	        System.out.println(s);
    		 out.print(s);
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 获取当前按钮的二级权限
	 * @param request
	 * @param response
	 * @param menuId
	 * @param roleId
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/menpower/menbuttonqueryprovs",produces="text/html;charset=UTF-8")
    public void getmenButtonQueryProvs(HttpServletRequest request,HttpServletResponse response,@RequestParam("buttonid") String buttonId,@RequestParam("roleid") String roleId)   {
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
			String s=HttpRequest.sendGet("http://10.37.31.24:8899/BDOP/auditRights/secRightsRequest", "roleId="+roleId+"&buttonId="+buttonId);
	        System.out.println(s);
    		 out.print(s);
	    	 if (jsonP) {
	    	     out.write(");");
	    	 }
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 校验当前角色菜单权限
	 * 同一按钮的多个二级权限编码用逗号分割；不同按钮的二级权限编码用分号分割
	 * @param request
	 * @param response
	 * @param menuId
	 * @param roleId
	 */ 
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/menpower/checkmenpower",produces="text/html;charset=UTF-8")
    public void getcheckMenPower(HttpServletRequest request,HttpServletResponse response,@RequestParam("buttonid") String buttonId,@RequestParam("roleid") String roleId,@RequestParam("menuid") String menuid,@RequestParam("secright") String secRight)   { 
			
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
				String s=HttpRequest.sendGet("http://10.37.31.24:8899/BDOP/auditRights/checkRights", "roleId="+roleId+"&menuId="+menuid+"&buttonId="+buttonId+"&secRight="+secRight);
		        System.out.println(s);
				 Gson json=new Gson(); 
				 Map<String,String> map = new HashMap<String,String>();
				 map.put("msg", "权限通过！");
				 map.put("code", "1");
				 out.print(json.toJson(map));
		    	 if (jsonP) {
		    	     out.write(");");
		    	 }
			}  catch (Exception e) {
				e.printStackTrace();
			}
    }
	
	public static void main(String[] args) throws JSONException {
		//String s=HttpRequest.sendGet("http://10.37.31.24:8899/BDOP/auditRights/buttonRequset", "roleId=1&menuId=1003");
		//String s=HttpRequest.sendGet("http://10.37.31.24:8899/BDOP/auditRights/buttonRequset", "roleId=1&Pid=881");
		//String s=HttpRequest.sendGet("http://10.37.31.24:8899/BDOP/auditRights/provinceRequest", "roleId=1&buttonId=217");
		//String s=HttpRequest.sendGet("http://10.37.31.24:8899/BDOP/auditRights/checkRights", "roleId=1&buttonId=313&secRight=2");
		//http://localhost:8086/BDOP/auditRights/checkRights?roleId=1&menuId=226,230&provs=843;831,832,833
		String s=HttpRequest.sendGet("http://127.0.0.1:8888/dx_cloud/userlogin/admin.do", "password=dsdod");
		//String sessionResult =HttpRequest.sendGet("http://127.0.0.1:8080/dx_cloud/usermanagement/userDetails", "userid=1505130419462");
		//String sessionResult =HttpRequest.sendGet("http://127.0.0.1:8080/dx_cloud/rolemanagement/roleUpOrSave", "userid=1505130419462");
        
		
		System.out.println(s);
        //System.out.println(s);
		
/*		
		String  str = "1,2,3,4,5";
		String [] os = str.split(",");
		for(int i=0;i<os.length;i++){
			System.out.println(os[i]);

		}*/
		
		  
	}
}
