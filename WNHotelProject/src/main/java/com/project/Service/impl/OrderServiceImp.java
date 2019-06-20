package com.project.Service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.Service.IOrderService;
import com.project.bean.LiveBean;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;
import com.project.dao.ILiveDao;
import com.project.dao.IOrderDao;
/**
 * 订单业务
 * @author x1c
 *
 */
@Service
public class OrderServiceImp implements IOrderService {
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private ILiveDao liveDao;
	//添加订单
	@Override
	public int insertOrder(OrderBean orderBean) {
		List<LiveBean> liveBeans = orderBean.getLives();
		for (LiveBean liveBean : liveBeans) {
			liveDao.insertLiveBean(liveBean);
		}
		int num = orderDao.insertOrder(orderBean);
		return num;
	}

	//通过订单状态分页查询订单
	@Override
	public PageBean selectOrderByState(int mid, int status,int page,int size) {
		page = (page-1)*size;
		System.out.println("_________________");
		List<OrderBean> list = orderDao.selectOrderByState(mid, status, page, size);
		System.out.println("-=-=-=-"+list);
//		int totalNumber  = orderDao.selectNumberByState(status);
		PageBean pageBean = new PageBean();
//		pageBean.setPage(page);
//		pageBean.setSize(size);
//		pageBean.setTotalNumber(totalNumber);
//		int totalPage = (totalNumber%size==0)?totalNumber/size:(totalNumber/size)+1;
//		pageBean.setTotalPage(totalPage);
//		pageBean.setList(list);
		return pageBean;
	}
	
	//通过入住信息查询订单
	@Override
	public List<OrderBean> selectOrderByAttr(String people,String time) {
		
		LiveBean liveBean = liveDao.selectBypeopleAndDate(people, time);
		List<OrderBean> list = orderDao.selectOrderByAttr(liveBean);
		for (OrderBean orderBean : list) {
			System.out.println(orderBean);
			List<LiveBean> lives = orderBean.getLives();
			List<LiveBean> list2= new ArrayList<LiveBean>();
			System.out.println("___"+list2);
			list2.addAll(lives);
			list2.add(liveBean);
			
			System.out.println("====="+list2);
			
			
		}
		System.out.println(list);
		return list;
	}

	//修改订单状态
	@Override
	public int updateOrderState(int oid, int status) {
		int num = orderDao.updateOrderState(oid, status);
		return num;
	}

}
