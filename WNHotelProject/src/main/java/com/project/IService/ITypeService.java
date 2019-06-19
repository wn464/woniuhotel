package com.project.IService;

import com.project.bean.TypeBean;

/**
 * 类型对象接口
 * @author lenovo
 *
 */
public interface ITypeService {
	/**
	 * 传入
	 * @param tid
	 * @return
	 */
public TypeBean selecttypebyId(int tid);
}
