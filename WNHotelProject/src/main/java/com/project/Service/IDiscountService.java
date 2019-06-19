package com.project.Service;

import java.util.List;

import com.project.bean.DiscountBean;

public interface IDiscountService {

	public int deletDiscount(int id);
	/**
	 * 添加用户
	 * @param discount
	 * @return
	 */
	public int insertDiscount(DiscountBean discount);
	public DiscountBean selectDiscountById(int id);
	/**
	 * 模糊查询优惠
	 * @param name
	 * @return
	 */
	public List<DiscountBean> selectDiscountByName(String name);
	/**
	 * 修改优惠信息
	 * @param discount
	 * @return
	 */
	public int updateDiscount(DiscountBean discount);
	/**
	 * 查询所有优惠
	 */
	public List<DiscountBean> selectDiscountAll();
}
