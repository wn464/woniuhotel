package com.project.Service;

import java.util.List;

import com.project.bean.CommentBean;
import com.project.bean.OrderBean;
import com.project.bean.PageBean;
//评论业务层接口
public interface ICommentService {
	//添加评论
	public int insertComment(CommentBean bean);
	//查询评论
	public PageBean selectComment(int page,int size);

}
