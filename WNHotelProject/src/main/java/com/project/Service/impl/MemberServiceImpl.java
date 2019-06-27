package com.project.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.IMemberService;
import com.project.Service.IVipService;
import com.project.bean.MemberBean;
import com.project.bean.VipBean;
import com.project.dao.IMemberDao;
import com.project.dao.IUserDao;

/*
 * 会员  serviceimpl接口
 */
@Service
public class MemberServiceImpl implements IMemberService {
	@Autowired
	private IMemberDao dao;
	@Autowired
	private IVipService vipSerice;
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
	public int updatePassword(String repassword,int id) {
		// TODO Auto-generated method stub
		
		return dao.updatePassword(repassword, id);
	}

	/*
	 * 修改以消费金额
	 */
	@Override
	public int updateMoney(double money, int id) {
		// TODO Auto-generated method stub
		MemberBean bean = dao.selectById(id);	//获取bean对象 
		
		double money1 = bean.getMoney()+money;	//获取bean对象已消费的金额 + 本次消费的金额
		
		int updateMoney = dao.updateMoney(money1, id);		//修改数据库金额
		
		VipBean vip = vipSerice.selectVipByMoney(money1);					//查询当前总消费 返回vipbean 
		System.out.println("vip--------------------------:"+vip);
		System.out.println("bean--------------------------:"+bean);
		
		
		MemberBean member = new MemberBean();				//new 一个memberBean 把数据封装到memebr
		member.setId(id);
		member.setVipBean(vip);
		
		int updateVip = updateVip(member);					//修改vip等级
		
		return updateMoney;									//返回数据库总金额
	}
	
	/*
	 * 修改vip等级
	 */
	@Override
	public int updateVip(MemberBean member) {
		// TODO Auto-generated method stub
		return dao.updateVip(member);
	}
	
	/*
	 * 通过用户名查找
	 */
	@Override
	public MemberBean selectByUsername(String userName) {
		// TODO Auto-generated method stub
		return dao.selectByUsername(userName);
	}
	
	/*
	 * 通过手机号查询
	 */
	@Override
	public MemberBean selectByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return dao.selectByPhoneNumber(phoneNumber);
	}

}
