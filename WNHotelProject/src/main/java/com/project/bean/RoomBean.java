package com.project.bean;

/**
 * 房间信息
 * @author my
 *
 */
public class RoomBean {

	private int id;//主键id
	private String name;//房间名
	private TypeBean type;//房间类型
	private String img;//图片名称
	private int status;//房间状态码
	private String phone;//房间电话
	private String location;//详细位置
	private String message;//描述
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
	public TypeBean getType() {
		return type;
	}
	public void setType(TypeBean type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "RoomBean [id=" + id + ", name=" + name + ", img=" + img + ", status=" + status + ", phone=" + phone
				+ ", location=" + location + ", message=" + message + "]";
	}
	
}
