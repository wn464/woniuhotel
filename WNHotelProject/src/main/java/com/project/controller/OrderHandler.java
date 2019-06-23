package com.project.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Service.IOrderService;
import com.project.bean.LiveBean;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;

/**
 * 订单接口
 * @author x1c
 *
 */
@Controller
@RequestMapping("/order")
public class OrderHandler {
	@Autowired
	private IOrderService orderService;
	
	/*
	 * 前台预定同时生成订单
	 */
	@PostMapping
	@ResponseBody
	public OrderBean getOrder(OrderBean orderBean) {
		//获取session的用户id
		OrderBean orderBean2 = orderService.getOrder(orderBean);
		return orderBean2;
	}
	/*
	 * 后台按照入住人员信息查询订单
	 */
	@GetMapping("/attr")
	@ResponseBody
	public List<OrderBean> selectOrderByAttr(LiveBean liveBean) {
		List<OrderBean> list = orderService.selectOrderByAttr(liveBean.getPeople(), liveBean.getInTime());
		return list;
	}
	/*
	 * 前台通过状态分页查询订单
	 */
	@GetMapping("/state/{status}")
	public String selectOrderByState(@PathVariable("status")int status) {
	    Subject subject = SecurityUtils.getSubject();
	    Session session = subject.getSession();
	    session.setAttribute("id", 1);//测试使用
        int mid = (int) session.getAttribute("id");
		PageBean pageBean = orderService.selectOrderByState(mid, status, 1, 4);
		System.out.println(pageBean);
		return "myorder.html";
	}
	/*
	 * 后台通过状态分页查询订单
	 */
	@GetMapping("/subStatus/{subscribeStatus}/{page}/{size}")
	@ResponseBody
	public PageBean selectOrderBySubStatus(@PathVariable("subscribeStatus")int subscribeStatus,@PathVariable("page")int page,@PathVariable("size")int size) {
		PageBean pageBean = orderService.selectOrderBySubStatus(subscribeStatus,page,size);
		return pageBean;
	}
	/*
	 * 修改订单状态
	 */
	@PutMapping
	@ResponseBody
	public String updateOrderStatus(OrderBean orderBean){
		int num = orderService.updateOrderAttr(orderBean);
		return "ok";
	}
	/*
	 * 通过时间段查询订单
	 */
	@GetMapping("/time/{startTime}/{endTime}/{page}/{size}")
	@ResponseBody
	public PageBean selectOrderByTime(@PathVariable("startTime")String startTime, @PathVariable("endTime")String endTime, @PathVariable("page")int page, @PathVariable("size")int size){
		PageBean pageBean = orderService.selectOrderByTime(startTime, endTime, page, size);
		return pageBean;
	}
	
	
}
