package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Service.IOrderService;
import com.project.bean.OrderBean;

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
	 * 预定添加订单
	 */
//	@PostMapping
//	public String insertOrder(OrderBean orderBean) {
//		orderService.insertOrder(orderBean);
//		return "ok";
//	}
//	/*
//	 * 后台查询未入住和入住订单
//	 */
//	@
//	public String insertOrder(OrderBean orderBean) {
//		orderService.selectOrderByAttr(people, time);
//		return "ok";
//	}
//	/*
//	 * 预定添加订单
//	 */
//	@PostMapping
//	public String insertOrder(OrderBean orderBean) {
//		orderService.insertOrder(orderBean);
//		return "ok";
//	}
	
}
