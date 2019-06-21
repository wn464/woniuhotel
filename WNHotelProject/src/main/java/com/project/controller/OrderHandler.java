package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RestController
@RequestMapping("/order")
public class OrderHandler {
	@Autowired
	private IOrderService orderService;
	
	/*
	 * 添加并生成订单
	 */
	@PostMapping
	public OrderBean getOrder(OrderBean orderBean) {
		OrderBean orderBean2 = orderService.getOrder(orderBean);
		return orderBean2;
	}
	/*
	 * 后台查询预定订单
	 */
	@GetMapping(value = "/{name}/{time}")
	public List<OrderBean> selectOrderByAttr(@PathVariable("name")String name,@PathVariable("time")String time) {
		System.out.println(name);
		System.out.println(time);
		List<OrderBean> list = orderService.selectOrderByAttr(name, time);
		return list;
	}
	/*
	 * 前台和后台通过订单状态分页查询订单
	 */
	@GetMapping(value = "/{mid}/{status}/{page}/{size}")
	public String selectOrderByState(@PathVariable("mid")int mid, @PathVariable("status")int status,@PathVariable("page")int page,@PathVariable("size")int size) {
		PageBean pageBean = orderService.selectOrderByState(mid, status, page, size);
		return "ok";
	}
	/*
	 * 修改订单状态
	 */
	@PutMapping
	public String  updateOrderStatus(OrderBean orderBean){
		int num = orderService.updateOrderAttr(orderBean);
		return "ok";
	}
	
}
