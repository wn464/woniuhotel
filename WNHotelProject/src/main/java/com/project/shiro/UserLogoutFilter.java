package com.project.shiro;

import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

public class UserLogoutFilter extends LogoutFilter {
	 

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
            String redirectUrl = "/unauth";
            this.issueRedirect(request, response, redirectUrl);
            return false;
        }
    }

}
