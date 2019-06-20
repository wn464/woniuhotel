package com.project.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public static int countTime(Timestamp time1,Timestamp time2) {
		long date1 = time1.getTime();
		long date2 = time2.getTime();
		long res = (date2-date1)/(1000*60*60);
		long i = (res%24>0)?(res/24)+1:(res/24);
		System.out.println("一共入住"+res+"小时，取整"+i+"天");
		return (int) res;
	}
	
}
