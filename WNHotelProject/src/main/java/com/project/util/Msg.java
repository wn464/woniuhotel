package com.project.util;

import java.util.HashMap;
import java.util.Map;

public class Msg {

	//请求成功或者失败
	private Integer code;
	
	private String message;
	
	private Map<String,Object> map = new HashMap<String,Object>();

	
	public static Msg success() {
		Msg msg = new Msg();
		msg.setCode(200);
		msg.setMessage("请求成功");
		return msg;
	}
	
	public static Msg failed() {
		Msg msg = new Msg();
		msg.setCode(500);
		msg.setMessage("请求失败");
		return msg;
	}
	
	public Msg add(String key,Object value) {
		this.getMap().put(key, value);
		return this;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
	
	
}
