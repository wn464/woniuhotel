package com.project.Service;

import java.util.List;

import com.project.bean.VipBean;

public interface IVip {

	/**
	 * 添加vip
	 */
	public int insertVip(VipBean vip);
	/**
	 * 修改vip
	 */
	public int updateVip(VipBean vip);
	/**
	 * 删除vip
	 */
	public int deletVip(int id);
	/**
	 * 查询vip，通过id
	 */
	public VipBean selectVipById(int id);
	/**
	 * 查询vip，通过金额
	 */
	public VipBean selectVipByMoney(double money);
	/**
	 * 查询所有vip
	 */
	public List<VipBean> selectVipAll();
}
