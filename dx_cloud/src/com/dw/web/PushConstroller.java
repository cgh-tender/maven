package com.dw.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dw.model.PushMenu;
import com.dw.model.ResultMessage2;
import com.dw.servce.PushService;
import com.google.gson.Gson;

import net.sf.json.JSONArray;

@Controller
public class PushConstroller {

	@Autowired
	PushService pushService;
	public static String receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
    
	    // 读取请求内容
	    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//		BufferedReader br = new BufferedReader(new InputStreamReader(request.getParameter("audit")));
	    String line = null;
	    StringBuilder sb = new StringBuilder();
	    while((line = br.readLine())!=null){
	        sb.append(line);
	    }
	
	    // 将资料解码
	    String reqBody = sb.toString();
	    return URLDecoder.decode(reqBody, HTTP.UTF_8);
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/openPost/delReal", produces = "text/html;charset=UTF-8")
	public void delReal(HttpServletRequest request, HttpServletResponse response) {//,@RequestParam("data") String jsonStr
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setContentType("text/javascript");
	    String jsonStr = null;
		try {
			jsonStr = receivePost(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}  
	    Gson json = new Gson();
		PrintWriter out = null;
		ResultMessage2 result = new ResultMessage2();
		PushMenu menu = new PushMenu();
		try {
			out = response.getWriter();
			menu.setDt(getdt());

			JSONArray jsonArray = JSONArray.fromObject(jsonStr);
			@SuppressWarnings({ "unchecked", "static-access" })
			List<PushMenu> data = jsonArray.toList(jsonArray,PushMenu.class);
			menu.setData(data);
			int code = pushService.delReal(menu);
			if (code == 1) {
				result.setCode("1");
				result.setMsg("消息成功");
			} else {
				result.setCode("0");
				result.setMsg("消息失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("消息失败 :" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(result);
		String str = json.toJson(result);
		out.print(str);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/openPost/NondelReal", produces = "text/html;charset=UTF-8")
	public void NondelReal(HttpServletRequest request, HttpServletResponse response) {//,
//		@RequestParam("data") String jsonStr
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setContentType("text/javascript");
	    String jsonStr = null;
		try {
			jsonStr = receivePost(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}  
		Gson json = new Gson();
		PrintWriter out = null;
		ResultMessage2 result = new ResultMessage2();
		PushMenu menu = new PushMenu();
		try {
			out = response.getWriter();
			menu.setDt(getdt());

			JSONArray jsonArray = JSONArray.fromObject(jsonStr);
			@SuppressWarnings({ "unchecked", "static-access" })
			List<PushMenu> data = jsonArray.toList(jsonArray,PushMenu.class);
			menu.setData(data);
			int code = pushService.NondelReal(menu);
			if (code == 1) {
				result.setCode("1");
				result.setMsg("消息成功");
			} else {
				result.setCode("0");
				result.setMsg("消息失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("消息失败 :" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(result);
		String str = json.toJson(result);
		out.print(str);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/openPost/fail", produces = "text/html;charset=UTF-8")
	public void fail(HttpServletRequest request, HttpServletResponse response) {//, 
//		@RequestParam("data") String jsonStr
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setContentType("text/javascript");
	    String jsonStr = null;
		try {
			jsonStr = receivePost(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}   
		Gson json = new Gson();
		PrintWriter out = null;
		ResultMessage2 result = new ResultMessage2();
		PushMenu menu = new PushMenu();
		try {
			out = response.getWriter();
			menu.setDt(getdt());

			JSONArray jsonArray = JSONArray.fromObject(jsonStr);
			@SuppressWarnings({ "unchecked", "static-access" })
			List<PushMenu> data = jsonArray.toList(jsonArray, PushMenu.class);
			menu.setData(data);
			int code = pushService.fail(menu);
			if (code == 1) {
				result.setCode("1");
				result.setMsg("消息成功");
			} else {
				result.setCode("0");
				result.setMsg("消息失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("消息失败 :" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(result);
		String str = json.toJson(result);
		out.print(str);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/openPost/availableProc", produces = "text/html;charset=UTF-8")
	public void availableProc(HttpServletRequest request, HttpServletResponse response) {//,
//		@RequestParam("data") String jsonStr
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setContentType("text/javascript");
	    String jsonStr = null;
		try {
			jsonStr = receivePost(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}   
		Gson json = new Gson();
		PrintWriter out = null;
		ResultMessage2 result = new ResultMessage2();
		PushMenu menu = new PushMenu();
		try {
			out = response.getWriter();
			menu.setDt(getdt());

			JSONArray jsonArray = JSONArray.fromObject(jsonStr);
			@SuppressWarnings({ "unchecked", "static-access" })
			List<PushMenu> data = jsonArray.toList(jsonArray,PushMenu.class);
			menu.setData(data);
			int code = pushService.availableProc(menu);
			if (code == 1) {
				result.setCode("1");
				result.setMsg("消息成功");
			} else {
				result.setCode("0");
				result.setMsg("消息失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("消息失败 :" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(result);
		String str = json.toJson(result);
		out.print(str);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/openPost/failProc", produces = "text/html;charset=UTF-8")
	public void failProc(HttpServletRequest request, HttpServletResponse response) {//,
//		@RequestParam("data") String jsonStr
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setContentType("text/javascript");
	    String jsonStr = null;
		try {
			jsonStr = receivePost(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}   
		Gson json = new Gson();
		PrintWriter out = null;
		ResultMessage2 result = new ResultMessage2();
		PushMenu menu = new PushMenu();
		try {
			out = response.getWriter();
			menu.setDt(getdt());

			JSONArray jsonArray = JSONArray.fromObject(jsonStr);
			@SuppressWarnings({ "unchecked", "static-access" })
			List<PushMenu> data = jsonArray.toList(jsonArray,PushMenu.class);
			menu.setData(data);
			int code = pushService.failProc(menu);
			if (code == 1) {
				result.setCode("1");
				result.setMsg("消息成功");
			} else {
				result.setCode("0");
				result.setMsg("消息失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("消息失败 :" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(result);
		String str = json.toJson(result);
		out.print(str);
	}

	/**
	 * return Date fromt yyyyMMddHHmmss
	 */
	public static String getdt() {
		String str = "";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		str = sdf.format(dt);
		return str;
	}
}
