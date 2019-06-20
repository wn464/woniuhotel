package com.project.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Service.IUserService;
import com.project.bean.MemberBean;
import com.project.bean.UserBean;
import com.project.util.Msg;
import com.project.util.Validation;
/*
 * user controller接口
 */
@RestController
public class UserHandler {
	@Autowired
	private IUserService service;
	
	/*
	 * 注册
	 */
	@PostMapping("/userReg")
	public Msg reg(@Validated UserBean bean,BindingResult result) {
			List<?> validation = Validation.getValidation(result);		//调用Validation 工具类
			if(validation.size()!=0) {											//返回msg 工具类 的failed方法
				 return Msg.failed();									
			}else {
				  UserBean login = service.login(bean.getUsername());			//查询是否有着用户
				
				if(login==null) {
					Object obj = new SimpleHash("MD5",bean.getPassword(),bean.getUsername(),1024);
					UserBean user = new UserBean();
					user.setUsername(bean.getUsername());
					user.setPassword(obj.toString());
					
					int reg = service.reg(user);									//注册方法
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
	@PostMapping("/userLogin")
	public Msg login(@Validated UserBean bean,BindingResult result) {
		List<?> validation = Validation.getValidation(result);						//调用Validation 工具类
		if(validation.size()!=0) {
			 return Msg.failed();													//返回msg 工具类 的failed方法
		}else {
			
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(bean.getUsername(),bean.getPassword());
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
}
