package com.project.util.countUtil;

import java.util.List;

import com.project.bean.DiscountBean;
import com.project.bean.DiscountTypeBean;
import com.project.bean.LiveBean;
import com.project.bean.OrderBean;

public class DiscountCount {

	/**
	 * 满减计算
	 * @param order
	 * @param discount
	 * @return
	 */
	public double fullDiscountCount(OrderBean order,DiscountBean discount) {
		double price = 0;
		List<LiveBean> lives = order.getLives();
		for (LiveBean liveBean : lives) {
			liveBean.getRoom().getType();
		}
		return price;
	}
	
}
