package com.project.util.timingutil;




import java.util.Timer;




/**
 * 订单定时器
 * @author zxc
 *
 */
public class ordertiming{
public static void ds(int number){
	Timer timer=new Timer();
	timer.schedule(new task(number),1000*60*15);
}
	 
}
