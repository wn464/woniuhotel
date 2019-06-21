package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.ICommentService;
import com.project.bean.CommentBean;
import com.project.bean.PageBean;

@Controller
public class CommentHandler {
	@Autowired
	private ICommentService service;
	@GetMapping("/find")
	@ResponseBody
	public PageBean selectComment(int page,int size) {
		PageBean bean = service.selectComment(page,size);
		System.out.println(bean);
		return bean;
	}
	@RequestMapping("/add")
	@ResponseBody
	public int insertComment(CommentBean bean) {
		int i = service.insertComment(bean);
		System.out.println(i);
		return i;
	}
	

}
