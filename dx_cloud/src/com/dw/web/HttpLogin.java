package com.dw.web;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
 
public class HttpLogin {
 
    public static void main(String[] args) {
        // 登陆 Url
        String loginUrl = "http://127.0.0.1:8080/dx_cloud/userlogin/admin?password=dsdod";
        HttpClient httpClient = new HttpClient();
         // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
         GetMethod getMethod = new GetMethod(loginUrl);
         try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode=httpClient.executeMethod(getMethod);
             // 获得登陆后的 Cookie
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
             }
            if(statusCode==200){ 
                System.out.println("模拟登录成功");
                getMethod = new GetMethod("http://127.0.0.1:8080/dx_cloud/jurmanagement/jurdetaillist?menu_id=16010002&role_id=1");
                getMethod.setRequestHeader("cookie", tmpcookies.toString());
                httpClient.executeMethod(getMethod);
                String text = getMethod.getResponseBodyAsString();
                System.out.println(text);
            }
            else {
                System.out.println("登录失败");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}