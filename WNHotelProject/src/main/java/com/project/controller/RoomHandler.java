package com.project.controller;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.Service.IRoomService;
import com.project.bean.MarkBean;
import com.project.bean.PageBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;




/***
 * 房间后端处理器
 * @author zxc
 *
 */
@Controller
public class RoomHandler {

	@Autowired
	private IRoomService service;

	/**
	 * 根据房间id查询房间详细信息
	 * @param rid房间id
	 * @return
	 */
	@GetMapping(value="/room/{rid}")
	public String selectroombyid(@PathVariable("rid")Integer rid,ModelMap map) {

		System.out.println(rid);
      RoomBean bean = service.selectroombyid(rid);
      System.out.println("1111"+bean);
      map.put("roomBean", bean);
      return "order.html";
	}
	/**
	 * 查询某一类型下所有房间
	 * @param tid房间类型id
	 * @param page当前页数
	 * @param size每页显示条数
	 * @return
	 */
	@GetMapping(value="/room/type/{tid}/{page}/{size}")
	public String selectroombytype(@PathVariable("tid")int tid,@PathVariable("page") int page,@PathVariable("size") int size,ModelMap map) {
		TypeBean type=new TypeBean();
		type.setId(tid);
		PageBean bean = service.selectroombytype(type, page, size);
		map.addAttribute("page", bean);
		return "room.html";
	}
	/**
	 * 查询所有可住房间
	 * @param page当前页数
	 * @param size每页显示条数
	 * @return
	 */
	@GetMapping(value="/status")
	@ResponseBody
	public PageBean selectroombystatus(int page, int size) {
		MarkBean status=new MarkBean();
		status.setId(3);
		PageBean bean = service.selectroombystatus(status, page, size);
		return bean;
	}
	/**
	 * 查询某一类型下可住房间
	 * @param ti'd房间类型id
	 * @param page当前页数
	 * @param size每页显示条数
	 * @return
	 */
	@GetMapping(value="/typestatus/{tid}/{page}/{size}")
	@ResponseBody
	public PageBean selectroombytypeandstatus(@PathVariable("tid")int tid,@PathVariable("page") int page,@PathVariable("size") int size) {
		TypeBean type=new TypeBean();
		type.setId(tid);
		MarkBean status=new MarkBean();
		status.setId(3);
		PageBean bean = service.selectroombytypeandstatus(type, status, page, size);
		return bean;
	}
	/**
	 * 
	 * @param tid
	 * @param inTime
	 * @param outTime
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/time")
	@ResponseBody
	public PageBean selectroombytypeantime(int tid, String inTime, String outTime, int page, int size) {
		TypeBean type=new TypeBean();
		type.setId(tid);
		PageBean bean=service.selectroombytypeantime(type, inTime, outTime, page, size);
		
		return bean;
	}
}
