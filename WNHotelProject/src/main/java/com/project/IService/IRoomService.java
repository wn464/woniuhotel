package com.project.IService;

import java.util.List;

import com.project.bean.MarkBean;
import com.project.bean.RoomBean;
import com.project.bean.TypeBean;

/***
 * 房间业务层接口
 * @author zxc
 *
 */
public interface IRoomService {
	/**
	 * 通过房间id查询房间详细信息
	 * @param id房间id
	 * @return
	 */
public RoomBean selectroombyid(int id);
/**
 * 根据类型查找房间返回房间集合
 * @param type房间类型对象
 * @return
 */
public List<RoomBean> selectroombytypeid(TypeBean type);
/**
 * 修改房间状态，传入状态对象
 * @param mark状态对象
 * @return
 */
public int updatestatus(MarkBean mark);

}
