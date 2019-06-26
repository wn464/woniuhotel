package com.project.dao;

import org.apache.ibatis.annotations.Select;

import com.project.bean.RoleBean;

public interface IRoleDao {
	
	
	public RoleBean findRoleById(int id);
}
