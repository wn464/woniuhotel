package com.project.utilTest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.project.util.TimeUtil;

public class TimeUtilTest {

	@Test
	public void test() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm.SSS");
		String d1 = "2018-8-21 20:00:00.00";
		String d2 = "2018-8-22 12:00:00.00";
		Date date1 = df.parse(d1);
		Date date2 = df.parse(d2);
		TimeUtil.countTime(new Timestamp(date1.getTime()), new Timestamp(date2.getTime()));
	}
}
