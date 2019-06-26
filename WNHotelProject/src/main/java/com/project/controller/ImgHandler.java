package com.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.util.ImgUtil;
@Controller
public class ImgHandler {

	@PostMapping("/upimg")
	@ResponseBody
	public String upImg(MultipartFile imgfile,HttpServletRequest req) {
		String imgName = ImgUtil.SaveImg(imgfile, req);
		System.out.println(imgName);
		return imgName;
	}
}
