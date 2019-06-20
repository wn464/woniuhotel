package com.project.Service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		List<OrderBean> list = orderDao.selectOrderByState(mid, status, page, size);
		int totalNumber  = orderDao.selectNumberByState(status);
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		pageBean.setSize(size);
		pageBean.setTotalNumber(totalNumber);
		int totalPage = (totalNumber%size==0)?totalNumber/size:(totalNumber/size)+1;
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}
	
	//通过入住信息分页查询订单
	@Override
	public List<OrderBean> selectOrderByAttr(String people,String time) {
		
//		List<OrderBean> list = orderDao.selectOrderByAttr();
		return null;
	}

	//修改订单状态
	@Override
	public int updateOrderState(int oid, int status) {
		int num = orderDao.updateOrderState(oid, status);
		return num;
	}

}
