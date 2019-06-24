package com.project.Service;

import java.util.List;

import com.project.bean.TypeBean;

/**
 * 房间类型实体类
 * @author zxc
 *
 */
public interface ITypeService {
	/**
	 * 查询所有房间类型
	 * @return
	 */
List<TypeBean> selecttypeall();
/**
 * 查询某一类型详情
 * @param tid
 * @return
 */
TypeBean selecttypebyid(int tid);
}
