package com.project.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.shiro.MemberRealm;
import com.project.shiro.UserRealm;

//添加配置注解
@Configuration
public class ShiroConfig {

	//生成shiro过滤器
	@Bean(name="shiroFilterFactoryBean")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		//注入安全管理器
		shiroFilter.setSecurityManager(securityManager);

		//认证失败跳转地址
		shiroFilter.setLoginUrl("/login");

		//认证失败跳转
		shiroFilter.setUnauthorizedUrl("/failed.html");
		Map<String,String> fmap = new LinkedHashMap<String,String>();


		fmap.put("/member/login", "anon");
		fmap.put("/member/reg", "anon");
		fmap.put("/user/reg", "anon");
		fmap.put("/user/login", "anon");
		fmap.put("/js/**", "anon");
		fmap.put("/index.html", "anon");
		fmap.put("/css/**", "anon");
		fmap.put("/images/**", "anon");
		fmap.put("/public.html","anon");
		fmap.put("/logout","logout");
		fmap.put("/**", "anon");

		
		shiroFilter.setFilterChainDefinitionMap(fmap);
		return shiroFilter;
		
	}
	//生成安全管理器,注入realm
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("myReaml") Realm realm,@Qualifier("myReam2") Realm realm2 ) {
		DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
		defaultSecurityManager.setRealm(realm);
		defaultSecurityManager.setRealm(realm2);
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
