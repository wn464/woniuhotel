package com.project.dao;

import org.apache.ibatis.annotations.Insert;

import com.project.bean.CommentBean;
//评论持久层接口
public interface ICommentDao {
	//添加评论
	@Insert("insert into comment(orderId,message)values(#{orderId.orderNumber},#{message})")
	public void addComment(CommentBean bean);

}
