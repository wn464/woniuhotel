package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.MemberBean;

/*
 * 前台 用户  dao层
 */
public interface IMemberDao {
	
	
	//注册
	@Insert("insert into member(username,password,phonenumber,money) values(#{userName},#{password},#{phoneNumber},0)")
	public int reg(MemberBean member);
	
	//查看个人用户信息
	public MemberBean selectById(int id);
	
	//修改密码
	@Update("update member set password = #{repassword} where id = #{id}")
	public int updatePassword(String repassword,int id);
	
	//修改消费金额
	@Update("update member set money = #{money} where id = #{id}")
	public int updateMoney(double money,int id);
	
	//修改VIP等级
	@Update("update member set vip=#{vip.id} where id = #{id}")
	public int updateVip(MemberBean member);
	
	//通过用户名查找	
	public MemberBean selectByUsername(String userName);
	
	//通过手机号查询
	public MemberBean selectByPhoneNumber(String phoneNumber);
	
}
