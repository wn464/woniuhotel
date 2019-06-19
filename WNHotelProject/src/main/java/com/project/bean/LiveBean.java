package com.project.bean;

import java.sql.Timestamp;
/**
 * 居住的房间
 * @author my
 *
 */
public class LiveBean {

	private int id;//主键
	private RoomBean room;//房间
	private PeopleBean people;//开房人
	private int number;//入住人数
	private Timestamp inTime;//入住时间
	private Timestamp outTime;//退房时间
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
	public PeopleBean getPeople() {
		return people;
	}
	public void setPeople(PeopleBean people) {
		this.people = people;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Timestamp getInTime() {
		return inTime;
	}
	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}
	public Timestamp getOutTime() {
		return outTime;
	}
	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}
	@Override
	public String toString() {
		return "LiveBean [id=" + id + ", room=" + room + ", people=" + people + ", number=" + number + ", inTime="
				+ inTime + ", outTime=" + outTime + "]";
	}
	
	
}
