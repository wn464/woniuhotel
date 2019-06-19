package com.project.Service;

import java.util.List;

import com.project.bean.DiscountBean;

public interface IDiscountService {

	public int deletDiscount(int id);
	/**
	 * 满额减固定优化
	 * @param discount
	 * @return
	 */
	public int insertFullDiscount(DiscountBean discount);
	/**
	 * 固定折扣优惠
	 * @param discount
	 * @return
	 */
	public int insertPercentageDiscount(DiscountBean discount);
	/**
	 * 阶梯型优惠，折扣型
	 */
	public int insertStepPercentageDiscount(DiscountBean discount);
	/**
	 * 阶梯型优惠，直减型
	 */
	public int insertStepFullDiscount(DiscountBean discount);
	/**
	 * 每满固定金额，直减优惠
	 */
	public int insertEveryMinusDiscount(DiscountBean discount);
	/**
	 * 通过id查询优惠
	 * @param id
	 * @return
	 */
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
