package com.project.bean;

/**
 * vip等级
 * @author my
 *
 */
public class VipBean {

	private int id;//主键
	private double discount;//优惠折扣
	private String name;//vip名称
	private double maxMoney;//vip最低消费金额
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMaxMoney() {
		return maxMoney;
	}
	public void setMaxMoney(double maxMoney) {
		this.maxMoney = maxMoney;
	}
	@Override
	public String toString() {
		return "VipBean [id=" + id + ", discount=" + discount + ", name=" + name + ", maxMoney=" + maxMoney + "]";
	}
	
	
}
