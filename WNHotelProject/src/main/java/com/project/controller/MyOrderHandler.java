package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyOrderHandler {
	
	@RequestMapping("/order")
	public String order() {
		
		
		return "myorder.html";
	}
	
}
