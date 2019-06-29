package com.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class myexcption  {
	@ExceptionHandler
 public String exection(HttpServletRequest req, Exception ex) {
	 System.out.println(ex.getMessage());
	 System.out.println(ex.getLocalizedMessage());
	 return "/505.html";
 }
}
