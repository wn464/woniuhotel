package com.project.controller.after;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Service.ICommentService;
import com.project.bean.CommentBean;

@Controller
public class CommentController {
	@Autowired
	private ICommentService service;
	
	@RequestMapping("/comment")
	public String selectmore(ModelMap map){
		System.out.println("进入");
		List<CommentBean> list = service.selectMoreComment();
		map.put("list",list);
		return "/admin/comment.html";
	}

}
