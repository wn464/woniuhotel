package com.project.shiro;

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
import com.project.bean.MemberBean;

public class MemberRealm extends AuthorizingRealm{

	@Autowired
	private IMemberService service;
	
	//授权管理
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}

	//认证管理
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username = token.getPrincipal().toString();				//获取用户名
		MemberBean bean = service.login(username);
		SimpleAuthenticationInfo info =null;
		if(bean!=null) {
			ByteSource bytes = ByteSource.Util.bytes(username);
			
			info = new SimpleAuthenticationInfo(bean.getUserName(),bean.getPassword(),bytes,getName());
		}
		return info;
	}
}
