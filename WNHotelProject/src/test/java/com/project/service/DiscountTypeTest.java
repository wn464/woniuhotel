package com.project.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.Service.IDiscountTypeService;
import com.project.demo.WnHotelProjectApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WnHotelProjectApplication.class)
public class DiscountTypeTest {

	@Autowired
	private IDiscountTypeService discountTypeService;
	@Test
	public void test1() {
		System.out.println(discountTypeService.selectDiscountTypeAll());
		System.out.println(discountTypeService.selectDiscountTypeById(1));
	}
}
