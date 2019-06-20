package com.project.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.TypeBean;
/**
 * 房间实体类
 * @author zxc
 *
 */
public interface ITypeDao {
	/***
	 * 根据房间类型id查询房类型
	 * @param tid
	 * @return
	 */
	@Select("select *from type where id=#{tid}")
public TypeBean selecttypeByid(@Param("tid")int tid);
}
