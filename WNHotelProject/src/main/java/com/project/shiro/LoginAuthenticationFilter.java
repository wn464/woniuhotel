package com.project.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;


public class LoginAuthenticationFilter extends FormAuthenticationFilter {
	//动态跳转登录网页
@Override
protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
	HttpServletRequest req = (HttpServletRequest) request;
    String loginUrl = getLoginUrl();
    String url = req.getRequestURI();
    if (url.contains("/admin/")) {
        loginUrl = "/admin/index.html";
    }
    WebUtils.issueRedirect(request, response, loginUrl);
}
	
}
