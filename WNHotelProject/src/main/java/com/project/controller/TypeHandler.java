package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.ITypeService;
import com.project.bean.TypeBean;
/**
 * 
 * @author zxc
 *
 */
@Controller
public class TypeHandler {
	@Autowired
	private ITypeService servcie;
	/**
	 * 查询所有房间类型
	 * @return
	 */
	@GetMapping(value="/typeall")
	@ResponseBody
public List<TypeBean> findalltype(){
		List<TypeBean> types=servcie.selecttypeall();
		return types;
}
	@GetMapping(value="/type/{tid}")
	public String findtypeby(@PathVariable("tid")int id,ModelMap map) {
		TypeBean type= servcie.selecttypebyid(id);
		map.addAttribute("type", type);
		return "message.html";
	}
}
