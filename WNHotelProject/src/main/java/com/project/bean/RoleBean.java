package com.project.bean;

public class RoleBean {

	private int id;//权限id主键
	private String name;//权限字段


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
		return "RoleBean [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
