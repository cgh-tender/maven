package com.dw.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MySessionContext {  
    private static MySessionContext instance;  
    private HashMap<String,HttpSession> sessionMap;  
    private HashMap<String,String> IdName;
    Set userWhite = new HashSet();
    private MySessionContext() {  
        sessionMap = new HashMap<String,HttpSession>();  
        IdName = new HashMap<String, String>();
        userWhite.add("dsdod_123");
        userWhite.add("dsdod_admin");
        userWhite.add("dsdod_main");
    }  
  
    public static MySessionContext getInstance() {  
        if (instance == null) {  
            instance = new MySessionContext();  
        }  
        return instance;  
    }  
    
    public synchronized void addSession(HttpSession session) {  
        if (session != null) {  
            sessionMap.put(session.getId(), session);  
        }  
    } 
    
    /**
     * 用户从验证码 更新session 内容 
     * @param userName
     * @param session
     */
    public synchronized void addSessionIsconn(String userName, HttpSession session) {  
        if (session != null) {  
        	for (String id : IdName.keySet()) {
        		if(!userWhite.contains(IdName.get(id)) && userName.equals(IdName.get(id))) {
					try {
						delSession(id);
					} catch (Exception e) {
						
					}
        		}
        	}
            sessionMap.put(session.getId(), session); 
            IdName.put(session.getId(),userName);
        }  
    } 

    public synchronized void delSession(String sessionId) {  
        if (sessionId != null) {  
			try {
				sessionMap.remove(sessionId); 
                IdName.remove(sessionId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("delSession sessionId: ");
			System.out.println("IdName : "+ IdName.toString());
			System.out.println("sessionMap : "+ sessionMap.toString());
		}
    }  
    synchronized void delSession(HttpSession session) {  
        if (session != null) {  
        	try {
				sessionMap.remove(session.getId()); 
                IdName.remove(session.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
        	System.out.println("delSession session: ");
			System.out.println("IdName : "+ IdName.toString());
			System.out.println("sessionMap : "+ sessionMap.toString());
        }  
    }  
  
    public synchronized HttpSession getSession(String sessionID) {  
        if (sessionID == null) {  
            return null;  
        }  
        return sessionMap.get(sessionID);  
    }  
    
//    public synchronized HttpSession getUserSession(String userName) {  
//        if (userName == null) {  
//            return null;  
//        }  
//        System.out.println("getSession 用户!");
//        System.out.println("当前登录的 sessionId - userName : " + IdName.toString());
//		System.out.println("当前登录的所有用户 : " + sessionMap.toString());
//		for(String id : IdName.keySet()) {
//			if(userName.equals(IdName.get(id))) {
//				return sessionMap.get(id); 
//			}
//		}
//        return null;
//    }  
    
    public synchronized boolean getInvalidated(HttpServletRequest request) {
    	boolean flag = false;
    	HttpSession session = request.getSession();
    	if(request.getSession(false) == null) {
    		flag = true;
    	} else if(null == sessionMap.get(session.getId())){
    		flag = true;
    	} else {
    		flag = false;
    	}
    	return flag;
    }
  
    public synchronized String getIp(HttpServletRequest request) {
    	String ip = null;
    	try {
    		ip = request.getHeader("x-forwarded-for"); 
            System.out.println("x-forwarded-for ip: " + ip);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
                if( ip.indexOf(",")!=-1 ){
                    ip = ip.split(",")[0];
                }
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
                System.out.println("Proxy-Client-IP ip: " + ip);
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
                System.out.println("WL-Proxy-Client-IP ip: " + ip);
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
                System.out.println("HTTP_CLIENT_IP ip: " + ip);
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
                System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("X-Real-IP");  
                System.out.println("X-Real-IP ip: " + ip);
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
                System.out.println("getRemoteAddr ip: " + ip);
            } 
            System.out.println("获取客户端ip: " + ip);
            
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return ip;  
    }
}  