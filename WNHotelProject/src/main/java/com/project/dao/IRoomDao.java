package com.project.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.RoomBean;

/**
 * 房间业务层接口
 * @author lenovo
 *
 */
public interface IRoomDao {
	/**
	 * 通过查询房间详情
	 * @param rid
	 * @return
	 */
 public RoomBean selectroombyid(@Param("rid") int rid);
}
