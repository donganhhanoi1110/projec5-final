package com.csc.fresher.java.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.UserDAO;
import com.csc.fresher.java.domain.User;

/**
 * User Service class call method from DAO for controller to do get Data or business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("userService")
public class UserService {
	
	UserDAO userDAO;
	
	
	
	public UserDAO getUserDAO() {
		return userDAO;
	}


	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}



	public User getUser(int id) {
		return this.getUserDAO().getUser(id);
	}
	
	public List<User> getAllUser() {
		return this.getUserDAO().getAllUser();
	}
	public User getUserbyUserName(String username) {
		return this.getUserDAO().getUserbyUserName(username);
	}
	public boolean createUser(User user) {
		return this.getUserDAO().createUser(user);
	}
	
	 public boolean updateUser(User User) {
	    	return this.getUserDAO().updateUser(User);
	    }
	 
	    /**
	     * Delete User by their Id.
	     *
	     * @param UserId the User Id.
	     */
	    public boolean deleteUserById(int UserId) {
	       return this.getUserDAO().deleteUserById(UserId);
	    }
	 
	    /**
	     * Delete User entity.
	     *
	     * @param User the object to be deleted.
	     */
	    public boolean deleteUser(User User) {
	    	return this.getUserDAO().deleteUser(User);
	    }
}
