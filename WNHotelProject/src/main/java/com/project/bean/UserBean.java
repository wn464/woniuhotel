package com.project.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserBean {

	private int id;//操作员id主键
	@NotBlank(message = "用户名不能为空")
	private String username;//操作员登录名
	@Pattern(regexp="^[0-9a-zA-Z]{4,12}$",message="密码长度必须为4-12位")
	private String password;//操作员登录密码
	private RoleBean role;//操作员权限
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RoleBean getRole() {
		return role;
	}
	public void setRole(RoleBean role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
}
