package com.project.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Service.ICommentService;
import com.project.bean.CommentBean;
import com.project.bean.PageBean;
import com.project.dao.ICommentDao;
@Service
public class CommentServiceImpl implements ICommentService{
	@Autowired
	private ICommentDao dao;

	@Override
	public void insertComment(CommentBean bean) {
		dao.insert(bean);
		
	}

	@Override
	public PageBean selectComment(int page,int size) {
		PageBean bean = new PageBean();
		int totalNumber = dao.selectAll();
		List<CommentBean> list = dao.selectComment((page-1)*size,size);
		bean.setList(list);
		bean.setPage(page);
		bean.setSize(size);
		bean.setTotalNumber(totalNumber);
		bean.setTotalPage((totalNumber%size==0)?(totalNumber/size+1):(totalNumber/size));
		return bean;
	}

}
