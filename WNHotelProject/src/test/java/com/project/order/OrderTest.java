package com.project.order;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

import com.alipay.api.domain.Member;
import com.project.Service.ILiveService;
import com.project.Service.IOrderService;
import com.project.Service.IRoomService;
import com.project.bean.LiveBean;
import com.project.bean.MarkBean;
import com.project.bean.MemberBean;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;
import com.project.bean.RoomBean;
import com.project.dao.ILiveDao;
import com.project.dao.IMarkDao;
import com.project.dao.IOrderDao;
import com.project.demo.WnHotelProjectApplication;
import com.project.util.CreateOrderInfo;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WnHotelProjectApplication.class)
public class OrderTest {
	    @Autowired
	    private IOrderService orderService;
	    @Autowired
	    private IOrderDao orderDao;
	    @Autowired
	    private IMarkDao markdao;
	    @Test
	    public void insertTest(){
	    OrderBean orderBean = new OrderBean();
	    LiveBean liveBean = new LiveBean();
	    liveBean.setInTime("2019-06-20");
	    liveBean.setOutTime("2019-06-21");
	    liveBean.setRoomid(1);
	    liveBean.setPhoneNumber("131444231212");
	    liveBean.setPeople("小张1");
	    List<LiveBean> lives = new ArrayList<LiveBean>();
	    lives.add(liveBean);
	    orderBean.setLives(lives);
	    
	    MarkBean statusBean = new MarkBean();
	    statusBean.setId(6);
	    orderBean.setStatus(statusBean);
	    
	    orderBean.setOrderTime("2019-06-20 21:08:13");
	    orderBean.setPrice(100);
	    MemberBean memberBean = new MemberBean();
	    memberBean.setId(1);
	    orderBean.setMember(memberBean);
	    
	    MarkBean subscriBean = new MarkBean();
	    subscriBean.setId(9);
	    orderBean.setSubscribeStatus(subscriBean);
	    
	    orderBean.setDelState(9);
	    OrderBean num = orderService.getOrder(orderBean);
	    System.out.println("----"+num);
	    }
	    @Test
	    public void selectByAttr(){
	    	List<OrderBean> list = orderService.selectOrderByAttr("小王", "2019-06-20");
	    	for (OrderBean orderBean : list) {
				System.out.println("---===----"+orderBean);
			}
	    }
	    @Test
	    public void selectByState(){
	    	PageBean pageBean = orderService.selectOrderByState(1, 6,1, 2);
	    	System.out.println("---------"+pageBean);
	    }
	    
	    @Test
	    public void updateByAttr(){
	    	OrderBean orderBean = new OrderBean();
	    	orderBean.setId(15);
//	    	 MarkBean statusBean = new MarkBean();
//	 	    statusBean.setId(7);
//	 	    orderBean.setStatus(statusBean);
//	    	MarkBean subscriBean = new MarkBean();
//		    subscriBean.setId(9);
//		    orderBean.setSubscribeStatus(subscriBean);
//	    	orderBean.setAlipayNumber("20190621102119950");
//	    	orderBean.setPayMoney(21.0);
	    	orderBean.setPrice(25);
	 	    System.out.println(orderBean);
	    	orderService.updateOrderAttr(orderBean);
	    }
	    @Test
	    public void selectOrderByOrderNumber(){
	    	OrderBean orderBean = orderDao.selectOrderByOrderNumber("20190621173745416");
	    	System.out.println(orderBean);
	    }
	    @Test
	    public void selectOrderById(){
	    	OrderBean orderBean = orderDao.selectOrderById(28);
	    	System.out.println(orderBean);
	    }
	    @Test
	    public void selectOrderByTime(){
//	    	List<OrderBean> list = orderDao.selectOrderByTime(startTime, endTime, page, size)
//	    for (OrderBean orderBean : list) {
//			System.err.println(orderBean);
//		}
			
	    }
	    
	    
	   


}