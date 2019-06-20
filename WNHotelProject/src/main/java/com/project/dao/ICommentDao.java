package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.bean.CommentBean;
import com.project.bean.OrderBean;
//评论持久层接口
public interface ICommentDao {
	//添加评论
	@Insert("insert into comment(orderId,message)values(#{orderId.orderNumber},#{message})")
	public void insert(CommentBean bean);
    //查询评论
	@Select("select * from comment")
	public CommentBean selectComment();
}
