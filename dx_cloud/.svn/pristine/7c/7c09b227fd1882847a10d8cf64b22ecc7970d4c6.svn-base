package com.dw.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.dw.model.SynUser;
import com.thoughtworks.xstream.XStream;

public class HttpClientUtil {

	public static void main(String[] args) {
		 
		execute();
	}

	public static void execute(){
		SynUser user = new  SynUser();
		
		user.setAccount("zhangsan");
		user.setEmail("123@qq.com");
 		XStream xstream = new XStream();
 		String httpUrl = "http://127.0.0.1:8888/dx_cloud/receive/sysUser";
		HttpPost httpRequest=new HttpPost(httpUrl);
		//使用NameValuePair来保存要传递的Post参数
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("synUser",xstream.toXML(user)));
		//设置字符集
		try {
			HttpEntity httpentity=new UrlEncodedFormEntity(params,"utf-8");
			//取得HttpClient对象
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
			CloseableHttpClient httpClient = httpClientBuilder.build();
 			HttpResponse httpResponse =httpClient.execute(httpRequest); 
 			
 			System.out.println(httpResponse.getStatusLine().getStatusCode());
 			
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){ 
				String strResult=EntityUtils.toString(httpResponse.getEntity());
				System.out.println(strResult);
			}
		} catch ( Exception e) {
 			e.printStackTrace();
		}
 	}
	
}
