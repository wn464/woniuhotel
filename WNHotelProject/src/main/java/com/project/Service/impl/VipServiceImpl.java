package com.project.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.IVipService;
import com.project.bean.VipBean;
import com.project.dao.IVipDao;
@Service
public class VipServiceImpl implements IVipService {

	@Autowired
	private IVipDao vipDao;
	@Override
	public int insertVip(VipBean vip) {
		int result = vipDao.insertVip(vip);
		return result;
	}

	@Override
	public int updateVip(VipBean vip) {
		int result = vipDao.updateVip(vip);
		return result;
	}

	@Override
	public int deletVip(int id) {
		int result = vipDao.deletVip(id);
		return result;
	}

	@Override
	public VipBean selectVipById(int id) {
		VipBean vip = vipDao.selectVipById(id);
		return vip;
	}

	@Override
	public VipBean selectVipByMoney(double money) {
		//查询所有大于当前金额的vip
		List<VipBean> list = vipDao.selectVipByMoney(money);
		VipBean vip = null;
		for (VipBean vipBean : list) {
			if(vip==null) {
				vip=vipBean;
				continue;
			}
			//查询最小的金额
			if(vip.getMaxMoney()>vipBean.getMaxMoney()) {
				vip=vipBean;
			}
		}
		return vip;
	}

	@Override
	public List<VipBean> selectVipAll() {
		List<VipBean> list =vipDao.selectVipAll(); 
		return list;
	}

}
