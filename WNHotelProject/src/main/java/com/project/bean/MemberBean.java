package com.project.bean;

/**
 * 宾客账号
 * @author my
 *
 */
public class MemberBean {

	private int id;//主键
	private String userName;//登录名
	private String password;//登录密码
	private String phoneNumber;//电话号码
	private VipBean vip;//vip等级
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
	public VipBean getVip() {
		return vip;
	}
	public void setVip(VipBean vip) {
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
