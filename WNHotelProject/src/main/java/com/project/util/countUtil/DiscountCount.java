package com.project.util.countUtil;

import java.util.List;



import com.project.Service.IDiscountService;
import com.project.Service.IVipService;
import com.project.bean.DiscountBean;
import com.project.bean.LiveBean;
import com.project.bean.OrderBean;
import com.project.util.MoneyUtil;

public class DiscountCount {
	
	protected IVipService vipService;
	
	protected IDiscountService discountService;
	
	
	public DiscountCount(IVipService vipService, IDiscountService discountService) {
		super();
		this.vipService = vipService;
		this.discountService = discountService;
	}
	/**
	 * 初步统计金额
	 * @param order
	 * @return
	 */
	public static double  getCount(OrderBean order) {
		double price = 0;
		
		//double roomPriceCount;//房间价格计算
		List<LiveBean> lives = order.getLives();
		for (LiveBean liveBean : lives) {
			double roomPrice = liveBean.getRoom().getPrice();
			//roomPriceCount=MoneyUtil.multiply(roomPrice, TimeUtil.countTime(liveBean.getInTime(), liveBean.getOutTime()))
			System.out.println("房间价格"+roomPrice);
			price = MoneyUtil.add(price, roomPrice);
		}
		System.out.println("初步计算价格："+price);
		return price;
	}
	protected List<DiscountBean> selectDiscounts(double price,int vipId){
		System.out.println(price);
		System.out.println(vipId);
		List<DiscountBean> list = discountService.selectDiscountByVipIdAndPrice(vipId, price);
		return list;
	}
	/**
	 * 满减计算
	 * @param order
	 * @param discount
	 * @return
	 */
	protected double getFullDiscountCount(OrderBean order,DiscountBean discount) {
		//double price = getCount(order);
		double price = order.getPrice();
		price = MoneyUtil.subtract(price, discount.getNumber2());
		return price;
	}
	protected double getFullDiscountCount(double price,DiscountBean discount) {
		price = MoneyUtil.subtract(price, discount.getNumber2());
		return price;
	}
	/**
	 * 每满指定金额，优惠一定金额
	 * @param order
	 * @param discount
	 * @return
	 */
	protected double getEveryMulitDiscountCount(OrderBean order,DiscountBean discount) {
		//double price = getCount(order);
		double price = order.getPrice();
		int i = (int)price/(int)discount.getNumber1();
		price = MoneyUtil.subtract(price, discount.getNumber2()*i);
		return price;
	}
	protected double getEveryMulitDiscountCount(double price,DiscountBean discount) {
		int i = (int)price/(int)discount.getNumber1();
		price = MoneyUtil.subtract(price, discount.getNumber2()*i);
		return price;
	}
	/**
	 * 满额折扣
	 */
	protected double getFixDiscountCount(OrderBean order,DiscountBean discount) {
		//double price = getCount(order);
		double price = order.getPrice();
		price = MoneyUtil.multiply(price, discount.getNumber2());
		return price;
	}
	protected double getFixDiscountCount(double price,DiscountBean discount) {
		price = MoneyUtil.multiply(price, discount.getNumber2());
		return price;
	}
	/**
	 * 会员折扣
	 * @param price
	 * @param order
	 * @return
	 */
	protected double getVipDiscountCount(double price,OrderBean order) {
		double res = price;
		//会员等级为0时，不计算
		if(order.getMember()!=null && order.getMember().getVip()!=0) {
			double discount = vipService.selectVipById(order.getMember().getVip()).getDiscount();
			res = MoneyUtil.multiply(price, discount);
		}
		return res;
	}
	protected double getVipDiscountCount(double price,int vipId) {
		double res = price;
		//会员等级为0时，不计算
		if(vipId!=0) {
			double discount = vipService.selectVipById(vipId).getDiscount();
			res = MoneyUtil.multiply(price, discount);
		}
		System.out.println("vip折扣后"+res);
		return res;
	}
}
