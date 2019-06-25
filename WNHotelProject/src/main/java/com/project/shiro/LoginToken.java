package com.project.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class LoginToken extends UsernamePasswordToken {

	
	private static final long serialVersionUID = 1L;

	private String loginType;

	public LoginToken(String username,String password,String loginType) {
		super(username, password);
		this.loginType = loginType;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	
}
