package com.project.dao;

import org.apache.ibatis.annotations.Select;

import com.project.bean.RoleBean;

public interface IRoleDao {
	
	@Select("select * from role where id=#{id}")
	public RoleBean findRoleById(int id);
}
