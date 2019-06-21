package com.project.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.IRoleService;
import com.project.bean.RoleBean;
import com.project.dao.IRoleDao;
@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao dao;
	
	@Override
	public RoleBean findRoleById(int id) {
		RoleBean bean = dao.findRoleById(id);
		return bean;
	}

}
