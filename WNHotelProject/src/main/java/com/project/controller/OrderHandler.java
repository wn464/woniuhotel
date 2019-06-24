package com.project.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.domain.Member;
import com.project.Service.IOrderService;
import com.project.bean.LiveBean;
import com.project.bean.MemberBean;
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
	 * 前台预定同时生成订单(线下预定时通过mname查询mid，添加mid)
	 */
	@PostMapping
	@ResponseBody
	public String getOrder(OrderBean orderBean) {
		Subject subject = SecurityUtils.getSubject();
	    Session session = subject.getSession();
	    session.setAttribute("id", 7);//测试使用
        int mid = (int) session.getAttribute("id");
        MemberBean memberBean = new MemberBean();
        memberBean.setId(mid);
        orderBean.setMember(memberBean);
		OrderBean orderBean2 = orderService.getOrder(orderBean);
		int oid = orderBean2.getId();
		String id = String.valueOf(oid);
		return id;
	}


	/*
	 * 后台按照预定人员信息(姓名和入住时间)模糊查询订单
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

	@ResponseBody

	public String selectOrderByState(@PathVariable("status")int status) {

	    Subject subject = SecurityUtils.getSubject();
	    Session session = subject.getSession();
	    session.setAttribute("id", 1);//测试使用
        int mid = (int) session.getAttribute("id");
		PageBean pageBean = orderService.selectOrderByState(mid, status, 1, 2);
		System.out.println(pageBean);
		return "myorder.html";

	}
	/*
	 * 后台通过状态分页查询订单
	 */
	@GetMapping("/subStatus/{subscribeStatus}")
	@ResponseBody
	public PageBean selectOrderBySubStatus(@PathVariable("subscribeStatus")int subscribeStatus) {
		PageBean pageBean = orderService.selectOrderBySubStatus(subscribeStatus,1,2);
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
	@GetMapping("/time/{startTime}/{endTime}")
	@ResponseBody
	public PageBean selectOrderByTime(@PathVariable("startTime")String startTime, @PathVariable("endTime")String endTime){
		PageBean pageBean = orderService.selectOrderByTime(startTime, endTime, 1, 2);
		return pageBean;
	}
	/*
	 * 通过订单id查询订单
	 */
	@GetMapping("/{oid}")
	public String selectOrderById(@PathVariable("oid")int oid,ModelMap map){
		OrderBean orderBean = orderService.selectOrderById(oid);
		map.put("orderBean", orderBean);
		return "pay.html";
	}
	
}
