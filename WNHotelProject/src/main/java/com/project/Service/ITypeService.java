package com.project.Service;

import com.project.bean.TypeBean;

/**
 * 类型对象
 * @author lenovo
 *
 */
public interface ITypeService {
	/**
	 * 通过type类型的id，查询类型的详情
	 * @param tid
	 * @return
	 */
public TypeBean selecttypebyid(int tid);
}
