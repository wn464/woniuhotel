package com.project.bean;

/**
 * 订单评价
 * 便于后台直接查看
 * @author my
 *
 */
public class CommentBean {
   //注释
	
	private int id;//主键ID
	private OrderBean orderId;//订单号，可以根据显示用户，评论，避免sql死循环，不要直接查询，order不进行查询comment。
	private String message;//订单完成后评论
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrderBean getOrderId() {
		return orderId;
	}
	public void setOrderId(OrderBean orderId) {
		this.orderId = orderId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CommentBean [id=" + id + ", orderId=" + orderId + ", message=" + message + "]";
	}
	
	
}
