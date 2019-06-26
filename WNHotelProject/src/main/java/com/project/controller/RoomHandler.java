package com.project.controller;



import java.util.List;

import org.apache.catalina.security.SecurityUtil;
import org.apache.ibatis.annotations.Update;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
      RoomBean bean = service.selectroombyid(rid);
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
		Subject scb=SecurityUtils.getSubject();
		
		int dl=0;
		if(scb.getSession(false)!=null) {
			System.out.println("------==========");
			if(scb.getSession(false).getAttribute("id")!=null) {
				dl=1;
			}
		};
		map.addAttribute("dl",dl);
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
	@GetMapping(value="/time/{tid}/{inTime}/{outTime}/{page}/{size}")
	public String selectroombytypeantime(@PathVariable("tid")int tid, @PathVariable("inTime")String inTime, @PathVariable("outTime")String outTime, @PathVariable("page")int page, @PathVariable("size")int size,ModelMap map) {
		System.out.println("类型"+tid);
		System.out.println("入住时间"+inTime);
		System.out.println(("离店时间"+outTime));
		TypeBean type=new TypeBean();
		type.setId(tid);
		PageBean bean=service.selectroombytypeantime(type, inTime, outTime, page, size);
		System.out.println("分页："+bean);
		List<RoomBean> list = (List<RoomBean>) bean.getList();
		for (RoomBean roombean : list) {
			String img =roombean.getImg();
			roombean.setImg("/images/"+img);
		}
		map.put("room",bean);
		map.put("tid",tid);
		map.put("inTime",inTime);
		map.put("outTime",outTime);
		map.put("page",page);
		map.put("size",size);
		return "findaa.html";
	}
	@GetMapping(value="/timea/{tid}/{inTime}/{outTime}/{page}/{size}")
	@ResponseBody
	public PageBean selectroombytypeantime2(@PathVariable("tid")int tid, @PathVariable("inTime")String inTime, @PathVariable("outTime")String outTime, @PathVariable("page")int page, @PathVariable("size")int size,ModelMap map) {
//		System.out.println(tid);
//		System.out.println(inTime);
//		System.out.println((outTime));
		TypeBean type=new TypeBean();
		type.setId(tid);
		PageBean bean=service.selectroombytypeantime(type, inTime, outTime, page, size);	
		return bean;
	}
	
	/**
	 * 点击入住跳转预约界面
	 * @param rid房间id
	 * @return
	 */
	@GetMapping(value="/rooms/{rid}/{inTime}/{outTime}")
	public String selectroombyid1(@PathVariable("rid")Integer rid,@PathVariable("inTime")String inTime,@PathVariable("outTime")String outTime,ModelMap map) {
      RoomBean bean = service.selectroombyid(rid);
      map.put("roomBean", bean);
      map.put("inTime",inTime);
      map.put("outTime", outTime);
      return "admin/subscribe.html";
	}
	/**
	 * 修改房间信息
	 * @param room
	 * @return
	 */
	@PutMapping(value="/updateroom")
	@ResponseBody
	public boolean updateroom(RoomBean room) {
		boolean boo=service.updateroom(room);
		return boo;
	}
	@PostMapping(value="/insertroom")
	@ResponseBody
	public boolean insertroom(RoomBean room) {
		boolean boo=service.insertroom(room);
		return boo;
	}
}
