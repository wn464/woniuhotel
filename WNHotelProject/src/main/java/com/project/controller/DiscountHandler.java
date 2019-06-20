package com.project.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Service.IDiscountService;
import com.project.bean.DiscountBean;


/**
 * 优惠的handler
 * @author my
 *
 */
@RestController
public class DiscountHandler {

	@Autowired
	private IDiscountService discountService;
	@GetMapping("/user/discount")
	/**
	 * 
	 * @param price 费用
	 * @param vipId vip等级
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
	@GetMapping("/admin/allDiscount")
	public List<DiscountBean>  selectDiscountAll(){
		List<DiscountBean> list = discountService.selectDiscountAll();
		return list;
	}
	/**
	 * 添加优惠
	 * @param discount 优惠
	 * @return 影响行数
	 */
	@PostMapping("/admin/discount")
	public int insertDiscount(DiscountBean discount) {
		int res = discountService.insertDiscount(discount);
		return res;
	}
	/**
	 * 修改优惠
	 * @param discount 优惠
	 * @return 影响行数
	 */
	@PutMapping("/admin/discount")
	public int updateDiscount(DiscountBean discount) {
		int res = discountService.updateDiscount(discount);
		return res;
	}
	/**
	 * 删除优惠
	 * @param id 优惠的id
	 * @return  影响行数
	 */
	@DeleteMapping("/admin/discount")
	public int deleteDiscountById(int id) {
		int res = discountService.deletDiscount(id);
		return res;
	}
}
