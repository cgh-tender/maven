package com.dw.common;


/**
 * 响应工具�?
 */
public class ResponseJson {
	
	String responseCode = "" ;//响应�?
	Object responseText = null ;//响应内容{当}
	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public Object getResponseText() {
		return responseText;
	}

	public void setResponseText(Object responseText) {
		if  ( null == responseText ) responseText = "";
		this.responseText = responseText;
	}
}
