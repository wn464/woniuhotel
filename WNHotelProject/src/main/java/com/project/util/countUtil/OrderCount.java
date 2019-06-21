package com.project.util.countUtil;


import com.project.bean.DiscountBean;
import com.project.bean.OrderBean;
import com.project.dao.IVipDao;

public class OrderCount extends DiscountCount{
	public OrderCount(IVipDao vipDao) {
		super(vipDao);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 检查优惠是否满足
	 * @param order
	 * @param discount
	 * @return
	 */
	public boolean orderCheckVip(OrderBean order,DiscountBean discount) {
		
		if(discount.getVip()!=0) {
			if(discount.getVip()<order.getMember().getVip()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 检查金额是否满足
	 * @param order
	 * @param discount
	 * @return
	 */
	public boolean orderCheckMoney(OrderBean order,DiscountBean discount) {
		if(discount.getNumber1()!=0) {
			double price = getCount(order);
			if (price<discount.getNumber1()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 检查订单是否满足需求
	 * @param order
	 * @param discount
	 * @return
	 */
	public boolean orderCheck(OrderBean order,DiscountBean discount) {
		if(orderCheckMoney(order, discount) && orderCheckVip(order, discount)) {
			return true;
		}
		return false;
	}
}
