package com.project.util.countUtil;


import com.project.bean.DiscountBean;
import com.project.bean.OrderBean;
import com.project.util.MoneyUtil;

public class OrderUtil extends OrderCount{

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
			price = getFixDiscountCount(order, discount);
			break;
		case 3:
			price = getEveryMulitDiscountCount(order, discount);
			break;
		default:
			price = getCount(order);
			break;
		}
		return getVipDiscountCount(price, order);
	}
}
