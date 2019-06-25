package com.project.shiro;

public enum LoginType {

	user("user"),admin("admin");

	private String type;
	private  LoginType(String type) {
		 this.type = type;
	}
	@Override
	public String toString() {
		return this.type.toString();
	}
}
