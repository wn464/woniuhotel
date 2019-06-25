package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.IVipService;
import com.project.bean.VipBean;

@Controller
public class VipHandler {

	@Autowired
	private IVipService vipService;
	//查询所有
	@GetMapping("/admin/vip")
	@ResponseBody
	public List<VipBean> selectAll(){
		return vipService.selectVipAll();
	}
	//按id查询
	@GetMapping("/admin/vip/{id}")
	@ResponseBody
	public VipBean selectById(@PathVariable int id ) {
		return vipService.selectVipById(id);
	}
	//删除
	@DeleteMapping("/admin/vip")
	@ResponseBody
	public int deleteById(int id) {
		return vipService.deletVip(id);
	}
	//添加
	@PostMapping("/admin/vip")
	@ResponseBody
	public int insert(VipBean vip) {
		return vipService.insertVip(vip);
	}
	//修改
	@PutMapping("/admin/vip")
	@ResponseBody
	public int update(VipBean vip) {
		return vipService.updateVip(vip);
	}
}
