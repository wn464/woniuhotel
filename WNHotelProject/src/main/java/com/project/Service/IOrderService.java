package com.project.Service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.project.bean.LiveBean;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;
import com.project.bean.PeopleBean;

/**
 * 订单业务
 * @author x1c
 *
 */
public interface IOrderService {

	//添加并获取订单信息
	public OrderBean getOrder(OrderBean orderBean);
	//前台和后台通过状态查询订单
	public PageBean selectOrderByState(int mid, int status, int page, int size);
	//后台通过入住信息查询订单
	public List<OrderBean> selectOrderByAttr(String people, String time);
	//修改订单状态
	public int updateOrderAttr(OrderBean orderBean);
	//通过订单id查询订单
	public OrderBean selectOrderById(int oid);
	//通过订单号查询订单
	public OrderBean selectOrderByOrderNumber(String orderNumber);
	//通过预定状态查询订单
	public PageBean selectOrderBySubStatus(int subscribeStatus, int page, int size);
	//通过时间段查询订单
	public PageBean selectOrderByTime(int subscribeStatus,String startTime, String endTime, int page, int size);
	//统计订单
	public List<OrderBean> selectOrderByMonth(int year, int startMonth, int endMonth);
	
	public List<OrderBean> selectOrderBySub(int subscribeStatus);
	
	
	
}
