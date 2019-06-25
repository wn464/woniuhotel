package com.project.controller;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.IUserService;
import com.project.bean.MemberBean;
import com.project.bean.UserBean;

@Controller
public class UserHandler {
	
	@Autowired
	private IUserService service;
	
	
	/*
	 * 登录
	 */
	@PostMapping("/user/login")
	public String login(ModelMap map, @Validated UserBean user,BindingResult result) {
		System.out.println(user);
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
				UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
			
				try {
					subject.login(token);
					System.out.println("认证成功");
					//把 id 和 用户名存到session  后续用的到
					UserBean bean = service.selectByUserName(user.getUsername());
					
					Session session = subject.getSession();
					session.setAttribute("id", bean.getId());
					session.setAttribute("userName", bean.getUsername());
					
					return "2";
				}catch (Exception e) {
					System.out.println("认证失败");
					return "1";
				}
			}
			
			
		}
		return null;
	}
	
			
			/*
			 * 注册
			 */
			@PostMapping("/user/reg")
			public String reg(ModelMap map,@Validated UserBean user,BindingResult result) {
				if(result.hasErrors()) {
					System.out.println("----------出现错误----------");
					List<FieldError> list =result.getFieldErrors();
					for (FieldError error : list) {
						System.out.println(error.getDefaultMessage());
					}
					return "redirect:/reg.html";
				}else {
					//判断是否存在这个用户 
					UserBean bean = service.selectByUserName(user.getUsername());
					if(bean==null) {
						
						
						Object obj = new SimpleHash("MD5",bean.getPassword(),bean.getUsername(),1024);		//盐值
						
						UserBean user1 = new UserBean();												//创建member对象 然后封装
						user1.setUsername(bean.getUsername());
						user1.setPassword(obj.toString());
						int reg = service.reg(user1);
						
						
						return "redirect:/admin/login.html"; // 注册成功  跳转注册成功页面
					}else {
						return "redirect:/reg.html";
					}
					
				}		
				
			}
			//验证用户名是否存在
			@GetMapping("/user/yz")
			@ResponseBody
			public String selectUserName(String userName) {
				UserBean user= service.selectByUserName(userName);
				if(user!=null) {
					return "1";
				}
				return "2";
			}
			

}
