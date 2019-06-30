package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.weaver.ast.Var;
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
import com.project.Service.IDiscountService;
import com.project.Service.IOrderService;
import com.project.Service.IVipService;
import com.project.Service.impl.VipServiceImpl;
import com.project.bean.DiscountBean;
import com.project.bean.LiveBean;
import com.project.bean.MarkBean;
import com.project.bean.MemberBean;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;
import com.project.bean.VipBean;
import com.project.util.countUtil.OrderUtil;
//import com.project.util.countUtil.OrderUtil;
import com.project.util.timingutil.ordertiming;

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
	@Autowired
	private IVipService vipService;
	@Autowired
	private IDiscountService discountService;
	
	
	/*
	 * 前台预定同时生成订单（线上散客、会员下单）
	 */
	@PostMapping
	@ResponseBody
	public String getOrder(OrderBean orderBean) {
		Subject subject = SecurityUtils.getSubject();
	    Session session = subject.getSession(false);
//	    session.setAttribute("id", 7);//测试使用
	    System.out.println("-----"+session);
	    String id = null;
    	if (session.getAttribute("id")!=null) {
    		int mid = (int) session.getAttribute("id");
	        MemberBean memberBean = new MemberBean();
	        memberBean.setId(mid);
	        orderBean.setMember(memberBean);
			OrderBean orderBean2 = orderService.getOrder(orderBean);
			int oid = orderBean2.getId();
			id = String.valueOf(oid);
		}
		else {
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
		System.out.println("+++++++++++++++++++---__-----"+list);
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
        int mid = (int) session.getAttribute("id");
		PageBean bean = orderService.selectOrderByState(mid, status, 1, 50);
		
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
	@GetMapping("/time")
	public String selectOrderByTime(int subscribeStatus,String startTime,String endTime,ModelMap map){
		PageBean pageBean = orderService.selectOrderByTime(subscribeStatus,startTime, endTime, 1, 4);
		map.put("bean", pageBean);
		
		return "admin/orderSet.html";
	}
	/*
	 * 前台通过订单id查询订单(统计价格)
	 */
	@GetMapping("/{oid}")
	public String selectOrderById(@PathVariable("oid")int oid,ModelMap map) throws Exception{
		OrderUtil orderutil = new OrderUtil(vipService, discountService);
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
//前台会员下单---------
		if (orderBean.getMember()!=null) {
			//有账号且会员
			System.out.println("//会员下单");
			if (orderBean.getMember().getVipBean()!=null) {
				MemberBean memberBean = orderBean.getMember();
				VipBean vipBean = memberBean.getVipBean();
				double price = orderutil.getOnLineMoney(orderBean.getPrice(), vipBean.getId());
				price = Math.floor(price);
				orderBean.setPrice(price);
				//修改数据库价格
				OrderBean order1 = new OrderBean();
				order1.setId(oid);
				order1.setPrice(price);
				orderService.updateOrderAttr(order1);
				//查询优惠信息
				List<DiscountBean> dislist = orderutil.getOnLineDiscount(orderBean.getPrice(), vipBean.getId());
				System.out.println("--------"+dislist);
				map.put("list", dislist);
				//查询修改后的订单
				OrderBean order = orderService.selectOrderById(oid);
				map.put("orderBean", order);
				System.out.println("修改价格后-=-=-=-----------------------------"+order);
				return "pay.html";
			}
			//有账号非会员
			else {
				System.out.println("//有账号非会员");
				double price = orderutil.getUnderLineMoney(orderBean.getPrice(), 0);
				price = Math.floor(price);
				orderBean.setPrice(price);
				//修改数据库价格
				OrderBean order1 = new OrderBean();
				order1.setId(oid);
				order1.setPrice(price);
				orderService.updateOrderAttr(order1);
				//查询优惠信息
				List<DiscountBean> dislist = orderutil.getOnLineDiscount(orderBean.getPrice(),0);
				System.out.println("--------"+dislist);
				map.put("list", dislist);
				
				//查询修改后的订单
				OrderBean order = orderService.selectOrderById(oid);
				map.put("orderBean", order);
				System.out.println("-=-=-=-----------------------------"+order);
				return "pay.html";
			}
		}
//非会员下单---------------------
		else {
			System.out.println("//非会员下单");
			double price = orderutil.getOnLineMoney(orderBean.getPrice(), 0);
			price = Math.floor(price);
			orderBean.setPrice(price);
			//修改数据库价格
			OrderBean order1 = new OrderBean();
			order1.setId(oid);
			order1.setPrice(price);
			orderService.updateOrderAttr(order1);
			//查询优惠信息
			List<DiscountBean> dislist = orderutil.getOnLineDiscount(orderBean.getPrice(),0);
			System.out.println("--------"+dislist);
			map.put("list", dislist);
			
			//查询修改后的订单
			OrderBean order = orderService.selectOrderById(oid);
			map.put("orderBean", order);
			System.out.println("-=-=-=-----------------------------"+order);
			return "pay.html";
		}
		
	}

//--------------------------------后台操作-------------------------------------------------
	
	/*
	 * 通过订单id查询订单(后台添加人员)
	 */
	@GetMapping("/people/{oid}")
	public String selectById(@PathVariable("oid")int oid,ModelMap map){
		//修改预定状态
		OrderBean orderBean2 = new OrderBean();
		orderBean2.setId(oid);
		MarkBean subscribeStatus = new MarkBean();
		subscribeStatus.setId(8);
		orderBean2.setSubscribeStatus(subscribeStatus);
		orderService.updateOrderAttr(orderBean2);
		//修改支付状态
		OrderBean orderBean3 = new OrderBean();
		orderBean3.setId(oid);
		MarkBean staBean = new MarkBean();
		staBean.setId(6);
		orderBean3.setStatus(staBean);
		orderService.updateOrderAttr(orderBean3);
		OrderBean orderBean = orderService.selectOrderById(oid);
		map.put("orderBean", orderBean);
		return "admin/people.html";
	}
	
	
	/*
	 * 线下散客、会员开单
	 */
	@PostMapping("/after")
	@ResponseBody
	public String getOrder1(OrderBean orderBean) {
	    String id = null;
		OrderBean orderBean2 = orderService.getOrder(orderBean);
		int oid = orderBean2.getId();
		id = String.valueOf(oid);
		return id;
	}
	
	/*
	 * 通过订单id查询订单(统计价格)
	 */
	
	@GetMapping("/after/{oid}/{flag}")
	public String pay(@PathVariable("oid")int oid,@PathVariable("flag") int flag,ModelMap map) throws Exception{
		OrderUtil orderutil = new OrderUtil(vipService, discountService);
		//查询订单
		OrderBean orderBean = orderService.selectOrderById(oid);
		//计算日期
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
//会员下单---------
		if (orderBean.getMember()!=null) {
			//有账号且会员
			if (orderBean.getMember().getVipBean()!=null) {
				MemberBean memberBean = orderBean.getMember();
				VipBean vipBean = memberBean.getVipBean();
				double price = orderutil.getUnderLineMoney(orderBean.getPrice(), vipBean.getId());
				price = Math.floor(price);
				orderBean.setPrice(price);
				//修改数据库价格
				OrderBean order1 = new OrderBean();
				order1.setId(oid);
				order1.setPrice(price);
				orderService.updateOrderAttr(order1);
				//查询优惠信息
				List<DiscountBean> dislist = orderutil.getUnderLineDiscount(orderBean.getPrice(),vipBean.getId());
				System.out.println("--------"+dislist);
				map.put("list", dislist);
				//设置订单预定状态
				if (flag==1) {
					OrderBean orderBean2 = new OrderBean();
					MarkBean suBean = new MarkBean();
					suBean.setId(9);
					orderBean2.setSubscribeStatus(suBean);
					orderBean2.setId(orderBean.getId());
					orderService.updateOrderAttr(orderBean2);
				}else {
					OrderBean orderBean2 = new OrderBean();
					MarkBean suBean = new MarkBean();
					suBean.setId(8);
					orderBean2.setSubscribeStatus(suBean);
					orderBean2.setId(orderBean.getId());
					orderService.updateOrderAttr(orderBean2);
				}
				//查询修改后的订单
				OrderBean order = orderService.selectOrderById(oid);
				map.put("orderBean", order);
				return "admin/count1.html";
			}
			//有账号非会员
			else {
				double price = orderutil.getUnderLineMoney(orderBean.getPrice(), 0);
				price = Math.floor(price);
				orderBean.setPrice(price);
				//修改数据库价格
				OrderBean order1 = new OrderBean();
				order1.setId(oid);
				order1.setPrice(price);
				orderService.updateOrderAttr(order1);
				//查询优惠信息
				List<DiscountBean> dislist = orderutil.getUnderLineDiscount(orderBean.getPrice(),0);
				System.out.println("--------"+dislist);
				map.put("list", dislist);
				//设置订单预定状态
				if (flag==1) {
					OrderBean orderBean2 = new OrderBean();
					MarkBean suBean = new MarkBean();
					suBean.setId(9);
					orderBean2.setSubscribeStatus(suBean);
					orderBean2.setId(orderBean.getId());
					orderService.updateOrderAttr(orderBean2);
				}else {
					OrderBean orderBean2 = new OrderBean();
					MarkBean suBean = new MarkBean();
					suBean.setId(8);
					orderBean2.setSubscribeStatus(suBean);
					orderBean2.setId(orderBean.getId());
					orderService.updateOrderAttr(orderBean2);
				}
				//查询修改后的订单
				OrderBean order = orderService.selectOrderById(oid);
				map.put("orderBean", order);
				return "admin/count1.html";
			}
		}
//非会员下单---------------------
		else {
			double price = orderutil.getUnderLineMoney(orderBean.getPrice(), 0);
			System.out.println("________________"+price);
			price = Math.floor(price);
			orderBean.setPrice(price);
			//修改数据库价格
			OrderBean order1 = new OrderBean();
			order1.setId(oid);
			order1.setPrice(price);
			orderService.updateOrderAttr(order1);
			//查询优惠信息
			List<DiscountBean> dislist = orderutil.getUnderLineDiscount(orderBean.getPrice(),0);
			System.out.println("--------"+dislist);
			map.put("list", dislist);
			//设置订单预定状态
			if (flag==1) {
				OrderBean orderBean2 = new OrderBean();
				MarkBean suBean = new MarkBean();
				suBean.setId(9);
				orderBean2.setSubscribeStatus(suBean);
				orderBean2.setId(orderBean.getId());
				orderService.updateOrderAttr(orderBean2);
			}else {
				OrderBean orderBean2 = new OrderBean();
				MarkBean suBean = new MarkBean();
				suBean.setId(8);
				orderBean2.setSubscribeStatus(suBean);
				orderBean2.setId(orderBean.getId());
				orderService.updateOrderAttr(orderBean2);
			}
			//查询修改后的订单
			OrderBean order = orderService.selectOrderById(oid);
			System.out.println("+++++++"+order);
			map.put("orderBean", order);
			return "admin/count1.html";
		}
	}
	//按id查看推送消息
	@GetMapping("/web/{oid}")
	public String selcetById(@PathVariable("oid")int oid,ModelMap map) {
		OrderBean orderBean= orderService.selectOrderById(oid);
		System.out.println(orderBean);
		map.put("orderBean", orderBean);
		return "admin/appointment.html";
	}
	//查看所有推送消息
	@GetMapping("/web")
	public String selcetById(ModelMap map) {
		List<OrderBean> list= orderService.selectOrderBySub(9);
		System.out.println("---------"+list);
		for (OrderBean orderBean : list) {
			System.out.println("-=--=-=----"+orderBean);
		}
		map.put("list", list);
		return "admin/appointment2.html";
	}
	//按时间段统计订单
	@GetMapping("/month")
	@ResponseBody
	public double[] selcetOrderByMonth(int year,int smonth,int emonth,ModelMap map) {
		double m[] = new double[12];
		List<Double> list = new ArrayList<Double>();
		list = orderService.selectOrderByMonth(year, smonth, emonth);
		int a = 0;
		for (int i = smonth-1; i < emonth; i++) {
			m[i] = list.get(a);
			a++;
			System.out.println("---------"+m[i]);
		}	
		return m;
	}
	//按年份查询收入
	@GetMapping("/year")
	@ResponseBody
	public double selcetOrderByYear(int year,ModelMap map) {
		List<Double> list = orderService.selectOrderByMonth(year, 1, 12);
		System.out.println(list);
		double yprice = 0;
		for (Double double1 : list) {
			yprice+=double1;
		}
		return yprice;
	}
	//按月份查询收入
	@GetMapping("/mth")
	@ResponseBody
	public double selcetOrderByDate(Integer year,Integer month,ModelMap map) {
		List<Double> list = orderService.selectOrderByMonth(year, month, month);
		double mprice = 0;
		for (Double double1 : list) {
			mprice+=double1;
		}
		return mprice;
	}
	//按日期查询收入
	@GetMapping("/dates")
	@ResponseBody
	public double selcetOrderByDate(String date) {
		System.out.println(date);
		List<Double> list = orderService.selcetOrderByDate(date);
		double dprice = 0;
		for (Double double1 : list) {
			dprice+=double1;
		}
		return dprice;
	}
		
	
	
}
