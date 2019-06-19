package com.project.bean;

/**
 * 入住的个人单位
 * @author my
 *
 */
public class PeopleBean {

	private int id;//主键id
	private String name;//姓名
	private String idCard;//身份证号
	private int gender;//性别
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "PeopleBean [id=" + id + ", name=" + name + ", idCard=" + idCard + ", gender=" + gender + "]";
	}
	
	
	
}
