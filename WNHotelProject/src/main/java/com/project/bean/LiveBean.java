package com.project.bean;

import java.sql.Timestamp;
import java.util.List;
/**
 * 居住的房间
 * @author my
 *
 */
public class LiveBean {

	private int id;//主键
	private RoomBean room;//房间
	private int roomid;
	private int orderid;
	private String people;//开房人名字
	private int number;//入住人数
	private String inTime;//入住时间
	private String outTime;//退房时间
	private String phoneNumber;//电话号码
	private List<PeopleBean> peoples;//入住人员
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getRoomid() {
		return roomid;
	}
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	public List<PeopleBean> getPeoples() {
		return peoples;
	}
	public void setPeoples(List<PeopleBean> peoples) {
		this.peoples = peoples;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RoomBean getRoom() {
		return room;
	}
	public void setRoom(RoomBean room) {
		this.room = room;
	}
	
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "LiveBean [id=" + id + ", room=" + room + ", roomid=" + roomid + ", orderid=" + orderid + ", people="
				+ people + ", number=" + number + ", inTime=" + inTime + ", outTime=" + outTime + ", phoneNumber="
				+ phoneNumber + ", peoples=" + peoples + "]";
	}
	
	
	
}
