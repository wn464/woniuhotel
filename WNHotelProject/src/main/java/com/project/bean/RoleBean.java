package com.project.bean;

public class RoleBean {

	private int id;//权限id主键
	private String role;//权限字段
	@Override
	public String toString() {
		return "RoleBean [id=" + id + ", role=" + role + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
