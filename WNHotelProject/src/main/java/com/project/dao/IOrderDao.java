package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

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
	//后台通过开房人属性(oid)查询订单
	public OrderBean selectOrderByAttr(LiveBean liveBean);
	//修改订单属性
	public int updateOrderAttr(OrderBean orderBean);
	//根据状态查询订单总数量
	public int selectNumberByState(int status);
	//通过订单号查询订单id
	public OrderBean selectOrderByOrderNumber(String orderNumber);
	//通订单id查询订单
	public OrderBean selectOrderById(int oid);
	//通过预定状态查询订单
	public List<OrderBean> selectOrderBySubStatus(int subscribeStatus, int page, int size);
	//根据状态查询订单总数量
	public int selectNumberBySubStatus(int subStatus);
	//根据时间段查询订单
	public List<OrderBean> selectOrderByTime(@PathVariable("startTime")String startTime,@PathVariable("endTime")String endTime,@Param("page")int page,@Param("size")int size);
	//根据时间段查询订单总数量
	public int selectNumberByTime(@PathVariable("startTime")String startTime,@PathVariable("endTime")String endTime);
	
}
