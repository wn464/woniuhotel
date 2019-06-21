 package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.bean.DiscountTypeBean;
import com.project.dao.IDiscountTypeDao;

/**
 * 获得优惠类型
 * @author my
 *
 */
@Controller
public class DiscountTypeHandler {

	@Autowired
	private IDiscountTypeDao discountTypeDao;
	/**
	 * 获得所有优惠类型
	 * @return
	 */
	@GetMapping("/admin/discountType")
	@ResponseBody
	public List<DiscountTypeBean> selectDiscountTypeAll(){
		List<DiscountTypeBean> list = discountTypeDao.selectDiscountTypeAll();
		return list;	
	}
	/**
	 * 查询单一优惠
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/discountType/{id}")
	@ResponseBody
	public DiscountTypeBean selectDiscountTypeByid(@PathVariable int id) {
		DiscountTypeBean discountType = discountTypeDao.selectDiscountTypeById(id);
		return discountType;
	}
	
}
