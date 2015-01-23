package com.csc.fresher.java.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for customer table. This is a simple POJO class with annotations
 * to define mapping with DB table
 * 
 * @author vuhuynh
 *
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id")
	private int userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "enable")
	private String enable;


	public User() {

	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEnable() {
		return enable;
	}


	public void setEnable(String enable) {
		this.enable = enable;
	}


	public User(int userId, String userName, String password, String enable) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.enable = enable;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", enable=" + enable + "]";
	}



}
