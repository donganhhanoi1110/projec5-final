package com.csc.fresher.java.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.UserDAO;
import com.csc.fresher.java.domain.User;

@Service
public class UserService {
	
	UserDAO userDAO;
	
	
	
	public UserDAO getUserDAO() {
		return userDAO;
	}


	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}



	public String getUser(String id, String pass) {
		return this.getUserDAO().getUser(id, pass);
	}
	public List<User> getAllUser() {
		return this.getUserDAO().getAllUser();
	}
	public boolean createUser(User user) {
		return this.getUserDAO().createUser(user);
	}
}
