package com.project.Service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import com.project.bean.MemberBean;

@Service
public interface IMemberService {
		//登录
		public MemberBean login(String username);
		
		//注册
		public int reg(MemberBean member);
		
		//查看个人用户信息
		public MemberBean selectById(int id);
		
		//修改密码
		public int updatePassword(MemberBean member);
		
		//修改消费金额
		public int updateMoney(double money,int id);
		
		//修改VIP等级
		public int updateVip(MemberBean member);
}
