package com.project.bean;

import java.sql.Timestamp;
import java.util.List;

public class OrderBean {

	private int id;//订单主键
	private Timestamp orderTime;//订单生成时间
	private String orderNumber;//订单号
	private int status;//订单状态 失效，未支付、已支付
	private String alipayNumber;//支付宝号
	private double payMoney;//押金
	private double price;//订单金额
	private MemberBean member;//订单所属用户
	private int subcribeStatus;//订单是否处于预约状态
	private CommentBean comment;//订单评论，避免sql死循环，不要直接查询，由comment进行维护
	private List<LiveBean> lives;//居住房间数组
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAlipayNumber() {
		return alipayNumber;
	}
	public void setAlipayNumber(String alipayNumber) {
		this.alipayNumber = alipayNumber;
	}
	public double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public MemberBean getMember() {
		return member;
	}
	public void setMember(MemberBean member) {
		this.member = member;
	}
	public int getSubcribeStatus() {
		return subcribeStatus;
	}
	public void setSubcribeStatus(int subcribeStatus) {
		this.subcribeStatus = subcribeStatus;
	}
	public CommentBean getComment() {
		return comment;
	}
	public void setComment(CommentBean comment) {
		this.comment = comment;
	}
	public List<LiveBean> getLives() {
		return lives;
	}
	public void setLives(List<LiveBean> lives) {
		this.lives = lives;
	}
	@Override
	public String toString() {
		return "OrderBean [id=" + id + ", orderTime=" + orderTime + ", orderNumber=" + orderNumber + ", status="
				+ status + ", alipayNumber=" + alipayNumber + ", payMoney=" + payMoney + ", price=" + price
				+ ", member=" + member + ", subcribeStatus=" + subcribeStatus + ", comment=" + comment + ", lives="
				+ lives + "]";
	}
	
	
}
