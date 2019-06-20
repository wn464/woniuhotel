package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
	 * 查询某一类型下，某一状态 的所有房间
	 * @param type
	 * @param status
	 * @return
	 */
	@Select("select count(*) from room where type=#{type.id} and status=#{status.id}")
	public int selectroomallbytypeandstatus(@Param("type")TypeBean type,@Param("status")MarkBean status);
	/**
	 * 查询某一状态房间下的所有房间
	 * @param status
	 * @return
	 */
	@Select("select count(*) from room where status=#{status.id}")
	public int selectroomallbystatus(@Param("status")MarkBean status);
	/**
	 * 查询某一类型下有多少房间
	 * @param type
	 * @return
	 */
	@Select("select count(*)from room where type=#{type.id}")
	public int selectroomallbytype(@Param("type")TypeBean type);
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
  * 将房间更改为可住状态
  * @param status
  * @return
  */
 @Update("update room set status=3 where id=#{room.id}")
 public int updateroomstatus(@Param("room")RoomBean room);
 /**
  * 将房间更改为不可住状态
  * @param room
  * @return
  */
 @Update("update room set status=4 where id=#{room.id}")
 public int updaterooomstatusin(@Param("room")RoomBean room);
 /**
  * 根据房间类型，查询当前类型下某一状态的房间
  * @param type
  * @param status
  * @param page
  * @param size
  * @return
  */
 public List<RoomBean> selectroombytypeandstatus(@Param("type")TypeBean type,@Param("status")MarkBean status,@Param("page")int page,@Param("size")int size);
}
