package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.bean.MarkBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;

/**
 * 房间业务层接口
 * @author zxc
 *
 */
public interface IRoomDao {
	/**
	 * 通过查询房间详情
	 * @param rid房间id
	 * @return
	 */
 public RoomBean selectroombyid(@Param("rid") int rid);
 /**
  * 根据房间类型，查询该类型下所有房间
  * @param type房间类型对象
  * @return
  */
 public List<RoomBean> selectroombytype(@Param("type")TypeBean type);
 /**
  * 根据房间的某一状态查询改状态下所有房间
  * @param status
  * @return
  */
 public List<RoomBean> selectroombystatus(@Param("status")MarkBean status);
 /**
  * 根据传入的房间状态，修改房间状态
  * @param status
  * @return
  */
 public int updateroomstatus(@Param("status")MarkBean status);
}
