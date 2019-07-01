package com.project.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.filter.CustomRolesAuthorizationFilter;
import com.project.shiro.LoginAuthenticationFilter;
import com.project.shiro.LoginAuthenticator;
import com.project.shiro.MemberRealm;
import com.project.shiro.OneOfRoleAuthorzationFilter;
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
		shiroFilter.setLoginUrl("/login.html");
		
		//认证失败跳转
		Map<String, Filter> filters = shiroFilter.getFilters();
		
		 // 将自定义的FormAuthenticationFilter注入shiroFilter中
        filters.put("authc", new LoginAuthenticationFilter());
        // 将自定义的LogoutFilter注入shiroFilter中
        filters.put("logout", new UserLogoutFilter());
        
        filters.put("oneOfRole", new OneOfRoleAuthorzationFilter());
        //使用 "/**"=oneOfRole[admin,superadmin]
        
		shiroFilter.setUnauthorizedUrl("/login.html");
		
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
		fmap.put("/upload/**", "anon");
		//登出
		fmap.put("/logout","logout");
		
		fmap.put("/admin/login","logout");
		fmap.put("/order/state/*", "authc");
		//只有管理员才能访问后台
		fmap.put("/admin/**", "oneOfRole[admin,superadmin,boss]");
		//前台
		fmap.put("/**", "anon");
		
		
		shiroFilter.setFilterChainDefinitionMap(fmap);
		return shiroFilter;
		
	}


	
	//生成安全管理器,注入realm
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("myReaml") Realm realm,@Qualifier("myReam2") Realm realm2) {
		DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
		defaultSecurityManager.setAuthenticator(new LoginAuthenticator());
		List<Realm> realms = new ArrayList<>();
        //添加多个Realm
		realms.add(realm);
		realms.add(realm2);
		defaultSecurityManager.setRealms(realms);
		
//		defaultSecurityManager.setRememberMeManager(rememberMeManager());
		return defaultSecurityManager;
	}
	
	
	public CustomRolesAuthorizationFilter getcustomRolesAuthorizationFilter() {
		CustomRolesAuthorizationFilter customRolesAuthorizationFilter = new CustomRolesAuthorizationFilter();
		return customRolesAuthorizationFilter;
		
	}

//	
//	//设置记住我
//	@Bean
//	public SimpleCookie rememberMeCookie() {
//		
//		SimpleCookie simpleCookie = new SimpleCookie();
//		//设置过期时间
//		simpleCookie.setMaxAge(3000);
//		simpleCookie.setPath("/");
//		simpleCookie.setName("rememberMe");
//		return simpleCookie;
//		
//	}
//	
//	@Bean  
//	public CookieRememberMeManager rememberMeManager(){  
//	      //System.out.println("ShiroConfiguration.rememberMeManager()");  
//	      CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();  
//	      cookieRememberMeManager.setCookie(rememberMeCookie());  
//	      //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)  
//	      cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));  
//	      return cookieRememberMeManager;  
//	} 
//	
//	
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
