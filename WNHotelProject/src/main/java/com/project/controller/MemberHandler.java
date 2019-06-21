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
	public Msg reg(@Validated MemberBean bean,BindingResult result) {
			List<?> validation = Validation.getValidation(result);		//调用Validation 工具类
			if(validation.size()!=0) {											//返回msg 工具类 的failed方法
				 return Msg.failed();									
			}else {
				  MemberBean selectByUsername = service.selectByUsername(bean.getUserName());			//查询是否有着用户
				
				if(selectByUsername==null) {
					Object obj = new SimpleHash("MD5",bean.getPassword(),bean.getUserName(),1024);
					MemberBean member = new MemberBean();
					member.setUserName(bean.getUserName());
					member.setPassword(obj.toString());
					
					int reg = service.reg(member);									//注册方法
					System.out.println("注册成功！！！");
					return Msg.success();											//返回 工具类msg的success方法
				}else {
					
					System.out.println("----------出现错误----------");
					System.out.println("已存在用户 注册失败！！！");
					return Msg.failed();											//如果有这个用户 返回失败方法
				}
				
			}
	
	};
	
	/*
	 * 登录
	 */
	@PostMapping("/login")
	public Msg login(@Validated MemberBean member,BindingResult result) {
		List<?> validation = Validation.getValidation(result);						//调用Validation 工具类
		if(validation.size()!=0) {
			 return Msg.failed();													//返回msg 工具类 的failed方法
		}else {
			
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(member.getUserName(),member.getPassword());
				try {
					subject.login(token);
					System.out.println("认证成功");
					return	Msg.success();
				} catch (Exception e) {
					System.out.println("认证失败");
					return Msg.failed();
				}
			}
		}
		return Msg.success();	
	}
	
	/*
	 * 修改密码
	 */
	public Msg updatePassword(int id) {
		
		return null;
		
	}
	
	/*
	 * 查看个人信息
	 */
	
}
