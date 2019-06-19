package com.project.Service;

import java.util.List;

import com.project.bean.DiscountTypeBean;

public interface IDiscountTypeService {

	/**
	 * 查询所有优惠
	 * @return
	 */
	public List<DiscountTypeBean> selectDiscountTypeAll();
	/**
	 * 通过ID查询优惠
	 * @param id
	 * @return
	 */
	public DiscountTypeBean selectDiscountTypeById(int id);
}
