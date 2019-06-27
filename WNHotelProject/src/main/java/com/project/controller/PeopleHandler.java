package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.IOrderService;
import com.project.Service.IPeopleService;
import com.project.bean.MarkBean;
import com.project.bean.OrderBean;
import com.project.bean.PeopleBean;

@Controller
public class PeopleHandler {
	@Autowired
	private IPeopleService service;
	@Autowired
	private IOrderService orderService;
	@RequestMapping("/name")
	@ResponseBody
	public List<PeopleBean> selectPeopleBean(String name) {
		List<PeopleBean> list = service.selectPeopleBean(name);
		return list;
	}
	@RequestMapping("/person")
	@ResponseBody
	public String insertPeopleBean(String name,String idCard,int gende,int id) {
		System.out.println(id);
		System.out.println(gende);
		int i = service.insertPeopleBean(name, idCard, gende, id);
		OrderBean orderBean = new OrderBean();
		orderBean.setId(id);
		MarkBean subsBean = new MarkBean();
		subsBean.setId(8);
		orderBean.setSubscribeStatus(subsBean);
		orderService.updateOrderAttr(orderBean);
		return "1";
	}
	
	/*
	 * 修改用户信息
	 */
	@PutMapping("/people/update")
	@ResponseBody
	public String update(PeopleBean people) {
		
		int update = service.update(people);
		if(update!=0) {
			return "1";				//1是修改成功
		}	
		return "2";					//2是修改失败
	}

}
