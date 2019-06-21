package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.bean.LiveBean;
import com.project.bean.MarkBean;
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
	public List<OrderBean> selectOrderByState(@Param("mid")int mid,@Param("status")int status,@Param("page")int page,@Param("size")int size);
	//后台通过开房人属性和状态分页查询订单
	public List<OrderBean> selectOrderByAttr(LiveBean liveBean);
	//修改订单属性
	public int updateOrderAttr(OrderBean orderBean);
	//根据状态查询订单总数量
	public int selectNumberByState(int status);
	//通过订单号查询订单id
	public int selectNumberByOrderNumber(String orderNumber);
	
	
}
