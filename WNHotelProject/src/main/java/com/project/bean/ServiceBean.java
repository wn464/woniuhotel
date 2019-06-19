package com.project.bean;

public class ServiceBean {

	private int id;//主键id
	private String name;//名称
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
	@Override
	public String toString() {
		return "ServiceBean [id=" + id + ", name=" + name + "]";
	}
	
}
