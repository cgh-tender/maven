package com.dw.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

public class SpringUtil {
private static Map<String,Object> cacheSpring = Collections.synchronizedMap(new HashMap<String, Object>());
	
	public static ApplicationContext ctx;

	public static ApplicationContext getCtx() {
		return ctx;
	}

	public static void setCtx(ApplicationContext ctx) {
		SpringUtil.ctx = ctx;
	}

	public static Object getBean(String springName) {
		Object obj = cacheSpring.get(springName);
		if ( obj == null ) {
			obj = ctx.getBean(springName);
			cacheSpring.put(springName, obj) ;
		} 
		return obj ;
	}
}
