package com.project.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.Service.IDiscountService;
import com.project.bean.DiscountBean;
import com.project.demo.WnHotelProjectApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WnHotelProjectApplication.class)
public class DiscountTest {

	@Autowired
	private IDiscountService discountService;
	@Test
	public void test1() throws ParseException {
		DiscountBean discount = new DiscountBean();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm.SSS");
		String d1 = "2018-8-21 20:00:00.00";
		String d2 = "2018-8-22 12:00:00.00";
		Date beginTime = df.parse(d1);
		Date endTime = df.parse(d2);
		discount.setBeginTime(new Timestamp(beginTime.getTime()));
		discount.setEndTime(new Timestamp(endTime.getTime()));
		discount.setDiscountType(3);
		discount.setName("满两千，9折优惠");
		discount.setNumber1(2000);
		discount.setNumber2(0.9);
		System.out.println(discountService.insertDiscount(discount));
		System.out.println(discountService.selectDiscountById(1));
		System.out.println(discountService.selectDiscountByVipIdAndPrice(2, 5000));
	}
}
