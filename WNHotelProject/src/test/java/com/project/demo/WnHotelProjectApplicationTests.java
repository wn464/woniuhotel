package com.project.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.Service.IRoomService;
import com.project.bean.RoomBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WnHotelProjectApplicationTests {

	@Autowired
	IRoomService roomservcie;
	@Test
	public void contextLoads() {

	
	}

}
