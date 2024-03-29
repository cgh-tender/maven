package com.dw.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dw.model.ExportMessage;
import com.dw.servce.IExportService;
import com.dw.servce.IReportService;
import com.dw.util.exportReportTemplete;

@RestController
@RequestMapping("/export")
public class ExportConstroller {
	private final static Logger logger = LoggerFactory.getLogger(ExportConstroller.class);
	@Autowired
	IExportService exportService;
	@Autowired
	IReportService reportS;
	
	@RequestMapping(value="/findHead",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ExportMessage findHead(HttpServletRequest request, HttpServletResponse response,@RequestBody ExportMessage message){
		try {
			exportService.getFieldHead(message);
		} catch (Exception e) {
			logger.error("这个是测试error" + e.getMessage());
		}
		return message;
	}
	
	@RequestMapping(value="/findData",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ExportMessage findData(HttpServletRequest request, HttpServletResponse response,@RequestBody ExportMessage message){
		try {
			exportService.getData(message);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return message;
	}
	@RequestMapping(value="/findExport",method = {RequestMethod.GET})
	@ResponseBody
	public void findExport(HttpServletRequest request, HttpServletResponse response,ExportMessage message){
		try {
			Object f = message.getReq().get("fileName");
			exportService.findExport(message);
			if (f!=null) {
				String fileName = f.toString() == "" ? message.getMethod() : f.toString();
				List<Map<String, Object>> header = message.getHeader();
				List<Map<String, String>> repHead = new ArrayList<Map<String,String>>();
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < header.size(); i++) {
					Map<String, Object> map2 = header.get(i);
					String key = map2.get("field").toString();
					String val = map2.get("title").toString();
					map.put("field_name_EN", key);
					map.put("field_name_CN", val);
					repHead.add(map);
					map = new HashMap<String, String>();
				}
				List<Map<String, Object>> data = message.getData();
				exportReportTemplete.returnXSSFWorkbookO(response, fileName, repHead, data);
				System.out.println("导出正常");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try {
			ApplicationContext APPLICATION_CONTEXT3=new ClassPathXmlApplicationContext(new String[]{"classpath:/applicationContext.xml","classpath:/applicationContext_db.xml"});
			IExportService exportService = (IExportService)APPLICATION_CONTEXT3.getBean("exportService");
			ExportMessage message = new ExportMessage();
			//获取头部信息
			message.setMethod("businessNUllRaito");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("dt", "20190325-20190325");
			map.put("prov_id", "pvct");
			map.put("month_interval", "1");
//			map.put("city_id", "allct");
//			map.put("time", "00");
			message.setReq(map);
//			exportService.getCount(message);
			exportService.getFieldHead(message);
//			map.put("start", "0");
//			map.put("limit", "20");
//			message.setReq(map);
//			message.setFieldVal(fileVal);
//			message.setFieldNameEN(fileNameString);
//			exportService.findExport(message);
//			exportService.getData(message);
			System.out.println(message.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
