package com.project.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.ILiveService;
import com.project.bean.LiveBean;
import com.project.dao.ILiveDao;
@Service
public class LiveServiceImp implements ILiveService{
	@Autowired
	private ILiveDao liveDao;
	@Override
	public int insertLiveBean(LiveBean liveBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteLiveBean(int lid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LiveBean selectLiveBean(LiveBean liveBean) {
		// TODO Auto-generated method stub
		return null;
	}
	//修改入住信息
	@Override
	public int updateLiveBean(LiveBean liveBean) {
		System.out.println("-------------------------------------------------------------------");
		int num = liveDao.updateLiveBean(liveBean);
		return num;
	}

}
