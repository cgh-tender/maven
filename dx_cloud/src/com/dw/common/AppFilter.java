package com.dw.common;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AppFilter implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest re, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) re ;
		HttpServletResponse response = (HttpServletResponse) res ;
		String uri = request.getRequestURI();
		String endWith = request.getContextPath()+"/" ;
		if ( uri.endsWith(request.getContextPath()+"/") 
				|| uri.endsWith(endWith+"login/login")
				|| uri.endsWith("login.jsp") 
				|| uri.endsWith(".js") 
				|| uri.endsWith(".css") 
				|| uri.endsWith(".jpg") 
				|| uri.endsWith(".png")
				|| uri.endsWith(".gif")
				|| uri.endsWith(".pdf")
				|| uri.endsWith(endWith+"client/kankan")
				|| uri.endsWith(endWith+"content/chakan")
				|| uri.endsWith(endWith+"download/apk")
		) {
			chain.doFilter(request, response);
			return ;
		}
		HttpSession session = request.getSession();
		String isLogin = (String) session.getAttribute("isLogin");
		if ( null ==isLogin) {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf-8");
			out.print("<script>location='"+request.getContextPath()+"/';</script>");
			out.close();
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}

