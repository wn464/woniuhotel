package com.project.shiro;

import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

public class UserLogoutFilter extends LogoutFilter {
	private String checkUrl="/admin/";
	private String logouturl="/admin/login.html";

    public String getCheckUrl() {
		return checkUrl;
	}

	public void setCheckUrl(String checkUrl) {
		this.checkUrl = checkUrl;
	}

	public String getLogouturl() {
		return logouturl;
	}

	public void setLogouturl(String logouturl) {
		this.logouturl = logouturl;
	}

	@Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = this.getSubject(request, response);
        
       
        if (this.isPostOnlyLogout() && !WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
            return this.onLogoutRequestNotAPost(request, response);
        } else {
            try {
                subject.logout();
            } catch (SessionException var6) {
            	var6.printStackTrace();
            }
            HttpServletRequest req = (HttpServletRequest) request;
            
            String url = req.getRequestURI();
            System.out.println("url:"+url);
            String redirectUrl = "/index.html";
            if (url.contains(checkUrl)) {
            	redirectUrl = logouturl;
            }
            this.issueRedirect(request, response, redirectUrl);
            return false;
        }
    }

}
