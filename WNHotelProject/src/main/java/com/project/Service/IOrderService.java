package com.project.Service;

import java.util.List;
import java.util.Map;

import com.project.bean.OrderBean;
import com.project.bean.PageBean;
import com.project.bean.PeopleBean;

/**
 * 订单业务
 * @author x1c
 *
 */
public interface IOrderService {

	//添加订单
	public int insertOrder(OrderBean orderBean);
	//前台和后台通过状态查询订单
	public PageBean selectOrderByState(int mid,int state);
	//后台通过开房人属性查询订单
	public PageBean selectOrderByState(PeopleBean peopleBean);
	//修改订单状态
	public int updateOrderState(int oid,int state);
	
}
