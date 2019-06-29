package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.project.Service.IMemberService;
import com.project.Service.IOrderService;
import com.project.Service.IRoomService;
import com.project.bean.LiveBean;
import com.project.bean.MarkBean;
import com.project.bean.OrderBean;
import com.project.bean.RoomBean;
import com.project.util.AlipayUtil;
import com.project.util.WebSocketUtil;

@Controller
public class PayController {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IRoomService roomService;
	@Autowired
	private IMemberService memberService;
	//调用支付宝接口
	@GetMapping("/pay/{oid}")
	@ResponseBody
	public String pay(@PathVariable("oid")int oid) {
		
		OrderBean orderBean = orderService.selectOrderById(oid);
		String string = null;
		try {
			int price = (int)Math.floor(orderBean.getPrice());
			string = AlipayUtil.pay(orderBean.getOrderNumber(),price,"支付");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return string;
	}
	
	//退款
	@GetMapping("/refund/{oid}")
	public String refund(@PathVariable("oid")int oid,ModelMap map) {
		OrderBean orderBean = orderService.selectOrderById(oid);
		System.out.println(orderBean.getPrice());
		boolean boo=false;
		String s="";
		try {
			
			System.out.println("--------------"+orderBean);
		 boo=AlipayUtil.refund(orderBean.getOrderNumber(), orderBean.getPrice());
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(boo);
		if(boo) {
			s="退款成功,款项已发回你支付宝账户！！！！！";
			OrderBean orderBean2 = new OrderBean();
			orderBean2.setId(oid);
			MarkBean status=new MarkBean();
			status.setId(13);
			orderBean2.setSubscribeStatus(status);
			orderService.updateOrderAttr(orderBean2);
		}else {
		s="退款失败，详情请联系卖家！！！！！";
		}
		map.addAttribute("tk", s);
		return "tuikuan.html";
	}
	//响应回调
	@RequestMapping("/ret")
	public  String ret(HttpServletRequest request) {
		System.out.println("------响应回调---------------");
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
			//修改预定状态
			OrderBean orderBean4 = new OrderBean();
			orderBean4.setId(orderBean.getId());
			MarkBean subBean = new MarkBean();
			subBean.setId(9);
			orderBean4.setSubscribeStatus(subBean);
			orderService.updateOrderAttr(orderBean4);
			//修改房间状态（不可住）
			RoomBean room = new RoomBean();
			List<LiveBean> list = orderBean.getLives();
			for (LiveBean liveBean : list) {
				room.setId(liveBean.getRoom().getId());
			}
			roomService.updateroomstatusin(room);
			//修改会员消费金额（提高会员等级）
			if (orderBean.getMember()!=null) {
				int i = memberService.updateMoney(orderBean.getPrice(), orderBean.getMember().getId());
			}
			
			
			//发送推送消息
			System.out.println("推送-------------------"+orderBean.getId());
			String id = String.valueOf(orderBean.getId());
			WebSocketUtil.sendMessageAll(id);
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/index.html";
	}
	
	//响应回调1
	@RequestMapping("/ret1")
	@ResponseBody
	public  String ret1(HttpServletRequest request) {
		System.out.println("------响应回调1---------------");
		return null;
	}
}
