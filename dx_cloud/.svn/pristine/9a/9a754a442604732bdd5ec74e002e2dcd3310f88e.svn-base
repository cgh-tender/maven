package com.dw.advice;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.dw.common.StringUtil;
import com.dw.model.Log;
import com.dw.servce.ILogService;


/**
 * Logger
 */
public class Logger {
	@Resource
	private ILogService logService ;
	 private static org.apache.commons.logging.Log logg = LogFactory.getLog(Logger.class);
	/**
	 * 记录
	 */
	public Object record(ProceedingJoinPoint pjp){
		Log log = new Log();
		try {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();   
			ServletContext ac = webApplicationContext.getServletContext(); 
			//ActionContext ac = ActionContext.getContext();
			//设置操作人
			if(ac != null){
				HttpSession tempsession = (HttpSession) ac.getAttribute("cu_session");
				if(tempsession != null){
					//Customer user = (Customer) tempsession.getAttribute("customer");
//					if(user != null){
//						log.setOperator(user.getApiKey());
//					}
				}
			}
			//操作名称
			String mname = pjp.getSignature().getName();
			log.setOperName(mname);
			//操作参数 
			Object[] params = pjp.getArgs();
			log.setOperParams(StringUtil.arr2Str(params));
			//调用目标对象的方法
			Object ret = pjp.proceed();
			//设置操作结果
//			log.setOperResult("success");
			log.setOperResult("failure");
			//设置结果消息
//			if(ret != null){
//				DXObject dx=(DXObject) ret;
//				if("200".equals(dx.getCode())){
//				log.setOperResult("success");
//				}
//				log.setResultMsg(ret.toString());
//			}
			return ret ;
		} catch (Throwable e) {
			log.setOperResult("failure");
			log.setResultMsg(e.getMessage());

		}
		finally{
			logg.warn(log);
			logService.saveEntity(log);
		}
		return null ;
	}
}
