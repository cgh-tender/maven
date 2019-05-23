package com.dw.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dw.model.ResultMessage;
import com.google.gson.Gson;

public class MyExceptionHandler implements HandlerExceptionResolver {

	/**
	 * 
	 * 全局异常捕获
	 * 
	 */
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e) {
		ResultMessage resultMessge = new ResultMessage();
		e.printStackTrace();
		 Gson json=new Gson(); 
 		 PrintWriter out;
 		 try {
			out = response.getWriter();
			resultMessge.setCode("2");
	 		resultMessge.setMsg("未知错误");
	 		out.print(json.toJson(resultMessge));
 		} catch (Exception e1) {
 			e1.printStackTrace();
		}
 		return null;
	}

}
