package com.project.util.countUtil;


import com.project.bean.DiscountBean;
import com.project.bean.OrderBean;

public class OrderUtil extends OrderCount{

	public double getFinalMoney(OrderBean order , DiscountBean discount) throws Exception {
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
		return price;
	}
}
