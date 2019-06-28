package com.project.Service;

import java.util.List;

import com.project.bean.MarkBean;
import com.project.bean.PeopleBean;

/**
 * 入住人员信息
 * @author x1c
 *
 */
public interface IPeopleService {
	
	//添加入住人员信息
	public int insertPeopleBean(String name,String idCard,int gende,int id);
	//删除入住人员信息
	public int deletePeopleBean(int pid);
	//模糊查询入住人员信息
	public List<PeopleBean> selectPeopleBean(String name);
	//修改入住人员信息
	public int update(PeopleBean people);
}
