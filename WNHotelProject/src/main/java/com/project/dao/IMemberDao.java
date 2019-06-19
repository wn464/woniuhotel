package com.project.dao;

import com.project.bean.MemberBean;

public interface IMemberDao {
	
	//登录
	public MemberBean login(String username);
	
	
}
