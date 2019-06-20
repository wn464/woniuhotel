package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.bean.LiveBean;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;
import com.project.bean.PeopleBean;

/**
 * 订单持久层
 * @author x1c
 *
 */
public interface IOrderDao {

	//添加订单
	public int insertOrder(OrderBean orderBean);
	//前台和后台通过状态分页查询订单
	public List<OrderBean> selectOrderByState(int mid,int status,int page,int size);
	//后台通过开房人属性和状态分页查询订单
	public List<OrderBean> selectOrderByAttr(LiveBean liveBean);
	//修改订单状态
	public int updateOrderState(@Param("oid")int oid,@Param("status")int status);
	//根据状态查询订单总数量
	public int selectNumberByState(int status);
	
}
