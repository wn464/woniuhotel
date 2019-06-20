package com.project.Service;

import java.util.List;

import com.project.bean.DiscountBean;
import com.project.bean.OrderBean;

public interface IDiscountService {

	public int deletDiscount(int id);
	/**
	 * 添加优惠
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
	/**
	 * 按消费查询所有优惠
	 */
	public List<DiscountBean>  selectDiscountByPrice(double price);
	/**
	 * 按vip查询所有消费
	 */
	public List<DiscountBean> selectDiscountByVipId(int id);
	/**
	 * 查询可以获得的优惠
	 * @param order
	 * @return
	 */
	public List<DiscountBean> selectDiscountByOrder(OrderBean order);
}
