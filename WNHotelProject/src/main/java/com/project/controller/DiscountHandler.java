package com.project.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Service.IDiscountService;
import com.project.bean.DiscountBean;


@RestController
public class DiscountHandler {

	@Autowired
	private IDiscountService discountService;
	@GetMapping("/user/discount")
	/**
	 * 
	 * @param price 费用
	 * @param vipId 
	 * @return
	 */
	public List<DiscountBean> selectDiscountByPriceAndMember(Double price,int vipId){
		
		List<DiscountBean> list = discountService.selectDiscountByVipIdAndPrice(vipId, price);
		return list;
	}
	/**
	 * 查询所有优惠
	 * @return
	 */
	@GetMapping("/admin/AllDiscount")
	public List<DiscountBean>  selectDiscountAll(){
		List<DiscountBean> list = discountService.selectDiscountAll();
		return list;
	}
	public int add(DiscountBean discount) {
		return 0;
	}
}
