package com.project.Service;

import com.project.bean.LiveBean;

/**
 * 入住信息
 * @author x1c
 *
 */
public interface ILiveService {

	//添加入住信息
	public int insertLiveBean(LiveBean liveBean);
	//删除入住信息
	public int deleteLiveBean(int lid);
	//查询入住信息
	public LiveBean selectLiveBean(LiveBean liveBean);
	//修改入住信息
	public int updateLiveBean(LiveBean liveBean);
}
