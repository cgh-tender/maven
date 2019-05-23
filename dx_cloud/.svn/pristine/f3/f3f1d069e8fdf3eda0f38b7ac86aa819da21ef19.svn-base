package com.dw.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.dw.util.HttpRequest;
import com.google.gson.Gson;

  
 
public class text {

 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)  {
		/*String result = "{synStatus:'%s',synMsg:'%s'}";
 		System.out.println(String.format(result, "失败","1"));*/
 		
 		/*String sessionResult =HttpRequest.sendGet("http://127.0.0.1:8888/dx_cloud/userlogin/checklogin", "userid=1505130419462&menulevel=1");
  		System.out.println(sessionResult);
		
		try {
			String url = java.net.URLDecoder.decode("http%3A%2F%2Ftest.ctyun.cn%3A8095%2Fopenidserver%2Fusers%2Flanyn","UTF-8");
			
			System.out.println(url);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	/*	String properties_1 = getProperties_1("config.properties", "login");
	    System.out.println("login = " + properties_1);*/
		 
		// getProperties_1("com/dw/web/config/config.properties");
		 
		 String str = "http://test.ctyun.cn:8095/openidserver/users/lanyn";
		 
		str =  str.substring(str.lastIndexOf('/')+1, str.length());
		 
		System.out.println(str);
		
		System.out.println("*********************************************");
		 
		
    }  
  
	      public static String getProperties_1(String filePath, String keyWord){
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
	      
	           public static void getProperties_1(String filePath){
	    	           Properties prop = null;
	    	           try {
	    	               // 通过Spring中的PropertiesLoaderUtils工具类进行获取
	    	               prop = PropertiesLoaderUtils.loadAllProperties(filePath);
	    	               printAllProperty(prop);
	    	           } catch (IOException e) {
	    	               e.printStackTrace();
	    	           }
	    	       } 
	      
	                 private static void printAllProperty(Properties props)  
	                 {  
	                     @SuppressWarnings("rawtypes")  
	                     Enumeration en = props.propertyNames();  
	                     while (en.hasMoreElements())  
	                     {  
	                         String key = (String) en.nextElement();  
	                         String value = props.getProperty(key);  
	                         System.out.println(key + " : " + value);  
	                     }  
	                 }
	      
	      
}



