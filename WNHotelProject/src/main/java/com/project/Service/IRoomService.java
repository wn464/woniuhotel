package com.project.Service;

import java.util.List;

import com.project.bean.MarkBean;
import com.project.bean.PageBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;

/***
 * 房间业务层接口
 * @author zxc
 *
 */
public interface IRoomService {
	/**
	 * 通过房间id查询房间详情
	 * @param rid房间id
	 * @return
	 */
public RoomBean selectroombyid(int rid);
/**
 * 通过房间类型，查询该类型下所有房间
 * @param type房间类型
 * @return
 */
public PageBean selectroombytype(TypeBean type,int page,int size);
/**
 * 传入状态对象，修改房间状态
 * @param status
 * @return
 */
public boolean updateroomstatus(MarkBean status,RoomBean room); 
/**
 * 根据房间状态查询该状态下的所有房间
 * @param status
 * @return
 */
public PageBean selectroombystatus(MarkBean status,int page,int size);
/**
 * 根据类型查询给类型下某一状态的房间
 * @param type
 * @param status
 * @param page
 * @param size
 * @return
 */
public PageBean selectroombytypeandstatus(TypeBean type,MarkBean status,int page,int size);
/**
 * 查询某时间段可住房间
 * @param type
 * @param inTime
 * @param outTime
 * @param page
 * @param size
 * @return
 */

public PageBean selectroombytypeantime(TypeBean type,String inTime,String outTime,int page,int size);
}
