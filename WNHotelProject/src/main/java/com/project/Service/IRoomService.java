package com.project.Service;

import java.util.List;

import com.project.bean.MarkBean;
import com.project.bean.PageBean;
import com.project.bean.PeopleBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;

/***
 * 房间业务层接口
 * @author zxc
 *
 */
public interface IRoomService {
	/**
	 * 通过房间号查询房间
	 * @param name
	 * @return
	 */
	public RoomBean selectroombyname(String name)throws Exception;
	/**
	 * 通过房间id查询房间详情
	 * @param rid房间id
	 * @return
	 */
public RoomBean selectroombyid(int rid)throws Exception;
/**
 * 通过房间类型，查询该类型下所有房间
 * @param type房间类型
 * @return
 * @throws Exception 
 */
public PageBean selectroombytype(TypeBean type,int page,int size) throws Exception;
/**
 * 修改房间状态为可住状态
 * @param status
 * @return
 */
public boolean updateroomstatus(RoomBean room)throws Exception; 
/**
 * 修改房间为不可住状态
 * @param room
 * @return
 */
public boolean updateroomstatusin(RoomBean room)throws Exception;
/**
 * 根据房间状态查询该状态下的所有房间
 * @param status
 * @return
 */
public PageBean selectroombystatus(MarkBean status,int page,int size)throws Exception;
/**
 * 根据类型查询给类型下某一状态的房间
 * @param type
 * @param status
 * @param page
 * @param size
 * @return
 */
public PageBean selectroombytypeandstatus(TypeBean type,MarkBean status,int page,int size)throws Exception;
/**
 * 查询某时间段可住房间
 * @param type
 * @param inTime
 * @param outTime
 * @param page
 * @param size
 * @return
 */

public PageBean selectroombytypeantime(TypeBean type,String inTime,String outTime,int page,int size)throws Exception;
/**
 * 查询所有房间
 * @param page
 * @param size
 * @return
 */
public PageBean selectroomall(int page,int size)throws Exception;
/**
 * 修改房间信息
 * @param room
 * @return
 */
public boolean updateroom(RoomBean room)throws Exception;
/**
 * 添加房间
 * @param room
 * @return
 */
public boolean insertroom(RoomBean room)throws Exception;
/**
 * 查询房间人数详情
 * @param name
 * @return
 */
public List<PeopleBean> selectpeopleall(String name)throws Exception;
}
