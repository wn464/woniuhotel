package com.project.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.ITypeService;
import com.project.bean.TypeBean;
import com.project.dao.ITypeDao;
/***
 * 
 * @author zxc
 *
 */
@Service
public class TypeService implements ITypeService {
	@Autowired
private ITypeDao dao;
	@Override
	public List<TypeBean> selecttypeall() {
		List<TypeBean> types=dao.selecttypeall();
		return types;
	}
	@Override
	public TypeBean selecttypebyid(int tid) {
		TypeBean type=dao.selecttypeByid(tid);
		return type;
	}

}
