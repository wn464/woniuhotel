package com.project.dao;

import org.apache.ibatis.annotations.Insert;

import com.project.bean.PeopleBean;
//入住人员持久层接口
public interface IPeopleDao {
	//添加入住信息
	@Insert("insert into people(name,idCard,gender,liveid)values(#{name},#{idCard},#{gender.message},#{liveid})")
	public void addPeole(PeopleBean bean);

}
