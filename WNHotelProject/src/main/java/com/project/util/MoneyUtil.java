package com.project.util;

import java.math.BigDecimal;

public class MoneyUtil {

	private static int scale = 5;
	private static BigDecimal start = new BigDecimal(1);
	/**
	 * 相除
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static double divide(double number1,double number2) {
		BigDecimal num1 = new BigDecimal(number1);
		BigDecimal num2 = new BigDecimal(number2);
		BigDecimal res = num1.divide(num2, scale, BigDecimal.ROUND_HALF_UP);
		return res.doubleValue();
	}
	/**
	 * 相乘
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static double multiply(double number1,double number2) {
		BigDecimal num1 = new BigDecimal(number1);
		BigDecimal num2 = new BigDecimal(number2);
		BigDecimal res = num1.multiply(num2);
		return res.divide(start, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 
	 * 相加
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static double add(double number1,double number2) {
		BigDecimal num1 = new BigDecimal(number1);
		BigDecimal num2 = new BigDecimal(number2);
		BigDecimal res = num1.add(num2);
		System.out.println(num1+"+"+num2+"="+res);
		return res.doubleValue();
	}
	/**
	 * 相减
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static double subtract(double number1,double number2) {
		BigDecimal num1 = new BigDecimal(number1);
		BigDecimal num2 = new BigDecimal(number2);
		BigDecimal res = num1.subtract(num2);
		return res.doubleValue();
	}
	public static BigDecimal getBigDcimal(double number) {
		BigDecimal num = new BigDecimal(number);
		return num.divide(start, scale, BigDecimal.ROUND_HALF_UP);
	}
	
}
