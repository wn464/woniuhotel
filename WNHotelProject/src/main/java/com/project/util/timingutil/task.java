package com.project.util.timingutil;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Service.IOrderService;

public class task extends TimerTask {
	@Autowired
 private IOrderService service;
	private int orderid;
	public task(int ordernumber) {
		this.orderid=ordernumber;
	}
	@Override
	public void run() {
	
	}

}
