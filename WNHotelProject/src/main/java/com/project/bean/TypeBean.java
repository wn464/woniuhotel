package com.project.bean;

/**
 * 房间类型
 * @author my
 *
 */
public class TypeBean {

	private int id;//主键id
	private String name;//类型名称
	private String message;//类型描述
	@Override
	public String toString() {
		return "TypeBean [id=" + id + ", name=" + name + ", message=" + message + "]";
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
