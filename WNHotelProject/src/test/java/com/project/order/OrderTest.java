package com.project.order;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.project.Service.IOrderService;
import com.project.Service.IRoomService;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;
import com.project.bean.RoomBean;
import com.project.dao.IMarkDao;
import com.project.demo.WnHotelProjectApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WnHotelProjectApplication.class)
public class OrderTest {
	    @Autowired
	    private IOrderService orderService;
	    @Autowired
	    private IMarkDao markdao;
	    @Test
	    public void insertTest(){
	    OrderBean orderBean = new OrderBean();
	    orderBean.setOrderTime("2019-06-20 13:55:15");
	    	int num = orderService.insertOrder(orderBean);
	    	
	    	System.out.println("----"+num);
	    }
	    @Test
	    public void selectByAttr(){
	    	List<OrderBean> list = orderService.selectOrderByAttr("小王", "2019-06-20");
	    }
	    @Test
	    public void selectByState(){
	    	PageBean pageBean = orderService.selectOrderByState(1, 6,1, 1);
	    }
	   


}