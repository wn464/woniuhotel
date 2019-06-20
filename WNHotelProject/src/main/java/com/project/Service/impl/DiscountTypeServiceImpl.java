package com.project.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.IDiscountTypeService;
import com.project.bean.DiscountTypeBean;
import com.project.dao.IDiscountTypeDao;
@Service
public class DiscountTypeServiceImpl implements IDiscountTypeService {

	@Autowired
	private IDiscountTypeDao discountTypeDao;
	@Override
	public List<DiscountTypeBean> selectDiscountTypeAll() {
		List<DiscountTypeBean> list =discountTypeDao.selectDiscountTypeAll();
		return list;
	}

	@Override
	public DiscountTypeBean selectDiscountTypeById(int id) {
		DiscountTypeBean discountType = discountTypeDao.selectDiscountTypeById(id);
		return discountType;
	}

}
