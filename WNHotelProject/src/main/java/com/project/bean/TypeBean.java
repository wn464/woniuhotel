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
	private String area;//面积
	private String img;//类型图片
	private int people;//以居住人数
	private String seat;//房间所在楼层
	
	

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
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
	@Override
	public String toString() {
		return "TypeBean [id=" + id + ", name=" + name + ", message=" + message + ", area=" + area + ", img=" + img
				+ ", people=" + people + ", seat=" + seat + "]";
	}
	
}
