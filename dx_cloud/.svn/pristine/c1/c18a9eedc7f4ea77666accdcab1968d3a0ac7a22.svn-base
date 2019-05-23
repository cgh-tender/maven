package com.dw.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dw.model.PushMenu;
import com.dw.model.ResultMessage4;
import com.dw.model.ResultMessage5;
import com.dw.model.ResultMessage6;
import com.dw.model.testTree;
import com.dw.servce.TestCloudService;
import com.google.gson.Gson;

@Controller  
public class TestCloudConstroller {

	@Autowired
	TestCloudService testCloudService ;
	
	/**
	 * ||"getorgantree".equals(mehtodName) 
	 * com.dw.interceptor.MyInterceptor.preHandle
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/testcloud/getorgantree", produces = "text/html;charset=UTF-8")
	public void  getorgantree(HttpServletRequest request,HttpServletResponse response) {
		 Gson json=new Gson();
		 ResultMessage5 result = new ResultMessage5();
		 String unit_id = request.getParameter("unit_id");
//		 String unit_id = "1";
//    	 if (unit_id != null) {
		 response.setHeader("Access-Control-Allow-Origin", "*");
    	     response.setContentType("text/javascript");
//    	 } else {
//    	     response.setContentType("application/x-json");
//    	 }
    	 PrintWriter out = null;
		try {
			
			out = response.getWriter();
			List<testTree> systemInfo = testCloudService.getorgantree(unit_id);
			if(systemInfo.size() == 0) {
				ResultMessage6 result1 = new ResultMessage6();
				result1.setIsLowest(1);
				try {
					List<Map<String, String>> untreated = testCloudService.getuntreated(unit_id);
					List<Map<String, String>> count = testCloudService.getcount(unit_id);
					Map data = count.get(0);
					data.put("untreated", untreated);
					result1.setData(data);
				}  catch (Exception e) {
					result1.setCode("2");
					result1.setMsg("查询失败");
					e.printStackTrace();
				}
				System.out.println(result1);
				String str = json.toJson(result1);
//				str = "("+str+")";
				out.print(str);
				return;
			}
			result.setCode("1");
			result.setMsg("查询成功");
			result.setData(systemInfo);
 		 }  catch (Exception e) {
			result.setCode("2");
			result.setMsg("查询失败");
			e.printStackTrace();
		}
		System.out.println(result);
		String str = json.toJson(result);
//		str = "("+str+")";
		out.print(str);
	}
	/**
	 * ||"getorgantree".equals(mehtodName) 
	 * com.dw.interceptor.MyInterceptor.preHandle
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/testcloud/getcleantoaccount1", produces = "text/html;charset=UTF-8")
	public void  getcleantoaccount1(HttpServletRequest request,HttpServletResponse response) {
		 Gson json=new Gson();
		 ResultMessage4 result = new ResultMessage4();
		 String unit_id = request.getParameter("unit_id");
		 response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setContentType("text/javascript");
    	 PrintWriter out = null;
		try {
			Map<String, String> m = new HashMap<String, String>();
			m.put("811","北京"); m.put("812","天津"); m.put("813","河北"); m.put("814","山西"); m.put("815","内蒙"); m.put("821","辽宁"); m.put("822","吉林"); m.put("823","黑龙江"); m.put("831","上海"); m.put("832","江苏"); m.put("833","浙江"); m.put("834","安徽"); m.put("835","福建"); m.put("836","江西"); m.put("837","山东"); m.put("841","河南"); m.put("842","湖北"); m.put("843","湖南"); m.put("844","广东"); m.put("845","广西"); m.put("846","海南"); m.put("850","重庆"); m.put("851","四川"); m.put("852","贵州"); m.put("853","云南"); m.put("854","西藏"); m.put("861","陕西"); m.put("862","甘肃"); m.put("863","青海"); m.put("864","宁夏"); m.put("865","新疆");
			
			out = response.getWriter();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("averageDelay", "20");
			List list = new ArrayList();
			int a = 0;
			PushMenu push ;
			for (String key : m.keySet()) {
				push = new PushMenu();
				push.setDay("20181125");
				push.setProv_id(key);
				push.setDatatype(a+"");
				push.setDatasubtype((a+1)+"");
				push.setBusiness_types(1+"");
				push.setType(1+"");
				push.setCount3(a*2 + "");
				push.setCount5(a*2 + "");
				push.setCount10(a*2 + "");
				push.setCount20(a*2 + "");
				push.setCount30(a*3 + "");
				push.setCount60(3*a+"");
				push.setCount180(3*a+"");
				push.setCount300(3*a+"");
				push.setCountmax(3*a+"");
				push.setAveragetime(a*2+"");
				list.add(push);
				push = new PushMenu();
				push.setDay("20181125");
				push.setProv_id(key);
				push.setDatatype(a+"");
				push.setDatasubtype((a+1)+"");
				push.setBusiness_types(2+"");
				push.setType(1+"");
				push.setCount3(a*2 + "");
				push.setCount5(a*2 + "");
				push.setCount10(a*2 + "");
				push.setCount20(a*2 + "");
				push.setCount30(a*3 + "");
				push.setCount60(3*a+"");
				push.setCount180(3*a+"");
				push.setCount300(3*a+"");
				push.setCountmax(3*a+"");
				push.setAveragetime(a*2+"");
				list.add(push);
				a++;
			}
			data.put("data",list);
			result.setCode("1");
			result.setMsg("查询成功");
			result.setData(data);
 		 }  catch (Exception e) {
			result.setCode("2");
			result.setMsg("查询失败");
			e.printStackTrace();
		}
		System.out.println(result);
		String str = json.toJson(result);
		out.print(str);
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/testcloud/getcleantoaccount2", produces = "text/html;charset=UTF-8")
	public void  getcleantoaccount2(HttpServletRequest request,HttpServletResponse response) {
		 Gson json=new Gson();
		 ResultMessage4 result = new ResultMessage4();
		 String unit_id = request.getParameter("unit_id");
		 response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setContentType("text/javascript");
    	 PrintWriter out = null;
		try {
			
			out = response.getWriter();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("averageDelay", "20");
			List list = new ArrayList();
			int a = 0;
			PushMenu push ;
			while(a < 20) {
				push = new PushMenu();
				push.setDay("20181125");
				push.setProv_id("811");
				push.setDatatype(a+"");
				push.setDatasubtype((a+1)+"");
				push.setBusiness_types(a < 10 ? 1+"" : 2+"");
				push.setType(a < 10 ? 2+"" : 1+"");
				push.setCount3(a*2 + "");
				push.setCount5(a*2 + "");
				push.setCount10(a*2 + "");
				push.setCount20(a*2 + "");
				push.setCount30(a*3 + "");
				push.setCount60(3*a+"");
				push.setCount180(3*a+"");
				push.setCount300(3*a+"");
				push.setCountmax(3*a+"");
				push.setAveragetime(a*2+"");
				list.add(push);
				a++;
			}
			data.put("data",list);
			result.setCode("1");
			result.setMsg("查询成功");
			result.setData(data);
 		 }  catch (Exception e) {
			result.setCode("2");
			result.setMsg("查询失败");
			e.printStackTrace();
		}
		System.out.println(result);
		String str = json.toJson(result);
		out.print(str);
	}
}
