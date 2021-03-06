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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.IUserService;
import com.project.bean.MemberBean;
import com.project.bean.UserBean;
import com.project.shiro.LoginToken;
import com.project.shiro.LoginType;

@Controller
public class UserHandler {
	
	@Autowired
	private IUserService service;
	@GetMapping("/admin/muen")
	public String muen() {
		return "/admin/muen.html";
	}
	
	/*
	 * 登录
	 */
	@PostMapping("/user/login")
	@ResponseBody
	public String login(ModelMap map, @Validated UserBean user,BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("----------出现错误----------");
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getDefaultMessage());
			}
			return "1";
		}else {
			Subject subject = SecurityUtils.getSubject();
			System.out.println(user);
			UsernamePasswordToken token = new LoginToken(user.getUsername(),user.getPassword(),LoginType.admin.toString());
			
			if(!subject.isAuthenticated()) {
				subject.logout();
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
			}else {
				//多角色登录
				subject.login(token);
			}
			
			
		}
		return "2";
	}
	
			
			/*
			 * 添加操作员
			 */
			@PostMapping("/user/reg")
			@ResponseBody
			public String reg(ModelMap map,@Validated UserBean user,BindingResult result) {
				System.out.println(user);
				if(result.hasErrors()) {
					System.out.println("----------出现错误----------");
					List<FieldError> list =result.getFieldErrors();
					for (FieldError error : list) {
						System.out.println(error.getDefaultMessage());
					}
					return "1";
				}else {
					//判断是否存在这个用户 
					UserBean bean = service.selectByUserName(user.getUsername());
					if(bean==null) {

						Object obj = new SimpleHash("MD5",user.getPassword(),user.getUsername(),1024);		//盐值
						
						UserBean user1 = new UserBean();												//创建member对象 然后封装
						user1.setUsername(user.getUsername());
						user1.setPassword(obj.toString());
						user1.setRole(user.getRole());
						int reg = service.reg(user1);
			
						return "2"; // 注册成功  跳转注册成功页面
					}else {
						return "1";
					}
					
				}		
				
			}
			
			//验证用户名是否存在
			@GetMapping("/user/yz")
			@ResponseBody
			public String selectUserName(String username) {
				UserBean user= service.selectByUserName(username);
				if(user!=null) {
					return "1";
				}
				return "2";
			}
			
			/*
			 * 查看所有操作员
			 */
			@GetMapping("/user/selectAll")
			@ResponseBody
			public List<UserBean> selectAll(){
				List<UserBean> selectAll = service.selectAll();
				System.out.println(selectAll);
				return selectAll;
			}
			
			
			/*
			 * 修改管理员
			 */
			@RequestMapping("/user/updateRole")
			@ResponseBody
			public int updateRole(UserBean user) {
				System.out.println(user);
				int updateRole = service.updateRole(user);
				
				return updateRole;
				
			}
}
