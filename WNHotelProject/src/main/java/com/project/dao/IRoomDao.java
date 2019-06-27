package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.MarkBean;
import com.project.bean.PeopleBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;

/**
 * 房间业务层接口
 * @author zxc
 *
 */
public interface IRoomDao {
	/**
	 * 查询房间人数
	 * @param nmae
	 * @return
	 */
	@Select("select  p.gender,p.id,p.idCard,p.name  from room r \r\n" + 
			"join live l \r\n" + 
			"on r.id=l.roomid \r\n" + 
			"join people p\r\n" + 
			"on l.peopleid=p.liveid\r\n" + 
			"where r.name=#{name}")
	@Results({
			@Result(property="gender", column="gender",one=@One(select=
					  "com.project.dao.IMarkDao.selectmarkbyid"))
	})
	public List<PeopleBean> selectpeopleall(@Param("name")String nmae);
	/**
	 * 查询房间人数
	 * @return
	 */
	@Select("select  count(p.id)  from room r \r\n" + 
			"join live l \r\n" + 
			"on r.id=l.roomid \r\n" + 
			"join people p\r\n" + 
			"on l.peopleid=p.liveid\r\n" + 
			"where r.name=#{name}")
	public int peoplenumber(@Param("name")String name);
	/***
	 * 
	 * @return
	 */
	@Select("select count(*) from room")
	public int selectroomallnumber() ;
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
 /***
  * 查询所有房间
  * @param page
  * @param size
  * @return
  */
 public List<RoomBean> selectroomall(@Param("page")int page,@Param("size")int size);
 
 /**
  * 修改房间信息
  * @param room
  * @return
  */
 public int updateroom(@Param("room")RoomBean room);
 /**
  * 添加房间
  * @param roo
  * @return
  */
 @Insert("insert into room (name,type,img,status,phone,location,message,price,area)"
 		+ "values(#{room.name},#{room.type.id},#{room.img},3,#{room.phone},#{room.location},#{room.message},#{room.price},#{room.area})")
 public int insertroom(@Param("room")RoomBean roo);
}
