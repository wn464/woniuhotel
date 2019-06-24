package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.IPeopleService;
import com.project.bean.MarkBean;
import com.project.bean.PeopleBean;

@Controller
public class PeopleHandler {
	@Autowired
	private IPeopleService service;
	@RequestMapping("/name")
	@ResponseBody
	public List<PeopleBean> selectPeopleBean(String name) {
		List<PeopleBean> list = service.selectPeopleBean(name);
		return list;
	}
	@RequestMapping("/addPeople")
	@ResponseBody
	public int insertPeopleBean(String name,String idCard,int gende,int id) {
		int i = service.insertPeopleBean(name, idCard, gende, id);
		return i;
	}

}
