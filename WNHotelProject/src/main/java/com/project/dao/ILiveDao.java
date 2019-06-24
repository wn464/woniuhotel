package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.project.bean.LiveBean;
/**
 * 入住信息持久层
 * @author x1c
 *
 */
public interface ILiveDao {

	//添加入住信息
	@Insert("insert into live (roomid,intime,outtime,orderid,phonenumber,people) values (#{roomid},#{inTime},#{outTime},#{orderid},#{phoneNumber},#{people})")
	public int insertLiveBean(LiveBean liveBean);
	
	//通过时间段来查询这个时间段已经被预定过了的房间(ok) 
	public List<Integer> selectTime(String inTime,String outTime);
	
	//通过开房人名字和入住时间来查询入住信息  里面包含RoomBean(ok)
	public List<LiveBean> selectBypeopleAndDate(String people,String time);
	
	//通过订单号查询入住信息
	public LiveBean findByorderid(int orderid);
	
	//修改入住信息
	public int updateLiveBean(LiveBean liveBean);
}
