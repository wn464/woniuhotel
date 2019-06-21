package com.project.util.countUtil;

import java.util.List;

import com.project.bean.DiscountBean;
import com.project.bean.LiveBean;
import com.project.bean.OrderBean;
import com.project.dao.IVipDao;
import com.project.util.MoneyUtil;

public class DiscountCount {

	private IVipDao vipDao;
	
	public DiscountCount(IVipDao vipDao) {
		super();
		this.vipDao = vipDao;
	}
	/**
	 * 初步统计金额
	 * @param order
	 * @return
	 */
	public static double  getCount(OrderBean order) {
		double price = 0;
		List<LiveBean> lives = order.getLives();
		for (LiveBean liveBean : lives) {
			double roomPrice = liveBean.getRoom().getPrice();
			System.out.println("房间价格"+roomPrice);
			price = MoneyUtil.add(price, roomPrice);
		}
		System.out.println("初步计算价格："+price);
		return price;
	}
	/**
	 * 满减计算
	 * @param order
	 * @param discount
	 * @return
	 */
	public double getFullDiscountCount(OrderBean order,DiscountBean discount) {
		double price = getCount(order);
		price = MoneyUtil.subtract(price, discount.getNumber2());
		return price;
	}
	/**
	 * 每满指定金额，优惠一定金额
	 * @param order
	 * @param discount
	 * @return
	 */
	public double getEveryMulitDiscountCount(OrderBean order,DiscountBean discount) {
		double price = getCount(order);
		int i = (int)price/(int)discount.getNumber1();
		price = MoneyUtil.subtract(price, discount.getNumber2()*i);
		return price;
	}
	/**
	 * 满额折扣
	 */
	public double getFixDiscountCount(OrderBean order,DiscountBean discount) {
		double price = getCount(order);
		price = MoneyUtil.multiply(price, discount.getNumber2());
		return price;
	}
	/**
	 * 会员折扣
	 * @param price
	 * @param order
	 * @return
	 */
	public double getVipDiscountCount(double price,OrderBean order) {
		double res = price;
		//会员等级为0时，不计算
		if(order.getMember().getVip()!=0) {
			double discount = vipDao.selectVipById(order.getMember().getVip()).getDiscount();
			res = MoneyUtil.multiply(price, discount);
		}
		return res;
	}
}
