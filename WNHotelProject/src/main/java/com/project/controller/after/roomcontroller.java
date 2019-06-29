package com.project.controller.after;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.IRoomService;
import com.project.bean.MarkBean;
import com.project.bean.PageBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;
import com.project.controller.myexcption;

@Controller
public class roomcontroller extends myexcption {

	@Autowired
	private IRoomService service;

	/**
	 * 根据房间id查询房间详细信息
	 * @param rid房间id
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/afterroom/{rid}")
	public String selectroombyid(@PathVariable("rid")Integer rid,ModelMap map) throws Exception {
      RoomBean bean = service.selectroombyid(rid);
      map.put("roomBean", bean);
      System.out.println(bean);
      return "order.html";
	}
	/**
	 * 根据房间id查询房间详细信息
	 * @param rid房间id
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/admin/room/{rid}")
	@ResponseBody
	public RoomBean selectroom(@PathVariable("rid")Integer rid,ModelMap map) throws Exception {
      RoomBean bean = service.selectroombyid(rid);
      return bean;
	}
	/**
	 * 查询某一类型下所有房间
	 * @param tid房间类型id
	 * @param page当前页数
	 * @param size每页显示条数
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/afterroom/type/{tid}/{page}/{size}")
	@ResponseBody
	public PageBean selectroombytype(@PathVariable("tid")int tid,@PathVariable("page") int page,@PathVariable("size") int size,ModelMap map) throws Exception {
		TypeBean type=new TypeBean();
		type.setId(tid);
		PageBean bean = service.selectroombytype(type, page, size);
		return bean;
	}
	/**
	 * 查询所有可住房间
	 * @param page当前页数
	 * @param size每页显示条数
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/afterstatus/{page}/{size}")
	public String selectroombystatus(@PathVariable("page")int page,@PathVariable("size") int size,ModelMap map) throws Exception {
		MarkBean status=new MarkBean();
		status.setId(3);
		PageBean bean = service.selectroombystatus(status, page, size);
		map.addAttribute("page", bean);
		return "/admin/findRoom.html";
	}
	@GetMapping(value="/afterstatustwo/{page}/{size}")
	@ResponseBody
	public PageBean selectroombystatustwo(@PathVariable("page")int page,@PathVariable("size") int size) throws Exception {
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
	 * @throws Exception 
	 */
	@GetMapping(value="/aftertypestatus/{tid}/{page}/{size}")
	@ResponseBody
	public PageBean selectroombytypeandstatus(@PathVariable("tid")int tid,@PathVariable("page") int page,@PathVariable("size") int size) throws Exception {
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
	 * @throws Exception 
	 */
	@GetMapping(value="/aftertime/{tid}/{intime}/{outtime}/{page}/{size}")
	@ResponseBody
	public PageBean selectroombytypeantime(@PathVariable("tid")int tid,@PathVariable("intime") String inTime,@PathVariable("outtime") String outTime,@PathVariable("page") int page,@PathVariable("size") int size) throws Exception {
		TypeBean type=new TypeBean();
		type.setId(tid);
		PageBean bean=service.selectroombytypeantime(type, inTime, outTime, page, size);
		
		return bean;
	}
	/**
	 * 查询所有房间
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/afterroomall/{page}/{size}")
	public String selectroomall(@PathVariable("page")int page,@PathVariable("size")int size,ModelMap map) throws Exception {
		PageBean bean=service.selectroomall(page, size);
		map.addAttribute("page", bean);
		return "/admin/roomManager.html";
	}
	@GetMapping(value="/afterroomalltwo/{page}/{size}")
	@ResponseBody
	public PageBean selectroomalltwo(@PathVariable("page")int page,@PathVariable("size")int size) throws Exception {
		PageBean bean=service.selectroomall(page, size);
		return bean;
	}
}
