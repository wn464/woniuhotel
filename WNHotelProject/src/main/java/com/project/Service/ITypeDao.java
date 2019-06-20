package com.project.Service;

import java.util.List;

import com.project.bean.TypeBean;

/**
 * 房间类型实体类
 * @author zxc
 *
 */
public interface ITypeDao {
	/**
	 * 查询所有房间类型
	 * @return
	 */
List<TypeBean> selecttypeall();
}
