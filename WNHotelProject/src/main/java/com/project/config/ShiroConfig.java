package com.project.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.shiro.LoginAuthenticationFilter;
import com.project.shiro.LoginAuthenticator;
import com.project.shiro.MemberRealm;
import com.project.shiro.UserLogoutFilter;
import com.project.shiro.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

//添加配置注解
@Configuration
public class ShiroConfig {

	@Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
	//生成shiro过滤器
	@Bean(name="shiroFilterFactoryBean")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		//注入安全管理器
		shiroFilter.setSecurityManager(securityManager);
		//认证失败跳转地址
		shiroFilter.setLoginUrl("/login");
		//认证失败跳转
		Map<String, Filter> filters = shiroFilter.getFilters();
		 // 将自定义的FormAuthenticationFilter注入shiroFilter中
        filters.put("authc", new LoginAuthenticationFilter());
        // 将自定义的LogoutFilter注入shiroFilter中
        filters.put("logout", new UserLogoutFilter());
		shiroFilter.setUnauthorizedUrl("/failed.html");
		
		Map<String,String> fmap = new LinkedHashMap<String,String>();
		
		//后台登录
		fmap.put("/admin/login.html","anon");
		fmap.put("/user/login","anon");
		
		//js css img
		fmap.put("/admin/js/**", "anon");
		fmap.put("/admin/css/**", "anon");
		fmap.put("/admin/data/**", "anon");
		fmap.put("/admin/fonts/**", "anon");
		fmap.put("/admin/icons-reference/**", "anon");
		fmap.put("/admin/img/**", "anon");
		fmap.put("/admin/vendor/**", "anon");
		fmap.put("/js/**", "anon");
		fmap.put("/css/**", "anon");
		fmap.put("/images/**", "anon");
		
		//前台	没写全 把自己的东西加进去
		fmap.put("/index.html", "anon");
		fmap.put("/public.html","anon");
		fmap.put("/member/login", "anon");
		fmap.put("/login.html","anon");
		fmap.put("/member/reg", "anon");
		fmap.put("/reg.html","anon");
		fmap.put("/map.html","anon");
		fmap.put("/ground.html","anon");
		fmap.put("/comment.html","anon");
		
		
		//登出
		fmap.put("/logout","logout");
		fmap.put("/admin/login","logout");
		
		fmap.put("/**", "anon");

		
		shiroFilter.setFilterChainDefinitionMap(fmap);
		return shiroFilter;
		
	}
	
	//生成安全管理器,注入realm
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("myReaml") Realm realm,@Qualifier("myReam2") Realm realm2 ) {
		DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
		defaultSecurityManager.setAuthenticator(new LoginAuthenticator());
		List<Realm> realms = new ArrayList<>();
        //添加多个Realm
		realms.add(realm);
		realms.add(realm2);
		defaultSecurityManager.setRealms(realms);
		return defaultSecurityManager;
	}
	//生成Realm
	//可以注入加密
	@Bean(name = "myReaml")
	public Realm getRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
		MemberRealm realm = new MemberRealm();
		realm.setCredentialsMatcher(hashedCredentialsMatcher);
		return realm;
	}
	@Bean(name = "myReam2")
	public Realm getRealm1(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
		UserRealm realm2 = new UserRealm();
		realm2.setCredentialsMatcher(hashedCredentialsMatcher);
		return realm2;
	}
	/**
	 * 使用md5来进行密码加密
	 * @return
	 */
	@Bean("hashedCredentialsMatcher")
	public HashedCredentialsMatcher getHashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1024);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}
}
