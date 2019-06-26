package com.project.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.IUserService;
import com.project.bean.UserBean;
import com.project.dao.IUserDao;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao dao;
	
	/*
	 * 通过id查找用户信息
	 */
	@Override
	public UserBean selectUserById(int id) {
		// TODO Auto-generated method stub
		return dao.selectUserById(id);
	}

	
	/*
	 * 登录 （通过用户名查找用户）
	 */
	@Override
	public UserBean selectByUserName(String username) {
		// TODO Auto-generated method stub
		return dao.selectByUserName(username);
	}

	/*
	 * 注册user
	 */
	@Override
	public int reg(UserBean user) {
		// TODO Auto-generated method stub
		return dao.reg(user);
	}

	/*
	 * 修改user密码
	 */
	@Override
	public int updatePassword(UserBean user) {
		// TODO Auto-generated method stub
		return dao.updatePassword(user);
	}
	
	/*
	 * 修改user 信息
	 */
	@Override
	public int updateRole(UserBean user) {
		// TODO Auto-generated method stub
		return dao.updateRole(user);
	}

	
	/*
	 * 查询所有 操作员
	 */
	@Override
	public List<UserBean> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

}
