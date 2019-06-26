package com.project.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.IMemberService;
import com.project.bean.MemberBean;
import com.project.shiro.LoginToken;
import com.project.shiro.LoginType;

@Controller
public class MemberHandler {
	
	@Autowired
	private IMemberService service;
	
	
	/*
	 * 注册
	 */
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
	
	
	/*
	 * 登录
	 */
	@PostMapping("/member/login")
	@ResponseBody
	public String login(ModelMap map, @Validated MemberBean member,BindingResult result) {
		System.out.println(member);
		if(result.hasErrors()) {
			System.out.println("----------出现错误----------");
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getDefaultMessage());
			}
			return "1";
		}else {
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isAuthenticated()) {
				UsernamePasswordToken token = new LoginToken(member.getUserName(),member.getPassword(),LoginType.user.toString());

				try {
					subject.login(token);
					System.out.println("认证成功");
					//把 id 和 用户名存到session  后续用的到
					MemberBean bean = service.selectByUsername(member.getUserName());
					
					Session session = subject.getSession();
					session.setAttribute("id", bean.getId());
					session.setAttribute("userName", bean.getUserName());
					
					return "2";
				}catch (Exception e) {
					System.out.println("认证失败");
					return "1";
				}
			}
			
			
		}
		return null;
	}
	
	//跳转页面
	@GetMapping("/member/jump")
	@ResponseBody
	public MemberBean jump() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String userName = (String) session.getAttribute("userName");
		MemberBean bean = service.selectByUsername(userName);
		return bean;
	}
	
	/*
	 * 通过手机号查询所有会员信息
	 */
	@GetMapping("/member/PhoneNumber")
	@ResponseBody
	public MemberBean selectAll(String phoneNumber) {
		System.out.println(phoneNumber);
		MemberBean bean = service.selectByPhoneNumber(phoneNumber);
		System.out.println("===="+bean);
		return bean;
		
	}
	/*
	 * 查看个人信息
	 */
	@GetMapping("/user/selectById")
	@ResponseBody
	public MemberBean selectById() {
		Subject subject = SecurityUtils.getSubject();
		int id = (int) subject.getSession().getAttribute("id");//获取当前登录的id
		MemberBean bean = service.selectById(id);
		return bean;
		
	}
	
	/*
	 * 修改密码
	 */
	@PutMapping("/user/update")
	@ResponseBody
	public String updataPassword(String password,String repassword) {
		Subject subject = SecurityUtils.getSubject();
		String userName =  (String) subject.getSession().getAttribute("userName");	//获取当前登录的用户名
		
		//将输入的原密码加密
		Object obj = new SimpleHash("MD5",password,userName,1024);
		
		MemberBean member = service.selectByUsername(userName);
		
		if(member.getPassword().equals(obj.toString())) {
			service.updatePassword(repassword, member.getId());		//如果旧密码匹配 执行修改密码sql
			return "1";					//1是修改成功
		}
		return "2";						//2是修改失败
		
	}
}
