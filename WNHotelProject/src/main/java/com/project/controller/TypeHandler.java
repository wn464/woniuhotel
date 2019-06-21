package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Service.ITypeService;
import com.project.bean.TypeBean;
/**
 * 
 * @author zxc
 *
 */
@RestController
public class TypeHandler {
	@Autowired
	private ITypeService servcie;
	/**
	 * 查询所有房间类型
	 * @return
	 */
	@GetMapping(value="/type")
public List<TypeBean> findalltype(){
		List<TypeBean> types=servcie.selecttypeall();
		return types;
}
}
