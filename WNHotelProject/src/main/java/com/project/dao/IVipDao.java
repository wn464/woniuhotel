package com.project.dao;

import java.util.List;

import com.project.bean.VipBean;

public interface IVipDao {

	/**
	 * 添加vip
	 * @param vip
	 * @return
	 */
	public int insertVip(VipBean vip);
	/**
	 * 修改vip
	 * @param vip
	 * @return
	 */
	public int updateVip(VipBean vip);
	/**
	 * 删除vip
	 * @param id
	 * @return
	 */
	public int deletVip(int id);
	/**
	 * 查询vip通过id
	 * @param id
	 * @return
	 */
	public VipBean selectVipById(int id);
	/**
	 * 查询vip，通过价格
	 * @param money
	 * @return
	 */
	public VipBean selectVipByMoney(double money);
	/**
	 * 查询vip，全部
	 * @return
	 */
	public List<VipBean> selectVipAll();
}
