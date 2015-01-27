package com.csc.fresher.java.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.UserRoleDAO;

import com.csc.fresher.java.domain.UserRole;

/**
 * UserRole Service class call method from DAO for controller to do get Data or
 * business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("userRoleService")
public class UserRoleService {

	UserRoleDAO UserRoleDAO;

	public UserRoleDAO getUserRoleDAO() {
		return UserRoleDAO;
	}

	@Autowired
	public void setUserRoleDAO(UserRoleDAO UserRoleDAO) {
		this.UserRoleDAO = UserRoleDAO;
	}

	public UserRole getUserRole(int id) {
		return this.getUserRoleDAO().getUserRole(id);
	}

	public List<UserRole> getAllUserRole() {
		return this.getUserRoleDAO().getAllUserRole();
	}

	public String getUserRolebyUserRoleName(String UserRolename) {
		return this.getUserRoleDAO().getUserRolebyUserName(UserRolename);
	}

	public boolean createUserRole(UserRole UserRole) {
		return this.getUserRoleDAO().createUserRole(UserRole);
	}

	public boolean updateUserRole(UserRole UserRole) {
		return this.getUserRoleDAO().updateUserRole(UserRole);
	}

	/**
	 * Delete UserRole by their Id.
	 *
	 * @param UserRoleId
	 *            the UserRole Id.
	 */
	public boolean deleteUserRoleById(int UserRoleId) {
		return this.getUserRoleDAO().deleteUserRoleById(UserRoleId);
	}

	/**
	 * Delete UserRole entity.
	 *
	 * @param UserRole
	 *            the object to be deleted.
	 */
	public boolean deleteUserRole(UserRole UserRole) {
		return this.getUserRoleDAO().deleteUserRole(UserRole);
	}
}
