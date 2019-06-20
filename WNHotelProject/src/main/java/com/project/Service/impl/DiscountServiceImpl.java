package com.project.Service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.IDiscountService;
import com.project.bean.DiscountBean;
import com.project.bean.OrderBean;
import com.project.dao.IDiscountDao;
import com.project.util.countUtil.OrderUtil;
@Service
public class DiscountServiceImpl implements IDiscountService {

	@Autowired
	private IDiscountDao discountDao;
	@Override
	public int deletDiscount(int id) {
		int result = discountDao.deletDiscountById(id);
		return result;
	}

	@Override
	public int insertDiscount(DiscountBean discount) {
		int result = discountDao.insertDiscount(discount);
		return result;
	}

	@Override
	public DiscountBean selectDiscountById(int id) {
		DiscountBean discount = discountDao.selectDiscountById(id);
		return discount;
	}

	@Override
	public List<DiscountBean> selectDiscountByName(String name) {
		String newName = new String("%"+name+"%");
		List<DiscountBean> list = discountDao.selectDiscountByName(newName);
		return list;
	}

	@Override
	public int updateDiscount(DiscountBean discount) {
		int result = discountDao.updateDiscount(discount);
		return result;
	}

	@Override
	public List<DiscountBean> selectDiscountAll() {
		List<DiscountBean> list = discountDao.selectDiscountAll();
		return list;
	}

	@Override
	public List<DiscountBean> selectDiscountByPrice(double price) {
		List<DiscountBean> list = discountDao.selectDiscountByPriceOrVip(price, 0, new Timestamp(System.currentTimeMillis()));
		return list;
	}

	@Override
	public List<DiscountBean> selectDiscountByVipId(int id) {
		List<DiscountBean> list = discountDao.selectDiscountByPriceOrVip(0, id, new Timestamp(System.currentTimeMillis()));
		return list;
	}

	@Override
	public List<DiscountBean> selectDiscountByOrder(OrderBean order) {
		List<DiscountBean> list = discountDao.selectDiscountByPriceOrVip(OrderUtil.getCount(order), order.getMember().getVip().getId(), new Timestamp(System.currentTimeMillis()));
		return list;
	}

}
