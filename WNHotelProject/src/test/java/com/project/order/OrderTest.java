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
import com.project.bean.VipBean;
import com.project.dao.ILiveDao;
import com.project.dao.IMarkDao;
import com.project.dao.IMemberDao;
import com.project.dao.IOrderDao;
import com.project.dao.IVipDao;
import com.project.demo.WnHotelProjectApplication;
import com.project.util.CreateOrderInfo;
import com.project.util.WebSocketUtil;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = WnHotelProjectApplication.class)
public class OrderTest {
	    @Autowired
	    private IOrderService orderService;
	    @Autowired
	    private IOrderDao orderDao;
	    @Autowired
	    private IMarkDao markdao;
	    @Autowired
	    private ILiveService liveService;
	    @Autowired
	    private IMemberDao member;
	    @Autowired
	    private IVipDao vip;
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
	    public void selectmember(){
	    	MemberBean memberBean = member.selectById(7);
//	    	VipBean vipBean = vip.selectVipById(1);
	    	System.out.println(memberBean);
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
	    }
	    @Test
	    public void selectOrderByTime(){
//	    	List<OrderBean> list = orderDao.selectOrderByTime(startTime, endTime, page, size)
//	    for (OrderBean orderBean : list) {
//			System.err.println(orderBean);
//		}
			
	    }
	    @Test
	    public void updatelivebean(){
	    	LiveBean liveBean = new LiveBean();
	    	liveBean.setId(46);
	    	liveBean.setPhoneNumber("111111");
	    	liveService.updateLiveBean(liveBean);
	    	
	    }
	    @Test

	    public void deleteById() {
	    	int i = orderService.deleteById(169);
	    	System.out.println(i);
	    }
	    @Test
	    public void selectMonth(){
	    	List<Double> list = orderService.selectOrderByMonth(2019,6,9);
//	    	List<Double> list = orderDao.selectOrderByMonth("2019-06-28 00:00:00" , "2019-06-29 00:00:00");
	    	System.out.println(list);
	    	

	    }
	    @Test
	   public void name() {
		   WebSocketUtil.sendMessageAll("200");
	   }


}