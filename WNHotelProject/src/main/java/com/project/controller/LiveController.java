package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.ILiveService;
import com.project.bean.LiveBean;

@Controller
@RequestMapping("live")
public class LiveController {
	@Autowired
	private ILiveService liveService;
	
	/*
	 * 修改入住信息
	 */
	@PutMapping
	@ResponseBody
	public String updateLiveBeanByAttr(LiveBean liveBean){
		int num = liveService.updateLiveBean(liveBean);
		return "ok";
	}
}
