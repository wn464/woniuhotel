package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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
 public List<RoomBean> selectroombytype(@Param("type")TypeBean type,@Param("page")int page,@Param("size")int size);
 /**
  * 根据房间的某一状态查询改状态下所有房间
  * @param status
  * @return
  */
 public List<RoomBean> selectroombystatus(@Param("status")MarkBean status,@Param("page")int page,@Param("size")int size);
 /**
  * 根据传入的房间状态，修改房间状态
  * @param status
  * @return
  */
 @Update("update room set status=#{status.id} where id=#{room.id}")
 public int updateroomstatus(@Param("status")MarkBean status ,@Param("room")RoomBean rooom);
 /**
  * 根据房间类型，查询当前类型下某一状态的房间
  * @param type
  * @param status
  * @param page
  * @param size
  * @return
  */
 public List<RoomBean> selectroombytypeandstatus(@Param("tyep")TypeBean type,@Param("status")MarkBean status,@Param("page")int page,@Param("size")int size);
}
