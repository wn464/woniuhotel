package com.project.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.project.config.AlipayConfig;

public class AlipayUtil {

	private static AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

	/**
	 * 统一收单交易支付接口
	 * @param out_trade_no 订单号
	 * @param total_amount 订单总金额
	 * @param subject 订单标题
	 * @return 返回一个表单提交，请内部派送给前端
	 * @throws AlipayApiException
	 */
	public String pay(String out_trade_no ,double total_amount,String subject) throws AlipayApiException  {
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		//同步回调
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		//异步回调
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizContent("{" +
				"\"out_trade_no\":\""+out_trade_no+"\"," +
				"\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
				"\"total_amount\":"+total_amount+"," +
				"\"subject\":\""+subject+"\"" +
		"  }");
		
		AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
		return response.getBody();
	}
	//退款
	/**
	 * 退款
	 * @param out_trade_no 订单号
	 * @param total_amount 订单总金额
	 * @throws AlipayApiException 
	 */
	public boolean refund(String out_trade_no,double total_amount) throws AlipayApiException {
		//详细文档：alipay.trade.page.pay
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" +
		//订单支付时传入的商户订单号,不能和 trade_no同时为空。
		"\"out_trade_no\":\""+out_trade_no+"\"," +
		//支付宝交易号，和商户订单号不能同时为空
		//"\"trade_no\":\"2014112611001004680073956707\"," +
		//需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
		"\"total_amount\":"+total_amount+"" +
		"  }");
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		return true;
		} else {
		return false;
		}
		
		
	}
	/**
	 * 返回响应
	 * @param request HttpServletRequest请求
	 * @return 返回支付宝订单
	 * @throws Exception 
	 */
	public String returnUrl(HttpServletRequest request) throws Exception {
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
		//调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
		
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	
		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
	
		//付款金额
		String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
		
		System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
	
		return trade_no;
			
		}else {
		throw new Exception("回调错误");
		}
		
	}
}
