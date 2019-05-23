package com.dw.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class StringUtil {
	/**
	 * �ж��ַ��Ƿ�Ϊ��
	 * 
	 * @param inStr �ַ�
	 * @return true null����""
	 *         false ����
	 */
	public static boolean isEmpty(String inStr) {
		boolean retBoo = false;
		if (inStr == null || "".equals(inStr) || "null".equals(inStr) || "".equals(inStr.trim())) {
			retBoo = true;
		}
		return retBoo;
	}

	public static String uRLEncoder(String str) throws Exception{
		if(isEmpty(str))return "";
		 try {
			str=URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return str;
	}
	public static String uRLDecoder(String str) throws Exception{
		if(isEmpty(str))return "";
		try {
			str=URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return str;
	}
	/**0:"yyyy-MM-dd";1:"yyyy-MM-dd HH:mm:ss";
	 * @throws Exception **/
	public static Date stringToDate(String str,int in) throws Exception {
		if(isEmpty(str))return null;
		String format="";
		if(in==0)format="yyyy-MM-dd";
		if(in==1){
			format="yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Date cDate=null;
		try {
			cDate = sformat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return cDate;
		
	}
	/**0:"yyyy-MM-dd";1:"yyyy-MM-dd HH:mm:ss";
	 * @throws Exception **/
	public static String  dateToString(Date str,int in) throws Exception {
		if(str==null||isEmpty(str+""))return null;
		String format="";
		if(in==0)format="yyyy-MM-dd";
		if(in==1){
			format="yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		String cDate=null;
		try {
			cDate = sformat.format(str);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return cDate;
	}
	public static Long  getOrder() throws Exception {
		String format="";	
			format="yyyyMMddHHmmssSSS";		
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		String cDate=null;
		try {
			cDate = sformat.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return Long.parseLong(cDate);
	}
	public static void main(String[] args) throws Exception {
		System.out.println(getOrder());
		Date date=stringToDate("2014-05-05", 0);
		System.out.println(date);		
		String str=dateToString(new Date(), 0);
		System.out.println(str);
		str=dateToString(new Date(), 1);
		System.out.println(str);		
	}
	public static Long[] StringToLong(String [] arg){
		if(arg==null||arg.length==0)return null;
		Long[] brg=new Long[arg.length];
		for (int i = 0; i < arg.length; i++) {
			brg[i]=Long.valueOf(arg[i]);
		}
		return brg;
	}
	public static Integer[] StringToInteger(String [] arg){
		if(arg==null||arg.length==0)return null;
		Integer[] brg=new Integer[arg.length];
		for (int i = 0; i < arg.length; i++) {
			brg[i]=Integer.valueOf(arg[i]);
		}
		return brg;
	}

		/**
		 * 将字符串转换成数组,按照tag分割
		 */
		public static String[] str2Arr(String str,String tag){
			if(ValidateUtil.isValid(str)){
				return str.split(tag);
			}
			return null ;
		}

		/**
		 * 判断在values数组中是否含有指定value字符串
		 */
		public static boolean contains(String[] values, String value) {
			if(ValidateUtil.isValid(values)){
				for(String s : values){
					if(s.equals(value)){
						return true ;
					}
				}
			}
			return false;
		}

		/**
		 * 将数组变换成字符串,使用","号分割
		 */
		public static String arr2Str(Object[] arr) {
			String temp = "" ;
			if(ValidateUtil.isValid(arr)){
				for(Object s : arr){
					temp = temp + s + "," ;
				}
				return temp.substring(0,temp.length() - 1);
			}
			return temp;
		}
		
		//获得字符串的描述信息
		public static String getDescString(String str){
			if(str != null && str.trim().length() > 30){
				return str.substring(0,30);
			}
			return str ;
		}
}