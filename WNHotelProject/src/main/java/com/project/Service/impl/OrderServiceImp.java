package com.project.Service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.Service.IOrderService;
import com.project.bean.LiveBean;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;
import com.project.dao.ILiveDao;
import com.project.dao.IOrderDao;
import com.project.util.CreateOrderInfo;
import com.project.util.WebSocketUtil;
import com.project.util.timingutil.ordertiming;
/**
 * 订单业务
 * @author x1c
 *
 */
@Service
public class OrderServiceImp implements IOrderService{
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private ILiveDao liveDao;
	//添加并获取订单信息
	@Override
	public OrderBean getOrder(OrderBean orderBean) {
		//生成订单时间
		String orderTime= CreateOrderInfo.getCreateTime();
	  	orderBean.setOrderTime(orderTime);
	  	//生成订单号
	    String orderNumber =  CreateOrderInfo.getOrderNumber();
	    orderBean.setOrderNumber(orderNumber);
	    //添加订单
	    int num =orderDao.insertOrder(orderBean);
		OrderBean orderBean2 = orderDao.selectOrderByOrderNumber(orderBean.getOrderNumber());
		//添加入住信息
		List<LiveBean> liveBeans = orderBean.getLives();
		for (LiveBean liveBean : liveBeans) {
			liveBean.setOrderid(orderBean2.getId());
			 num = liveDao.insertLiveBean(liveBean);
		}
		//生成订单
		OrderBean orderBean3 = orderDao.selectOrderByOrderNumber(orderNumber);
		//开启定时器
		ordertiming.ds(orderBean3.getId(),this);
		WebSocketUtil.sendMessageAll("有新订单了");
		return orderBean3;
	}

	//通过订单状态分页查询订单
	@Override
	public PageBean selectOrderByState(int mid, int status,int page,int size) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		page = (page-1)*size;
		List<OrderBean> list = orderDao.selectOrderByState(mid, status, page, size);
		int totalNumber  = orderDao.selectNumberByState(status);
		pageBean.setSize(size);
		pageBean.setTotalNumber(totalNumber);
		int totalPage = (totalNumber%size==0)?totalNumber/size:(totalNumber/size)+1;
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}
	
	//通过入住信息查询订单
	@Override
	public List<OrderBean> selectOrderByAttr(String people,String time) {
		List<OrderBean> list = new ArrayList<OrderBean>();
		//通过姓名和时间模糊查询开房信息
		List<LiveBean> liveBean = liveDao.selectBypeopleAndDate(people, time);
		
		OrderBean orderBean = null;
		for (LiveBean liveBean2 : liveBean) {
			//通过入住信息（orderid）查询订单
			orderBean = orderDao.selectOrderByAttr(liveBean2);
			list.add(orderBean);
		}
		return list;
	}

	//修改订单属性
	@Override
	public int updateOrderAttr(OrderBean orderBean) {
		int num = orderDao.updateOrderAttr(orderBean);
		return num;
	}
	//通过订单id查询订单
	@Override
	public OrderBean selectOrderById(int oid) {
		OrderBean orderBean = orderDao.selectOrderById(oid);
		return orderBean;
	}
	//通过订单号查询订单
	@Override
	public OrderBean selectOrderByOrderNumber(String orderNumber) {
		OrderBean orderBean = orderDao.selectOrderByOrderNumber(orderNumber);
		return orderBean;
	}
	//通过预定状态查询订单
	@Override
	public PageBean selectOrderBySubStatus(int subscribeStatus, int page, int size) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		page = (page-1)*size;
		List<OrderBean> list = orderDao.selectOrderBySubStatus(subscribeStatus, page, size);
		int totalNumber  = orderDao.selectNumberBySubStatus(subscribeStatus);
		pageBean.setSize(size);
		pageBean.setTotalNumber(totalNumber);
		int totalPage = (totalNumber%size==0)?totalNumber/size:(totalNumber/size)+1;
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}
	//根据时间段查询订单
	@Override
	public PageBean selectOrderByTime(int subscribeStatus,String startTime,String endTime,int page,int size){
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		page = (page-1)*size;
		List<OrderBean> list = orderDao.selectOrderByTime(subscribeStatus,startTime, endTime, page, size);
		int totalNumber  = orderDao.selectNumberByTime(startTime, endTime);
		pageBean.setSize(size);
		pageBean.setTotalNumber(totalNumber);
		int totalPage = (totalNumber%size==0)?totalNumber/size:(totalNumber/size)+1;
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}


//	统计订单
	@Override
	public List<OrderBean> selectOrderByMonth(int year,int startMonth, int endMonth) {
//		List<OrderBean> list = null;
//		for (int i = startMonth; i <= endMonth; i++) {
//			String startTime = year+"-"+startMonth+"-01"+" "+"00:00:00";
//			String endTime = year+"-"+endMonth+"-31"+" "+"00:00:00";
//			list = orderDao.selectOrderByMonth(startTime, endTime);
//		}
		return null;
	}

	


}
