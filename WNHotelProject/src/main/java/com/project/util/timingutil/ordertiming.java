package com.project.util.timingutil;




import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Service.IOrderService;




/**
 * 订单定时器
 * @author zxc
 *
 */
public class ordertiming{
	
public static void ds(int number ,IOrderService service){
	Timer timer=new Timer();
	timer.schedule(new task(service ,number),1000*60*15);
}
	 
}
