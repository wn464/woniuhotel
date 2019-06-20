package com.project.room;

import org.junit.Test;

import com.project.Service.IRoomService;
import com.project.Service.impl.RoomServiceImpl;
import com.project.bean.PageBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;

public class RoomTest {
	IRoomService ro = new RoomServiceImpl();
	@Test
	public void test1() {		
		RoomBean bean = ro.selectroombyid(1);
		System.out.println(bean);
	}
	@Test
	public void test2() {
		TypeBean bean1 = new TypeBean();
		bean1.setId(1);
		PageBean page = ro.selectroombytype(bean1, 0, 1);
		System.out.println(page);
	}

}
