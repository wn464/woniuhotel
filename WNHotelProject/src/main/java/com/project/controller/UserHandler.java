package com.project.controller;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Service.IUserService;
import com.project.bean.UserBean;

@Controller
public class UserHandler {
	
	@Autowired
	private IUserService service;
	@RequestMapping("/login")
	public String login(String username,String password,BindingResult result) {
		List<FieldError> list = result.getFieldErrors();
		for (FieldError fieldError : list) {
			System.out.println(fieldError.getDefaultMessage());
		}
		//产生一个用户（门面对象）
				Subject currentUser = SecurityUtils.getSubject();
				
				 if (!currentUser.isAuthenticated()) {
			            UsernamePasswordToken token = new UsernamePasswordToken();
			            try {
			            	//调用login进行认证
			                currentUser.login(token);
			                System.out.println("认证成功");
			                return "redirect:/index.html";
			            } 
			            //认证失败异常
			            catch (AuthenticationException ae) {
			                //unexpected condition?  error?
			            	System.out.println("认证失败");
			            	return "redirect:/login.html";
			            }
			      }
				 return "redirect:/index.html";
			}
	
	
			@RequestMapping("/reg")
			public String reg(String username,String password,BindingResult result){
				List<FieldError> list = result.getFieldErrors();
				for (FieldError fieldError : list) {
					System.out.println(fieldError.getDefaultMessage());
				}
				Object obj = new SimpleHash("MD5", password, username, 1024);
				UserBean user = new UserBean();
				user.setUsername(username);
				user.setPassword(obj.toString());
				service.reg(user);
				
				return "redirect:/login.html";
			}

}
