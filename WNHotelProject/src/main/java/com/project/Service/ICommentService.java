package com.project.Service;

import com.project.bean.CommentBean;
import com.project.bean.OrderBean;
//评论业务层接口
public interface ICommentService {
	//添加评论
	public void insertComment(CommentBean bean);
	//查询评论
	public CommentBean selectComment();

}
