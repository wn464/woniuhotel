package com.project.util.timingutil;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Service.IOrderService;
import com.project.bean.MarkBean;
import com.project.bean.OrderBean;

public class task extends TimerTask {
	@Autowired
 private IOrderService service;
	private int orderid;
	
	public task(IOrderService service,int ordernumber) {
		System.out.println("开始计时。15分钟后修改订单状态");
		this.orderid=ordernumber;
		this.service=service;
	}
	@Override
	public void run() {
		OrderBean oder=service.selectOrderById(orderid);
		/**
		 * 判断订单是否已支付
		 */
		if(oder.getStatus().getId()==6) {
			
		}else {
			/**
			 * 如果未支付修改订单为已超时
			 */
			OrderBean order=new OrderBean();
			order.setId(orderid);
			MarkBean status=new MarkBean();
			status.setId(7);
			order.setStatus(status);
			service.updateOrderAttr(order);
		}
	}

}
