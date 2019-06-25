package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.bean.CommentBean;
import com.project.bean.OrderBean;
//评论持久层接口
public interface ICommentDao {
	//添加评论
	@Insert("insert into comment(orderId,message,name,imgname)values(#{orderId.id},#{message},#{name},#{imgName})")
	public int insert(CommentBean bean);
    //查询评论
	@Select("select * from comment order by id DESC limit #{page},#{size} ")
	public List<CommentBean> selectComment(int page,int size);
	@Select("select count(*) from comment ")
	public int selectAll();
	@Select("select * from comment order by id DESC")
	public List<CommentBean> selectAllComment();
}
