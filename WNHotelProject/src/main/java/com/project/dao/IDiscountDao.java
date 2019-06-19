package com.project.dao;

import java.util.List;

import com.project.bean.DiscountBean;

/**
 * 定制优惠的dao
 * @author my
 *
 */
public interface IDiscountDao {

	/**
	 * 添加优惠信息
	 * @param discount
	 * @return
	 */
	public int insertDiscount(DiscountBean discount);
	/**
	 * 修改优惠信息，必须传入优惠id
	 * @param discount
	 * @return
	 */
	public int updateDiscount(DiscountBean discount);
	/**
	 * 通过id查询优惠，精准查询
	 * @param id
	 * @return
	 */
	public DiscountBean selectDiscountById(int id);
	/**
	 * 通过名字查询优惠，模糊查询
	 * @param name
	 * @return
	 */
	public List<DiscountBean> selectDiscountByName(String name);
	/**
	 * 通过id删除优惠
	 * @param id
	 * @return
	 */
	public int deletDiscountById(int id);
	/**
	 * 查询所有优惠
	 * @return
	 */
	public List<DiscountBean> selectDiscountAll();
}
