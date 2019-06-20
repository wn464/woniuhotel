package com.project.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Service.IMemberService;
import com.project.bean.MemberBean;
import com.project.util.Msg;
import com.project.util.Validation;
/*
 *会员   handler 
 */
@RestController
public class MemberHandler {
	
	@Autowired
	private IMemberService service;
	
	/*
	 * 注册
	 */
	@PostMapping("/reg")
	public Msg reg(ModelMap map,@Validated MemberBean bean,BindingResult result) {
		
			List<?> validation = Validation.getValidation(result);		//调用Validation 工具类
			if(validation.size()!=0) {
				 return Msg.failed();									
			}else {
				boolean boo = service.selectByUsername(bean.getUserName());			//查询是否有着用户
				
				if(boo) {
					Object obj = new SimpleHash("MD5",bean.getPassword(),bean.getUserName(),1024);
					MemberBean member = new MemberBean();
					member.setUserName(bean.getUserName());
					member.setPassword(obj.toString());
					
					int reg = service.reg(member);									//注册方法
					
					return Msg.success();											//返回 工具类msg的success方法
				}else {
					return Msg.failed();											//如果有这个用户 返回失败方法
				}
				
			}
			
			
			
	
		
	};
	
	/*
	 * 登录
	 */
	@PostMapping("/login")
	public String login(Model model,ModelMap map,@Validated MemberBean member,BindingResult result) {
		if(result.hasErrors()) {									//不输入账号 密码长度不对 等错误
			System.out.println("----------登录出现错误----------");
			List<FieldError> list = result.getFieldErrors();
			for (FieldError fieldError : list) {
				map.put("error_"+fieldError.getField(), fieldError.getDefaultMessage());
			}
			
			return "login.html";
		}else {
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(member.getUserName(),member.getPassword());
				try {
					subject.login(token);
					System.out.println("认证成功");
					return null; 				//登录成功跳转的地址
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("认证失败");
					return "redirect:/html/login.html";
				}
			}
		}
		return "html/index.html";	
		
	}
	
	
	
}
