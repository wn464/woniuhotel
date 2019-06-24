package com.project.util.countUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.Service.IDiscountService;
import com.project.Service.IVipService;
import com.project.bean.DiscountBean;
import com.project.bean.OrderBean;
import com.project.util.MoneyUtil;
import com.project.util.TimeUtil;
@Component
public class OrderUtil extends OrderCount{

	@Autowired
	private static  IVipService vipService;
	@Autowired
	private IDiscountService discountService;
	public OrderUtil() {
		super(vipService);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 线上计费，需要获取order中用户的vip、居住信息，完整的优惠方案
	 * @param order
	 * @param discount1 优惠折扣
	 * @param discount2 线上折扣
	 * @return 计算后金额
	 * @throws Exception 优惠不匹配
	 */
	public double getOnlineMoney(OrderBean order , DiscountBean discount1,DiscountBean discount2) throws Exception {
		double price = getUnderLineMoney(order, discount1);
		return MoneyUtil.multiply(price, discount2.getNumber2());
	}
	/**
	 * 线下计费，需要获取order中用户的vip、居住信息，完整的优惠方案
	 * @param order
	 * @param discount 优惠折扣
	 * @return 计算后金额
	 * @throws Exception 优惠不匹配
	 */
	public double getUnderLineMoney(OrderBean order , DiscountBean discount) throws Exception {
		if(!orderCheck(order, discount)) {
			throw new  Exception("订单优惠有误，请选择正确的优惠。");
		}
		double price = 0;
		switch (discount.getDiscountType()) {
		case 1:
			price = getFullDiscountCount(order, discount);
			break;
		case 2:
			price = getEveryMulitDiscountCount(order, discount);
			break;
		case 3:
			price = getFixDiscountCount(order, discount);
			break;
		default:
			price = getCount(order);
			break;
		}
		System.out.println("会员折扣之前："+price);
		return getVipDiscountCount(price, order);
	}
	/**
	 * 线下计费，需要获取order中用户的vip、居住信息，完整的优惠方案
	 * @param price订单价格
	 * @param discountId 优惠折扣id
	 * @return 计算后金额
	 * @throws Exception 优惠不匹配
	 */
	public double getUnderLineMoney(double price, int discountId,int vipId) throws Exception {
		DiscountBean discount = discountService.selectDiscountById(discountId);
		
		switch (discount.getDiscountType()) {
		case 1:
			price = getFullDiscountCount(price, discount);
			break;
		case 2:
			price = getEveryMulitDiscountCount(price, discount);
			break;
		case 3:
			price = getFixDiscountCount(price, discount);
			break;
		default:
			break;
		}
		System.out.println("会员折扣之前："+price);
		return getVipDiscountCount(price, vipId);
	}
}
