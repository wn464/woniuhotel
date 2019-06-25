package com.project.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.Service.IMemberService;
import com.project.Service.IRoleService;
import com.project.Service.IUserService;
import com.project.bean.MemberBean;
import com.project.bean.RoleBean;
import com.project.bean.UserBean;


public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private IRoleService service1;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取用户名
		Object username = principals.getPrimaryPrincipal();
		Set<String> set = new HashSet<String>();
		
		//获取数据库中的角色
		UserBean user = service.selectByUserName((String)username);
		RoleBean bean = service1.findRoleById(user.getId());
		
		if ("superAdmin".equals(bean.getRole())) {
			set.add("superAdmin");
		}else if ("admin".equals(bean.getRole())) {
			set.add("admin");
		}
		info.addRoles(set);
		return info;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = token.getPrincipal().toString();				//获取用户名
		UserBean bean = service.selectByUserName(username);
		SimpleAuthenticationInfo info =null;
		if(bean!=null) {
			ByteSource bytes = ByteSource.Util.bytes(username);
			info = new SimpleAuthenticationInfo(bean.getUsername(),bean.getPassword(),bytes,getName());
		}
		return info;
	}
	

}
