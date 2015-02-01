package com.csc.fresher.java.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csc.fresher.java.domain.User;

/**
 * DAO class for Account entity. This class contains all methods that
 * inserts/updates/deletes user info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("userDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class UserDAO {
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	@SuppressWarnings("unchecked")
	public User getUser(int id) {
		User user = null;
		try {
			user = entityManager.find(User.class, id);
			if (user == null) {
				throw new EntityNotFoundException("Can't find User for ID "
						+ id);
			}
			System.out.println(user.toString() + "getUser-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting user had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return user;

	}


	public User getUserbyUserName(String username) {
		User user = null;
		try {
			TypedQuery<User> query = entityManager.createQuery("SELECT c FROM "
					+ User.class.getName() + " c where c.userName=:username",
					User.class);
			query.setParameter("username", username);
			user = query.getSingleResult();
			if (user == null) {
				throw new EntityNotFoundException(
						"Can't find User for UserName " + username);
			}
			System.out.println(user.toString() + "getUser-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting user had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return user;

	}

	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		try {
			TypedQuery<User> query = entityManager.createQuery("SELECT c FROM "
					+ User.class.getName() + " c", User.class);

			list = query.getResultList();
			System.out.println("Get All Users");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");

		}
		return list;

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public boolean createUser(User user) {
		boolean check = false;

		try {

			// Insert a row to User table
			entityManager.persist(user);
			check = true;

		} catch (Exception e) {
			System.out.println("\nGet Error with Create User " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Update User information.
	 *
	 * @param User
	 *            an User to be updated.
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public boolean updateUser(User User) {

		boolean check = false;
		try {
			entityManager.merge(User);
			check = true;
			System.out.println("User " + User.getUserName() + "updated");
		} catch (Exception e) {
			System.out.println("\nUpdate User get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Delete User by their Id.
	 *
	 * @param UserId
	 *            the User Id.
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public boolean deleteUserById(int UserId) {
		boolean check = false;
		try {
			User User = entityManager.find(User.class, UserId);
			if (User == null) {
				check = false;
				throw new EntityNotFoundException("Can't find User for ID "
						+ UserId);
			}
			System.out.println(User.toString());
			if (User != null) {

				entityManager.remove(User);
				check = true;
				System.out.println("delete User by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete User by ID get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Delete User entity.
	 *
	 * @param User
	 *            the object to be deleted.
	 */
	public boolean deleteUser(User User) {
		boolean check = false;
		try {
			entityManager.remove(User);
			check = true;
			System.out.println("delete user by User");
		} catch (Exception e) {
			System.out.println("\nDelete User get Error" + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}
}
