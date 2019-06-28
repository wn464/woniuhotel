package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MuenHandler {
		
	
	@RequestMapping("/user/muen")
	public String uuu() {
		
		return "/admin/muen.html";
	}
}
