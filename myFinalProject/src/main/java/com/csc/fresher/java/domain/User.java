package com.csc.fresher.java.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity class for user table. This is a simple POJO class with annotations
 * to define mapping with DB table
 * 
 * @author Nguyen Anh Minh
 *
 */
@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = "user_name")})
public class User implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "enable")
	private int enable;
	
	@ManyToMany( mappedBy = "transactions")
	private Set<Transaction> users;
	public User() {

	}


	public Set<Transaction> getUsers() {
		return users;
	}


	public void setUsers(Set<Transaction> users) {
		this.users = users;
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


	public int getEnable() {
		return enable;
	}


	public void setEnable(int enable) {
		this.enable = enable;
	}


	
	public User(int userId, String userName, String password, int enable) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", enable=" + enable
				+"]";
	}






}
