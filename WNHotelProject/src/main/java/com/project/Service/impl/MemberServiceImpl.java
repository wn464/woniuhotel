package com.project.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Service.IMemberService;
import com.project.bean.MemberBean;
import com.project.dao.IMemberDao;
import com.project.dao.IUserDao;

/*
 * 会员  serviceimpl接口
 */
public class MemberServiceImpl implements IMemberService {
	@Autowired
	private IMemberDao dao;
	
	
	/*
	 * //登录
	 */
	@Override
	public MemberBean login(String userName) {
		// TODO Auto-generated method stub
		return dao.login(userName);
	}
	
	/*
	 * 注册
	 */
	@Override
	public int reg(MemberBean member) {
		// TODO Auto-generated method stub
		return dao.reg(member);
	}
	
	//通过id查询用户信息
	@Override
	public MemberBean selectById(int id) {
		// TODO Auto-generated method stub
		return dao.selectById(id);
	}
	
	/*
	 * 修改密码
	 */
	@Override
	public int updatePassword(MemberBean member) {
		// TODO Auto-generated method stub
		return dao.updatePassword(member);
	}

	/*
	 * 修改以消费金额
	 */
	@Override
	public int updateMoney(double money, int id) {
		// TODO Auto-generated method stub
		MemberBean bean = dao.selectById(id);	//获取bean对象 
		double money1 = bean.getMoney()+money;	//获取bean对象已消费的金额 + 本次消费的金额
		return dao.updateMoney(money1, id);		
	}
	
	/*
	 * 修改vip等级
	 */
	@Override
	public int updateVip(MemberBean member) {
		// TODO Auto-generated method stub
		return dao.updateVip(member);
	}

}