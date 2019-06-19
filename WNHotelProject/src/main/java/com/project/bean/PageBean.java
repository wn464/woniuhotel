package com.project.bean;

import java.util.List;
/**
 * 分页信息
 * @author x1c
 *
 */
public class PageBean {

	private int page;
	private int size;
	private int totalNumber;
	private int totalPage;
	private List<?> list;
	
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String toString() {
		return "PageBean [page=" + page + ", size=" + size + ", totalNumber=" + totalNumber + ", totalPage=" + totalPage
				+ ", list=" + list + "]";
	}
	
}
