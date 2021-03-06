package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.OrderBean;
import com.project.bean.PeopleBean;
/**
 * 入住人员持久层
 * @author x1c
 *
 */
public interface IPeopleDao {
	//添加入住人员信息
	@Insert("insert into people (name,idCard,gender,liveid) values(#{name},#{idCard},#{gende},#{liveId})")
	public int insertPeopleBean(PeopleBean bean);
	//删除入住人员信息
	public int deletePeopleBean(int pid);
	//模糊查询入住人员信息
	public List<PeopleBean> selectPeopleBean(String name);
	
	//修改入住人员信息
	@Update("update people set name=#{name},idCard=#{idCard} where id =#{id}")
	public int update(PeopleBean people);
	

}
