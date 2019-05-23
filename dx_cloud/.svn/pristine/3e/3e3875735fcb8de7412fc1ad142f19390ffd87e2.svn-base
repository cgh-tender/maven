package com.dw.common;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Properties;


public class LogManage {
	private String logname ;
	private String filePath  = "" , folder = "" ;
	String fileName = "" ;
	static class CommonUtil{
		public static boolean isEmpty(String folder) {
			if ( null == folder || "".equals(folder) ) return true;
			return false;
		}
	}
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private static LogManage LogManage = null ;
	public static synchronized LogManage newInstance(String logname,String fileName){
		if ( null == LogManage ) {
			LogManage = new LogManage(logname, fileName);
		} else {
			LogManage.setLogname(logname);
			LogManage.setFileName(fileName);
		}
		return LogManage ;
	}
	
	
	public LogManage(String logname){
		this.logname = logname ;
	}
	public LogManage(String logname,String fileName){
		this.logname = logname ;
		if (null != fileName ) { this.fileName = fileName ; } 
	}
	public LogManage(String logname,String fileName,String folder){
		this.logname = logname ;
		if (null != fileName ) { this.fileName = fileName ; } 
		this.folder = folder ;
	}
	private static String getDate(){
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}
	private static String getDates(){
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}
	private void init(){
		try {
		//	System.out.println("caonima++++++++++++++++++++++++++++++++++++++++===");
			Properties po=System.getProperties();
		//	String str1 = po.getProperty("os.name");
			String filepath = "";  
			String tomcatPath=po.getProperty("user.dir");
			//System.out.println(tomcatPath);
		        if (tomcatPath.contains("/")) {  
		            filepath = tomcatPath.replace("/bin", "/webapps/appmvc/");  
		        } else {  
		            filepath = tomcatPath.replace("\bin", "\\webapps\\appmvc\\");  
		        }  
		        File rfile=new File(filepath);
		        if(!rfile.exists()){
		        	filepath = tomcatPath;  
		        }
		     //df  System.out.println(filepath);
			String rootPath = filepath+File.separator+"logs" ;
			if ( !CommonUtil.isEmpty(this.folder) ) {
				rootPath = this.folder ;
			}
			File file= new File(rootPath);
			if ( !file.exists() ){
				file.mkdir();
			}
			file= new File(rootPath+"/"+this.fileName);
			if ( !file.exists() ){
				file.mkdir();
			}
			rootPath = rootPath+"/"+this.fileName ;
			this.filePath = rootPath+"/"+ this.fileName+getDate()+".log" ;
			file = new File(this.filePath);
			if (!file.exists()){
				file.createNewFile();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void error(String msg){
		try {
			init();
			String mssg = ("["+getDates()+"] [ERROR] ["+this.logname+"]"+msg+"\r\n");
			FileWriter   fw   =   new   FileWriter(filePath,true);
			fw.write(mssg);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public synchronized void warn(String msg){
		try {
			init();
			String mssg = ("["+getDates()+"] [WARN] ["+this.logname+"]"+msg+"\r\n");
			FileWriter   fw   =   new   FileWriter(filePath,true);
			fw.write(mssg);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	public synchronized void info(String msg){
		try {
			init();
			String mssg = ("["+getDates()+"] [INFO] ["+this.logname+"]"+msg+"\r\n");
			FileWriter   fw   =   new   FileWriter(filePath,true);
			fw.write(mssg);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public static void main(String[] args) {
		
		//C:\tomcat\apache-tomcat-6.0.37\bin
		Properties po=System.getProperties();
		String tomcatPath=po.getProperty("user.dir");
		tomcatPath="C:\\tomcat\\apache-tomcat-6.0.37\\bin";
	        String filepath = "";  
	        if (tomcatPath.contains("//")) {  
	            filepath = tomcatPath.replace("//bin", "//webapps//appmvc//");  
	        } else {  
	            filepath = tomcatPath.replace("/bin", "/webapps/appmvc/");  
	        }  
	        System.out.println(filepath);
//		LogManage ll=new LogManage("sssss");
//		ll.info("666");
	}
}
