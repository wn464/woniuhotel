package com.project.util.countUtil;


import com.project.bean.DiscountBean;
import com.project.bean.OrderBean;

public class OrderCount {
	/**
	 * 检查优惠是否满足
	 * @param order
	 * @param discount
	 * @return
	 */
	public boolean orderCheckVip(OrderBean order,DiscountBean discount) {
		
		if(discount.getVip()!=0) {
			if(discount.getVip()!=order.getMember().getVip().getId()) {
				return false;
			}
		}
		return true;
	}
}
