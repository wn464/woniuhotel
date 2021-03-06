package com.project.Service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.Service.IRoomService;
import com.project.bean.MarkBean;
import com.project.bean.PageBean;
import com.project.bean.PeopleBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;
import com.project.dao.ILiveDao;
import com.project.dao.IRoomDao;
@Service
public class RoomServiceImpl implements IRoomService{
	@Autowired
	private IRoomDao dao;
	@Autowired
	private ILiveDao dao2;
	@Override
	public RoomBean selectroombyid(int rid)throws Exception {
		RoomBean bean = dao.selectroombyid(rid);
		return bean;
	}

	@Override
	public PageBean selectroombytype(TypeBean type, int page, int size) throws Exception {
		PageBean bean = new PageBean();
		List<RoomBean> list = dao.selectroombytype(type, (page-1)*size, size);
		int totalNumber = dao.selectroomallbytype(type);
		bean.setPage(page);
		bean.setSize(size);
		bean.setList(list);
		bean.setTotalNumber(totalNumber);
		bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
		return bean;
	}

	@Override
	public boolean updateroomstatus( RoomBean room)throws Exception {
		int i =dao.updateroomstatus(room);
		if(i>0) {
			return true;
		}
		return false;
	}

	@Override
	public PageBean selectroombystatus(MarkBean status, int page, int size)throws Exception {
		PageBean bean = new PageBean();
		List<RoomBean> list = dao.selectroombystatus(status, (page-1)*size, size);
		int totalNumber = dao.selectroomallbystatus(status);
		bean.setPage(page);
		bean.setSize(size);
		bean.setList(list);
		bean.setTotalNumber(totalNumber);
		bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
		return bean;
	}

	@Override
	public PageBean selectroombytypeandstatus(TypeBean type, MarkBean status, int page, int size)throws Exception {
		PageBean bean = new PageBean();
		System.out.println(66666);
		List<RoomBean> list = dao.selectroombytypeandstatus(type, status, (page-1)*size, size);
		int totalNumber = dao.selectroomallbytypeandstatus(type, status);
		bean.setPage(page);
		bean.setSize(size);
		bean.setList(list);
		bean.setTotalNumber(totalNumber);
		bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
		return bean;
	}
/***
 * 
 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	public PageBean selectroombytypeantime(TypeBean type, String inTime, String outTime, int page, int size)throws Exception {
		PageBean bean = new PageBean();
		List<RoomBean> roomsa=	dao.selectroombytype(type, (page-1)*size, size);

		List<Integer> ids=dao2.selectTime(inTime, outTime);
		
	
	for (int i = 0; i < roomsa.size(); i++) {
		System.out.println(ids.contains(roomsa.get(i).getId()));
		if(ids.contains(roomsa.get(i).getId())) {
			roomsa.remove(i);
			i--;
		}
	}
	System.out.println("房间数目："+roomsa.size());

	int totalNumber=dao.selectroomallbytype(type)-(ids.size());
	bean.setPage(page);
	bean.setSize(size);
	bean.setTotalNumber(totalNumber);
	bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
	bean.setList(roomsa);
	return bean;
	}

@Override
public boolean updateroomstatusin(RoomBean room) throws Exception{
	int i=dao.updaterooomstatusin(room);
	if(i>0) {
		return true;
	}
	return false;
}

@Override
public PageBean selectroomall(int page, int size)throws Exception {
	PageBean bean = new PageBean();
	
	List<RoomBean> list = dao.selectroomall( (page-1)*size, size);
	int totalNumber=dao.selectroomallnumber();
	bean.setPage(page);
	bean.setSize(size);
	bean.setList(list);
	bean.setTotalNumber(totalNumber);
	bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
	return bean;
}

@Override
public boolean updateroom(RoomBean room)throws Exception {
	int i=dao.updateroom(room);
	if(i>0) {
		return true;
	}
	return false;
}

@Override
public boolean insertroom(RoomBean room)throws Exception {
 int i=dao.insertroom(room);
 if(i>0) {
	 return true;
 }
	return false;
}

@Override
public List<PeopleBean> selectpeopleall(String name) throws Exception{
	
	LocalDateTime dt=LocalDateTime.now();
	String tiime=dt.toString();
	String time=tiime.substring(0, 10);
	List<PeopleBean> peos=dao.selectpeopleall(name,time);
	return peos;
}

@Override
public RoomBean selectroombyname(String name)throws Exception {
	RoomBean room=dao.selectroombyname(name);
	return room;
}

}
