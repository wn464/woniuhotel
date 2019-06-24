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
	private MarkBean gender;
	private int liveId;  //入住的id
	private int gende;
	public int getLiveId() {
		return liveId;
	}
	public void setLiveId(int liveId) {
		this.liveId = liveId;
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public MarkBean getGender() {
		return gender;
	}
	public void setGender(MarkBean gender) {
		this.gender = gender;
	}
	
	public int getGende() {
		return gende;
	}
	public void setGende(int gende) {
		this.gende = gende;
	}
	@Override
	public String toString() {
		return "PeopleBean [id=" + id + ", name=" + name + ", idCard=" + idCard + ", gender=" + gender + ", liveId="
				+ liveId + ", gende=" + gende + "]";
	}
	
	
	
	
	
}
