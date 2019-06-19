package com.project.Service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import com.project.bean.UserBean;

@Service
public interface IUserService {

	//通过id查找用户信息
	public UserBean selectUserById(int id);
	
	//登录时 通过用户名查找用户
	public UserBean login(String username);
	
	//添加用户
	public int reg(UserBean user);
	
	//修改用户密码
	public int updatePassword(UserBean user);
	
	//修改操作员权限
	public int updateRole(UserBean user);
}
