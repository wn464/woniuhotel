package com.project.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.MarkBean;

/**
 * 码表持久层接口
 * @author lenovo
 *
 */
public interface IMarkDao {
	@Select("select *from mark where id=#{mid}")
public MarkBean selectmarkbyid(@Param("mid")int mid);
}
