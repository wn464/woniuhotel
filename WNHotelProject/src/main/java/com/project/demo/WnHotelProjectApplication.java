
package com.project.demo;


import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.project.util.timingutil.ordertiming;
@SpringBootApplication
@EnableScheduling
@ComponentScan("com.project.controller")
@ComponentScan("com.project.controller.after")
@ComponentScan("com.project.Service")
@ComponentScan("com.project.config")
@MapperScan("com.project.dao")
@ComponentScan("com.project.shiro")
//@ComponentScan("com.project.util.timingutil")
//@ComponentScan("com.project.util")
@EnableCaching  //开启缓存
public class WnHotelProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(WnHotelProjectApplication.class, args);
	}

}

