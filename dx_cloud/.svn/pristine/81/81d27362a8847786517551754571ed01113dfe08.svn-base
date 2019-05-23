package com.dw.util;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * 日志工具类
 */
public class LogUtil {
	
	public static void main(String[] args) {
		System.out.println(generateLogTableName(1));
	}
	/**
	 * 生成日志表名称:logs_2013_9 
	 * offset:偏移量
	 */
	public static String generateLogTableName(int offset){
		Calendar c = Calendar.getInstance();
		// 2013
		int year = c.get(Calendar.YEAR);
		// 0 -11
		int month = c.get(Calendar.MONTH) + 1 + offset;
		
		if(month > 12){
			year ++ ;
			month = month - 12 ;
		}
		if(month < 1){
			year -- ;
			month = month + 12 ;
		}
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("00");
		return "logs" + year+ df.format(month) ;
	}
}
