package com.dw.model;

import java.util.List;

public class ResultMessage5 {

	private String code;
	private String msg;
	private List data;
	private Integer level = 1;
	private Integer isLowest = 0;
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getIsLowest() {
		return isLowest;
	}
	public void setIsLowest(Integer isLowest) {
		this.isLowest = isLowest;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
}
