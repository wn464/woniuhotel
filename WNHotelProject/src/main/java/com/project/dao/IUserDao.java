package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.UserBean;

/*
 * 操作员 dao
 */
public interface IUserDao {
	
		//通过id查找用户信息
		@Select("select * from user where id=#{id}")
		public UserBean selectUserById(int id);
		
		//登录时 通过用户名查找用户
		@Select("select * from user where username=#{username}")
		public UserBean selectByUserName(String username);
		
		//添加用户
		@Insert("insert into user(username,password,role) values(#{username},#{password},#{role.id})")
		public int reg(UserBean user);
		
		//修改用户密码
		@Update("update user set password = #{password} where id = #{id}")
		public int updatePassword(UserBean user);
		
		//修改操作员权限
		@Update("update user set role = #{role.id} where id = #{id}")
		public int updateRole(UserBean user);
		
		
	
	
}
