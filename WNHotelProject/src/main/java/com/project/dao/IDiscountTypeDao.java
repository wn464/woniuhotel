package com.project.dao;

import java.util.List;

import com.project.bean.DiscountTypeBean;

public interface IDiscountTypeDao {

	/**
	 * 添加优惠类型
	 * @param discountType
	 * @return
	 */
	public int insertDiscountType(DiscountTypeBean discountType);
	/**
	 * 修改优惠类型
	 * @param discountType
	 * @return
	 */
	public int updateDiscountType(DiscountTypeBean discountType);
	/**
	 * 删除优惠类型
	 * @param id
	 * @return
	 */
	public int deletDiscountType(int id);
	/**
	 * 查询所有优惠类型
	 * @return
	 */
	public List<DiscountTypeBean> selectDiscountTypeAll();
	/**
	 * 通过id查询优惠类型
	 * @param id
	 * @return
	 */
	public DiscountTypeBean selectDiscountTypeById(int id);
	
}
