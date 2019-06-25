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
//import com.project.util.countUtil.OrderUtil;

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
//	@Autowired
//	private OrderUtil orderutil;
	
	/*
	 * 前台预定同时生成订单(线下预定时通过mname查询mid，添加mid)
	 */
	@PostMapping
	@ResponseBody
	public String getOrder(OrderBean orderBean) {
		Subject subject = SecurityUtils.getSubject();
	    Session session = subject.getSession(false);
//	    session.setAttribute("id", 7);//测试使用
	    String id = null;
	    if (session!=null) {
	    	int mid = (int) session.getAttribute("id");
	        MemberBean memberBean = new MemberBean();
	        memberBean.setId(mid);
	        orderBean.setMember(memberBean);
			OrderBean orderBean2 = orderService.getOrder(orderBean);
			int oid = orderBean2.getId();
			id = String.valueOf(oid);
		}else {
			OrderBean orderBean2 = orderService.getOrder(orderBean);
			int oid = orderBean2.getId();
			id = String.valueOf(oid);
		}
        
		return id;
	}


	/*
	 * 后台按照预定人员信息(姓名和入住时间)模糊查询订单
	 */
	@GetMapping("/attr")
	public String selectOrderByAttr(LiveBean liveBean,ModelMap map) {
		List<OrderBean> list = orderService.selectOrderByAttr(liveBean.getPeople(), liveBean.getInTime());
		System.out.println(list);
		map.put("list", list);
		return "admin/findOrder.html";
	}
	/*
	 * 前台通过状态分页查询订单
	 */
	@GetMapping("/state/{status}")
	public String selectOrderByState(@PathVariable("status")int status,ModelMap map) {
		Subject subject = SecurityUtils.getSubject();
	    Session session = subject.getSession();
	    session.setAttribute("id", 1);//测试使用
        int mid = (int) session.getAttribute("id");
		PageBean bean = orderService.selectOrderByState(mid, status, 1, 50);
		System.out.println(bean);
		map.put("bean",bean);
		
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
		System.out.println(orderBean);
		int num = orderService.updateOrderAttr(orderBean);
		return "ok";
	}
	/*
	 * 通过时间段查询订单
	 */
	@GetMapping("/time/{subscribeStatus}/{startTime}/{endTime}/{page}/{size}")
	@ResponseBody
	public PageBean selectOrderByTime(@PathVariable("subscribeStatus")int subscribeStatus,@PathVariable("startTime")String startTime, @PathVariable("endTime")String endTime,@PathVariable("page")int page,@PathVariable("size")int size){
		
		PageBean pageBean = orderService.selectOrderByTime(subscribeStatus,startTime, endTime, page, size);
		return pageBean;
	}
	/*
	 * 通过订单id查询订单(统计价格)
	 */
	@GetMapping("/{oid}")
	public String selectOrderById(@PathVariable("oid")int oid,ModelMap map){
		OrderBean orderBean = orderService.selectOrderById(oid);
		List<LiveBean> list = orderBean.getLives();
		for (LiveBean liveBean : list) {
			String outTimeString = liveBean.getOutTime();
			String inTimeString = liveBean.getInTime();
			
			//截取日期
			String otime = outTimeString.substring(outTimeString.length()-2,outTimeString.length());
			int ot =Integer.parseInt(otime);
			String intime = inTimeString.substring(inTimeString.length()-2,inTimeString.length());
			int it =Integer.parseInt(intime);
			int day = ot - it;
			
			//截取月份
			String omouth =outTimeString.substring(outTimeString.length()-4,outTimeString.length()-3);
			String inmouth =inTimeString.substring(inTimeString.length()-4,inTimeString.length()-3);
			int om =Integer.parseInt(omouth);
			int im = Integer.parseInt(inmouth);
			if ((om-im)>0) {
				day = day+(om-im)*30;
			}
			double price = liveBean.getRoom().getPrice()*day;
			orderBean.setPrice(price);
			
			OrderBean orderBean2 = new OrderBean();
			orderBean2.setId(orderBean.getId());
			orderBean2.setPrice(price);
			orderService.updateOrderAttr(orderBean2);
		}
		map.put("orderBean", orderBean);
		return "pay.html";
	}
	
	
	/*
	 * 通过订单id查询订单(后台统计人员)
	 */
	@GetMapping("/people/{oid}")
	public String selectById(@PathVariable("oid")int oid,ModelMap map){
		OrderBean orderBean = orderService.selectOrderById(oid);
		map.put("orderBean", orderBean);
		return "admin/people.html";
	}
	
}
