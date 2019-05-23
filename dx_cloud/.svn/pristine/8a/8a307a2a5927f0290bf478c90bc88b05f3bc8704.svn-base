package com.dw.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dw.common.AdminSession;
import com.dw.interceptor.MySessionContext;

public class getUserSessionUtil {
	
	
	public static AdminSession  getUserSession(HttpServletRequest request){
 		AdminSession sessionuser = null;
		 Enumeration<String> e = request.getHeaders("Cookie");
         String jsessionid="";
        String[] logns;
         try {
        	 while(e.hasMoreElements()){
             	String logname2 = (String) e.nextElement();
             	if (!"".equals(logname2)) {
     				logns = logname2.split(";");
      				for (int i = 0; i < logns.length; i++) {
     					String logname1=logns[i].substring(0,logns[i].indexOf("=")); 
      					if ("JSESSIONID".equals(logname1.trim())) {
      						jsessionid=logns[i].substring(logns[i].indexOf("=")+1,logns[i].length());
     						 MySessionContext myc= MySessionContext.getInstance();  
     	    	        	 HttpSession sess = myc.getSession(jsessionid);  
      	    	        	 sessionuser = ((AdminSession)sess.getAttribute("UserSession"));
     					}
     				}
     			}
             }
             return sessionuser;
         }catch (Exception e1) {
			System.out.println("Sesssion 库中没有 : "+jsessionid + " 的用户");
		}
         return null;
 	}
	
	
	public static HttpSession  getSession(HttpServletRequest request){
 		AdminSession sessionuser = null;
		 Enumeration<String> e = request.getHeaders("Cookie");
         String jsessionid="";
        String[] logns;
         try {
        	 while(e.hasMoreElements()){
             	String logname2 = (String) e.nextElement();
             	if (!"".equals(logname2)) {
     				logns = logname2.split(";");
      				for (int i = 0; i < logns.length; i++) {
     					String logname1=logns[i].substring(0,logns[i].indexOf("=")); 
      					if ("JSESSIONID".equals(logname1.trim())) {
      						jsessionid=logns[i].substring(logns[i].indexOf("=")+1,logns[i].length());
     						 MySessionContext myc= MySessionContext.getInstance();  
     	    	        	 HttpSession sess = myc.getSession(jsessionid);  
     	    	        	 return sess;
      					}
     				}
     			}
             }
		} catch (Exception e2) {
			System.out.println("Sesssion 库中没有 : "+jsessionid + " 的用户");
		}
        return null;
 	}
	
	
 }


