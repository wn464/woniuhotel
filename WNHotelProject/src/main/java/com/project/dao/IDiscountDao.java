package com.project.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	/**
	 * 通过消费和vip获得优惠,时间
	 * @param price
	 * @param vip
	 * @return
	 */
	public List<DiscountBean> selectDiscountByPriceOrVip(@Param("price")double price,@Param("vip")int vip,@Param("time")String time);
}
