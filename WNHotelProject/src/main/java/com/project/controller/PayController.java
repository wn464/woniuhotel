package com.project.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.project.Service.IOrderService;
import com.project.Service.IRoomService;
import com.project.bean.MarkBean;
import com.project.bean.OrderBean;
import com.project.util.AlipayUtil;

@Controller
public class PayController {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IRoomService roomService;
	//调用支付宝接口
	@GetMapping("/pay/{oid}")
	@ResponseBody
	public String pay(@PathVariable("oid")int oid) {
		OrderBean orderBean = orderService.selectOrderById(oid);
		String string = null;
		try {
			string = AlipayUtil.pay(orderBean.getOrderNumber(), orderBean.getPrice(), "支付");
			//修改支付状态
			OrderBean orderBean2 = new OrderBean();
			orderBean2.setId(oid);
			MarkBean statusBean = new MarkBean();
			statusBean.setId(6);
			orderBean2.setStatus(statusBean);
			orderService.updateOrderAttr(orderBean2);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return string;
	}
	//退款
	@GetMapping("/refund/{oid}")
	@ResponseBody
	public boolean refund(@PathVariable("oid")int oid) {
		OrderBean orderBean = orderService.selectOrderById(oid);
		boolean boo = false;
		try {
			boo =AlipayUtil.refund(orderBean.getOrderNumber(), orderBean.getPrice());
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boo;
	}
	//响应回调
	@RequestMapping("/ret")
	public  String ret(HttpServletRequest request) {
		String[] num = null;
		try {
			num = AlipayUtil.returnUrl(request);
			System.out.println(num[1]);
			OrderBean orderBean = orderService.selectOrderByOrderNumber(num[0]);
			//修改支付宝号
			OrderBean orderBean2 = new OrderBean();
			orderBean2.setId(orderBean.getId());
			orderBean2.setAlipayNumber(num[1]);
			orderService.updateOrderAttr(orderBean2);
			//修改订单状态
			OrderBean orderBean3 = new OrderBean();
			orderBean3.setId(orderBean.getId());
			MarkBean statusBean = new MarkBean();
			statusBean.setId(6);
			orderBean3.setStatus(statusBean);
			orderService.updateOrderAttr(orderBean3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/index.html";
	}
}
