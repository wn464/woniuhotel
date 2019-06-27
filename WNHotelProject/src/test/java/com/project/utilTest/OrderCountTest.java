package com.project.utilTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.Service.IDiscountService;
import com.project.Service.IOrderService;
import com.project.Service.IVipService;
import com.project.bean.DiscountBean;
import com.project.bean.LiveBean;
import com.project.bean.OrderBean;
import com.project.dao.IOrderDao;
import com.project.dao.IVipDao;
import com.project.demo.WnHotelProjectApplication;
import com.project.util.countUtil.OrderUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = WnHotelProjectApplication.class)
public class OrderCountTest {

	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IDiscountService discountService;
	@Autowired
	private IVipDao vipDao;
	@Autowired
	private IVipService vipService;
	
	@Test
	public void test() {
		OrderUtil orderutil = new OrderUtil(vipService, discountService);
//		LiveBean live = new LiveBean();
//		live.setOrderid(1);
//		OrderBean order = orderDao.selectOrderByAttr(live);
		//System.out.println("list="+list);
//		OrderBean order=null;
//		for (OrderBean orderBean : list) {
//			 order = orderBean;
//		}
//		System.out.println("order"+order);
//		DiscountBean discount=discountService.selectDiscountById(1);
//		System.out.println("discount"+discount);
//		try {
//			System.out.println("费用"+orderutil.getUnderLineMoney(order, discount));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {

			System.out.println(orderutil.getOnLineMoney(200, 1));
			System.out.println(orderutil.getUnderLineMoney(5000, 0));

			//System.out.println(orderutil.getUnderLineMoney(5000));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
