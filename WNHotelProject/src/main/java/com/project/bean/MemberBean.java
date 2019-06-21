package com.project.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 宾客账号
 * @author my
 *
 */
public class MemberBean {

	private int id;//主键
	@NotBlank(message = "用户名不能为空")
	private String userName;//登录名
	@Pattern(regexp="^[0-9a-zA-Z]{6,12}$",message="密码长度必须为6-12位")
	private String password;//登录密码
	@Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$",message = "请输入正确的手机号")
	private String phoneNumber;//电话号码
	private int vip;//vip等级
	private double money;//消费金额
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", userName=" + userName + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", vip=" + vip + ", money=" + money + "]";
	}
	
	
}
