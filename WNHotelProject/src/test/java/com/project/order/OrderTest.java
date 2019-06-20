package com.project.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.project.Service.IRoomService;
import com.project.bean.RoomBean;
import com.project.demo.WnHotelProjectApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WnHotelProjectApplication.class)
public class OrderTest {
	    @Autowired
	    private IRoomService ro;
       
	    @Test
	    public void insertTest(){
	    	RoomBean room = ro.selectroombyid(1);
	    	System.out.println(room);
	    }
	    @Test
	    public void test1() {
	    	
	    }


}