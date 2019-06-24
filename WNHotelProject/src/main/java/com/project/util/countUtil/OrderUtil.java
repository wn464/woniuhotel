package com.project.util.countUtil;


import java.util.List;

import org.springframework.stereotype.Component;

import com.project.Service.IDiscountService;
import com.project.Service.IVipService;
import com.project.bean.DiscountBean;
import com.project.bean.OrderBean;
import com.project.util.MoneyUtil;

@Component
public class OrderUtil extends OrderCount{

	public OrderUtil(IVipService vipService, IDiscountService discountService) {
		super(vipService, discountService);
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
		price = switchCount(price, discount);
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
		
		price = switchCount(price, discount);
		System.out.println("会员折扣之前："+price);
		return getVipDiscountCount(price, vipId);
	}
	protected double switchCount(double price,DiscountBean discount) {
		switch (discount.getDiscountType()) {
		case 1:
			price = getFullDiscountCount(price, discount);
			System.out.println("优惠"+price);
			break;
		case 2:
			price = getEveryMulitDiscountCount(price, discount);
			System.out.println("优惠"+price);
			break;
		case 3:
			price = getFixDiscountCount(price, discount);
			System.out.println("优惠"+price);
			break;
		default:
			System.out.println("优惠"+price);
			break;
		}
		return price;
	}
	public double getUnderLineMoney(double price,int vipId) throws Exception {
		List<DiscountBean> list = selectDiscounts(price, vipId);
		double price1=price;
		
		for (DiscountBean discountBean : list) {
			double price2 = getUnderLineMoney(price, discountBean.getId(), vipId);
			if(price1>price2) {
				price1=price2;
			}
		}
		if (list.size()==0) {
			price1 =getVipDiscountCount(price1, vipId);
		}
		return price1;
	}
	public double getOnLineMoney(double price,int vipId) throws Exception {
		double price1 = getUnderLineMoney(price, vipId);
		discountService.selectDiscountById(1).getNumber2();
		price1 = switchCount(price1, discountService.selectDiscountById(1));
		return price1;
	}
	public double getUnderLineMoney(double price) throws Exception {
		return getUnderLineMoney(price, 0);
	}
}
