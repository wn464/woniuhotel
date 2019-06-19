package com.project.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.filter.TestFilter;

@Configuration
public class FilterConfig {
//	@Bean   //生成bean
//	public FilterRegistrationBean testFilter(){
//		FilterRegistrationBean filterbean = new FilterRegistrationBean<>();
//		filterbean.setFilter(new TestFilter());  //filter-class
//		filterbean.addUrlPatterns("/*");   // filterbean
//		filterbean.setOrder(0);   //设置过滤器优先级
//		
//		return filterbean;
//				
//	}
}
