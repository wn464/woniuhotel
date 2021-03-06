package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.ServiceBean;
import com.project.bean.TypeBean;

/**
 * 房间服务对象实体
 * @author zxc
 *
 */
public interface IServiceDao {
	/**
	 * 根据房间id查询当前房间所有的服务
	 * @return
	 */
	@Select("select *from service where roomid=#{rid}")
public List<ServiceBean> selectservicebyrid(@Param("rid")int rid);
}
