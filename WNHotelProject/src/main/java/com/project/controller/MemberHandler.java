  package com.project.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
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

import com.alipay.api.domain.SenderInfoVO;
import com.project.Service.IMemberService;
import com.project.bean.MemberBean;
import com.project.shiro.LoginToken;
import com.project.shiro.LoginType;
import com.project.util.CodeUtil;
import com.project.util.JuHeDemo;

@Controller
public class MemberHandler {
	private static StringBuffer  generateCode = null;
	@Autowired
	private IMemberService service;
	
	
	
	/*
	 * 发送短信
	 */
	@GetMapping("/member/send")
	@ResponseBody
	public  boolean Send(String phone) {
		generateCode = CodeUtil.generateCode();
		System.out.println("--------"+generateCode.toString());
		boolean mobileQuery = JuHeDemo.mobileQuery(phone, 169209, generateCode);
		return mobileQuery;
	}
	
	
	
	/*
	 * 注册
	 */
	@PostMapping("/member/reg")
	public String reg(ModelMap map,@Validated MemberBean member,String code,BindingResult result) {
		
		System.out.println(code);
		System.out.println(member);
		if(result.hasErrors()) {
			System.out.println("----------出现错误----------");
			List<FieldError> list =result.getFieldErrors();
			for (FieldError error : list) {
				System.out.println(error.getDefaultMessage());
			}
			return "redirect:/reg.html";
		}else {
			
			if (code.equals(generateCode.toString())) {
				System.out.println("----------------------"+code);
				System.out.println(generateCode);
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
			}else {
				return "redirect:/reg.html"; // 注册成功  跳转注册成功页面
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
	public String login(ServletRequest request,ServletResponse response,ModelMap map, @Validated MemberBean member,BindingResult result) {
		
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
			//subject.logout();
			
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
					SavedRequest savedRequest = (SavedRequest) subject.getSession(false).getAttribute("shiroSavedRequest");
					
					String url = null;
					if(savedRequest!=null) {
						 url= savedRequest.getRequestURI();
						System.out.println("RequestURI+++++++++++++++"+url);
						if(url!=null) {
							//WebUtils.issueRedirect(request, response, url);
							return url;
							//return "1";	
						}
					}
					
				}catch (Exception e) {
					System.out.println("认证失败");
					return "1";
				}
			}
			
			
		}
		return "2";
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
		MemberBean bean =null;
		int id = (int) subject.getSession().getAttribute("id");//获取当前登录的id
		System.out.println("======="+id);
		bean = service.selectById(id);
		System.out.println(bean);
		subject.getSession().setAttribute("bean", bean);
		return bean;
		
		
	}
	
	/*
	 * 修改密码
	 */
	@PutMapping("/user/update")
	@ResponseBody
	public String updataPassword(String password, String repassword) {
		System.out.println(repassword.length());
		if(repassword.length()<6||repassword.length()>12) {
			return "3";						//3前台输入是密码长度不合格
		}else {
			Subject subject = SecurityUtils.getSubject();
			String userName =  (String) subject.getSession().getAttribute("userName");	//获取当前登录的用户名

			MemberBean member = service.selectByUsername(userName);
			
			//将输入的原密码加密
			Object obj = new SimpleHash("MD5",password,userName,1024);
			
			if(member.getPassword().equals(obj.toString())) {
				//将输入的新密码加密
				Object obj1 = new SimpleHash("MD5",repassword,userName,1024);
				int num = service.updatePassword(obj1.toString(), member.getId());		//如果旧密码匹配 执行修改密码sql
				
				return "1";					//1是修改成功
			}
			return "2";						//2是修改失败	
		}
		
	}
}
