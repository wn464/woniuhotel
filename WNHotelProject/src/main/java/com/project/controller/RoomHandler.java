package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.IRoomService;
import com.project.bean.MarkBean;
import com.project.bean.PageBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;

@Controller
public class RoomHandler {
	@Autowired
	private IRoomService service;
	@RequestMapping("/room")
	@ResponseBody
	public RoomBean selectroombyid(int rid) {
      RoomBean bean = service.selectroombyid(rid);
      return bean;
	}
	@RequestMapping("/type")
	@ResponseBody
	public PageBean selectroombytype(TypeBean type, int page, int size) {
		PageBean bean = service.selectroombytype(type, page, size);
		return bean;
	}
	@RequestMapping("/status")
	@ResponseBody
	public PageBean selectroombystatus(MarkBean status, int page, int size) {
		PageBean bean = service.selectroombystatus(status, page, size);
		return bean;
	}
	@RequestMapping("/typestatus")
	@ResponseBody
	public PageBean selectroombytypeandstatus(TypeBean type, MarkBean status, int page, int size) {
		PageBean bean = service.selectroombytypeandstatus(type, status, page, size);
		return bean;
	}
}
