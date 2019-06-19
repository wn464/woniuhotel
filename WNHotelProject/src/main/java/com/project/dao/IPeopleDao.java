package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.project.bean.PeopleBean;
/**
 * 入住人员持久层
 * @author x1c
 *
 */
public interface IPeopleDao {
	//添加入住人员信息
	public int insertPeopleBean(PeopleBean peopleBean);
	//删除入住人员信息
	public int deletePeopleBean(int pid);
	//模糊查询入住人员信息
	public List<PeopleBean> selectPeopleBean(PeopleBean peopleBean);
	//修改入住人员信息
	public int upstatePeopleBean(int pid);

}
