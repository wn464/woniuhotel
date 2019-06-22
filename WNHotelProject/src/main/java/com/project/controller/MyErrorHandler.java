package com.project.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyErrorHandler implements ErrorController {

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping("/error")
	@ResponseBody
	public String requestError() {
		return "网页丢失请稍后再试";
	}
}
