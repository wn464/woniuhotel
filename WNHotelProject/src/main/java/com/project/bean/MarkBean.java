package com.project.bean;

/**
 * mark表，提供查询用
 * @author my
 *
 */
public class MarkBean {

	private int id;//主键
	private String name;//名称
	@Override
	public String toString() {
		return "MarkBean [id=" + id + ", name=" + name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
