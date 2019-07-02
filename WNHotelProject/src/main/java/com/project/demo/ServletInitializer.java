package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
@SpringBootApplication
@EnableScheduling
@EnableWebSocket
@ComponentScan("com.project.controller")
@ComponentScan("com.project.controller.after")
@ComponentScan("com.project.Service")
@ComponentScan("com.project.config")
@MapperScan("com.project.dao")
@ComponentScan("com.project.shiro")
@EnableCaching  //开启缓存
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WnHotelProjectApplication.class);
	}

//	@Bean  
//    public ServerEndpointExporter serverEndpointExporter() {  
//        return new ServerEndpointExporter();  
//    }  
}
