package com.project.bean;

import java.sql.Timestamp;

public class DiscountBean {

	public int id ;//主键
	private String name;//优惠名字
	private int discountType;//优惠类型
	private Timestamp beginTime;//开始时间
	private Timestamp endTime;//结束时间
	private int vip;//vip等级
	private double number1;//数字段1
	private double number2;//数字段2
	private double number3;//数字段3
	private double number4;//数字段4
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDiscountType() {
		return discountType;
	}
	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public double getNumber1() {
		return number1;
	}
	public void setNumber1(double number1) {
		this.number1 = number1;
	}
	public double getNumber2() {
		return number2;
	}
	public void setNumber2(double number2) {
		this.number2 = number2;
	}
	public double getNumber3() {
		return number3;
	}
	public void setNumber3(double number3) {
		this.number3 = number3;
	}
	public double getNumber4() {
		return number4;
	}
	public void setNumber4(double number4) {
		this.number4 = number4;
	}
	@Override
	public String toString() {
		return "DiscountBean [id=" + id + ", name=" + name + ", discountType=" + discountType + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", vip=" + vip + ", number1=" + number1 + ", number2=" + number2
				+ ", number3=" + number3 + ", number4=" + number4 + "]";
	}
	
	
}
