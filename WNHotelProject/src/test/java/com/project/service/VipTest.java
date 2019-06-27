package com.project.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.Service.IMemberService;
import com.project.Service.IVipService;
import com.project.bean.VipBean;
import com.project.demo.WnHotelProjectApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = WnHotelProjectApplication.class)
public class VipTest {

	@Autowired
	private IVipService vipService;
	@Autowired
	private IMemberService service;
	
	@Test
	public void test1() {
		List<VipBean> list = vipService.selectVipAll();
		for (VipBean vipBean : list) {
			System.out.println(vipBean);
		}
//		vipService.selectVipById(3);
//		vipService.insertVip(vip);
//		vipService.selectVipByMoney(money);
//		vipService.updateVip(vip);
//		vipService.deletVip(3);
	}
	@Test
	public void test2() {
		VipBean vip = vipService.selectVipById(3);
		System.out.println(vip);
		vip.setDiscount(0.8);
		vip.setMaxMoney(20000);
		vip.setName("超级尊贵会员");
		int res = vipService.insertVip(vip);
		System.out.println(res);
	}
	@Test
	public void test3() {
		VipBean vip = vipService.selectVipByMoney(10000);
		System.out.println(vip);
	}
	
	
	@Test
	public void test4() {
		int updateMoney = service.updateMoney(4000, 13);
		
	}
}
