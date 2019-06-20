package com.project.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.IRoomService;
import com.project.bean.MarkBean;
import com.project.bean.PageBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;
import com.project.dao.IRoomDao;
@Service
public class RoomServiceImpl implements IRoomService{
	@Autowired
	private IRoomDao dao;

	@Override
	public RoomBean selectroombyid(int rid) {
		RoomBean bean = dao.selectroombyid(rid);
		return bean;
	}

	@Override
	public PageBean selectroombytype(TypeBean type, int page, int size) {
		PageBean bean = new PageBean();
		List<RoomBean> list = dao.selectroombytype(type, page, size);
		int totalNumber = dao.selectroomallbytype(type);
		bean.setPage(page);
		bean.setSize(size);
		bean.setList(list);
		bean.setTotalNumber(totalNumber);
		bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
		return bean;
	}

	@Override
	public boolean updateroomstatus(MarkBean status, RoomBean room) {
		int i =dao.updateroomstatus(status, room);
		if(i>0) {
			return true;
		}
		return false;
	}

	@Override
	public PageBean selectroombystatus(MarkBean status, int page, int size) {
		PageBean bean = new PageBean();
		List<RoomBean> list = dao.selectroombystatus(status, page, size);
		int totalNumber = dao.selectroomallbystatus(status);
		bean.setPage(page);
		bean.setSize(size);
		bean.setList(list);
		bean.setTotalNumber(totalNumber);
		bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
		return bean;
	}

	@Override
	public PageBean selectroombytypeandstatus(TypeBean type, MarkBean status, int page, int size) {
		PageBean bean = new PageBean();
		List<RoomBean> list = dao.selectroombytypeandstatus(type, status, page, size);
		int totalNumber = dao.selectroomallbytypeandstatus(type, status);
		bean.setPage(page);
		bean.setSize(size);
		bean.setList(list);
		bean.setTotalNumber(totalNumber);
		bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
		return bean;
	}

}
