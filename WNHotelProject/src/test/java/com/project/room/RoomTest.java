package com.project.room;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.Service.IRoomService;
import com.project.bean.MarkBean;
import com.project.bean.PageBean;
import com.project.bean.TypeBean;
import com.project.demo.WnHotelProjectApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WnHotelProjectApplication.class)
public class RoomTest {
	    @Autowired
	    private IRoomService ro;
       
	    @Test
	    public void insertTest(){
	    	TypeBean t=new TypeBean();
	    	t.setId(1);
	    	MarkBean m=new MarkBean();
	    	m.setId(3);
	    	PageBean page=ro.selectroombytypeandstatus(t, m,0, 1);
	    	System.out.println(page);
	    }
	    @Test
	    public void test1() {
	    	
	    }


}
