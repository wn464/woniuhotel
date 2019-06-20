package com.project.bean;

public class DiscountTypeBean {

	private int id;//主键ID
	private String name;//优惠类型名称
	private String message;//优惠参数描述
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "DiscountTypeBean [id=" + id + ", name=" + name + ", message=" + message + "]";
	}
	
}
