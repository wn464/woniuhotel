package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.Service.ICommentService;
import com.project.Service.IOrderService;
import com.project.bean.CommentBean;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;

@Controller
public class CommentHandler {
	@Autowired
	private ICommentService service;
	@Autowired
	private IOrderService orderService;
	public static String newName;

	@GetMapping("/find")
	@ResponseBody
	public PageBean selectComment(int page, int size) {
		PageBean bean = service.selectComment(page, size);
		System.out.println(bean);
		return bean;
	}

	@PostMapping("/add")
	public String insertComment(MultipartFile imageName, String mess, HttpServletRequest req,ModelMap map) {
		// 获取到tomcat的物理路径 将虚拟路径转化为物理路径
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("/upload");
		System.out.println(path);
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		File file1 = new File(path, changeName(imageName.getOriginalFilename()));
		// 将是客户端传递过来的文件信息复制给path目录下面的文件
		try {
			imageName.transferTo(file1);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommentBean bean = new CommentBean();
	
		bean.setImgName(newName);
		bean.setMessage(mess);
		bean.setName("王先生");
		OrderBean order = new OrderBean();
		order.setId(1);
		bean.setOrderId(order);
		System.out.println(bean);
		int i = service.insertComment(bean);
		return "comment.html";
		
	}

	public String changeName(String name) {
		newName = UUID.randomUUID().toString() + "_" + name;

		return newName;
	}

	@RequestMapping("/findComment")
	@ResponseBody
	public List<CommentBean> selectAll() {
		List<CommentBean> list = service.selectAllComment();
		System.out.println(list);
		return list;
	}

}
