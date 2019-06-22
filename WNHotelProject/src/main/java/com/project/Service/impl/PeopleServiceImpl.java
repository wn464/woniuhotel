package com.project.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.IPeopleService;
import com.project.bean.PeopleBean;
import com.project.dao.IPeopleDao;
@Service
public class PeopleServiceImpl implements IPeopleService{
	@Autowired
	private IPeopleDao dao;

	@Override
	public int insertPeopleBean(PeopleBean peopleBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePeopleBean(int pid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PeopleBean> selectPeopleBean(String name) {
		List<PeopleBean> list = dao.selectPeopleBean(name);
		return list;
	}

	@Override
	public int upstatePeopleBean(int pid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
