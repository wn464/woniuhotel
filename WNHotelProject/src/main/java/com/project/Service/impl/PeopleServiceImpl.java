package com.project.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.IPeopleService;
import com.project.bean.LiveBean;
import com.project.bean.MarkBean;
import com.project.bean.OrderBean;
import com.project.bean.PeopleBean;
import com.project.dao.IOrderDao;
import com.project.dao.IPeopleDao;
@Service
public class PeopleServiceImpl implements IPeopleService{
	@Autowired
	private IPeopleDao dao;
	@Autowired
	private IOrderDao dao1;

	@Override
	public int insertPeopleBean(String name,String idCard,int gende,int id) {
		OrderBean orderBean = dao1.selectOrderById(id);
		PeopleBean bean = new PeopleBean();
		List<LiveBean> lives = orderBean.getLives();
		int value =0;
		for (LiveBean liveBean : lives) {
			bean.setLiveId(liveBean.getId());
			bean.setGende(gende);
			bean.setIdCard(idCard);
			bean.setName(name);
			value += dao.insertPeopleBean(bean);
		}
		return value;
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
