package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.bean.LiveBean;
/**
 * 入住信息持久层
 * @author x1c
 *
 */
public interface ILiveDao {

	//添加入住信息
	@Insert("")
	public int insertLiveBean(LiveBean liveBean);
	
	//通过时间段来查询这个时间段已经被预定过了的房间
	public List<Integer> selectTime(String inTime,String outTime);
	
	
	//删除入住信息
	public int deleteLiveBean(int lid);
	//查询入住信息
	public LiveBean selectLiveBean(LiveBean liveBean);
	//修改入住信息
	public int updateLiveBean(int lid);
}
