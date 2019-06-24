package com.project.controller;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.IMemberService;
import com.project.bean.MemberBean;

@Controller
public class MemberHandler {
	
	@Autowired
	private IMemberService service;
	
	@PostMapping("/member/reg")
	public String reg(ModelMap map,@Validated MemberBean member,BindingResult result) {
		System.out.println(member);
		if(result.hasErrors()) {
			System.out.println("----------出现错误----------");
			List<FieldError> list =result.getFieldErrors();
			for (FieldError error : list) {
				System.out.println(error.getDefaultMessage());
			}
			return "redirect:/reg.html";
		}else {
			//判断是否存在这个用户 
			MemberBean bean = service.selectByUsername(member.getUserName());
			if(bean==null) {
				
				
				Object obj = new SimpleHash("MD5",member.getPassword(),member.getUserName(),1024);		//盐值
				MemberBean memb = new MemberBean();												//创建member对象 然后封装
				memb.setUserName(member.getUserName());
				memb.setPassword(obj.toString());
				memb.setPhoneNumber(member.getPhoneNumber());
				int reg = service.reg(memb);
				
				
				return "redirect:/login.html"; // 注册成功  跳转注册成功页面
			}else {
				return "redirect:/reg.html";
			}
			
		}		
		
	}
	
	//验证用户名是否存在
	@GetMapping("/member/yz")
	@ResponseBody
	public String selectUserName(String userName) {
		MemberBean member= service.selectByUsername(userName);
		if(member!=null) {
			return "1";
			
		}
		return "2";
		
	}
	
	
}
