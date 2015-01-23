package com.csc.fresher.java.DAO;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.csc.fresher.java.controller.EntityManagerFactoryUtil;
import com.csc.fresher.java.domain.User;



/**
 * DAO class for Account entity. This class contains all methods that
 * inserts/updates/deletes account info
 * 
 * @author nvu3
 *
 */
@Component
public class UserDAO {

	public boolean checkLogin(String id, String pass) {

		// Obtains entity manager object
		EntityManager entityManager = EntityManagerFactoryUtil
				.createEntityManager();

		// Obtains transaction from entity manager
		EntityTransaction entr = entityManager.getTransaction();

		// -----------Begin transaction-----------
		List<User> users= null;
		boolean check = false;
		try {
			entr.begin();
			// Get a list of accounts from DB

			TypedQuery<User> query = entityManager
					.createQuery(
							"SELECT c FROM "
									+ User.class.getName()
									+ " c Where c.userName= :loginId and c.password= :password",
									User.class);
			query.setParameter("loginId", id);
			query.setParameter("password", pass);
			User a= query.getSingleResult();
			System.out.println(a.toString());
			check = query.getResultList().size() > 0;
			entr.commit();
		} catch (Exception e) {
			entityManager.close();
		}
		// -----------End transaction-----------

		return check;

	}


	public User getCustomerByLoginId(String id) {

		// Obtains entity manager object
		EntityManager entityManager = EntityManagerFactoryUtil
				.createEntityManager();

		// Obtains transaction from entity manager
		EntityTransaction entr = entityManager.getTransaction();

		// -----------Begin transaction-----------
		User user = null;
		try {
			entr.begin();
			// Get a list of accounts from DB

			TypedQuery<User> query = entityManager.createQuery(
					"SELECT c FROM " + User.class.getName()
							+ " c Where c.userName= :loginId", User.class);
			query.setParameter("loginId", id);

			user = query.getSingleResult();

			entr.commit();
		} catch (Exception e) {
			entityManager.close();
		}
		// -----------End transaction-----------

		return user;

	}
	public List<User> getCustomerProfileByLoginId(String id) {

		// Obtains entity manager object
		EntityManager entityManager = EntityManagerFactoryUtil
				.createEntityManager();

		// Obtains transaction from entity manager
		EntityTransaction entr = entityManager.getTransaction();

		// -----------Begin transaction-----------
		List<User> user = null;
		try {
			entr.begin();
			// Get a list of accounts from DB

			TypedQuery<User> query = entityManager.createQuery(
					"SELECT c FROM " + User.class.getName()
							+ " c Where c.userName= :loginId", User.class);
			query.setParameter("loginId", id);

			user = query.getResultList();

			entr.commit();
		} catch (Exception e) {
			entityManager.close();
		}
		// -----------End transaction-----------

		return user;

	}
	
	public boolean checkUpdatePassword(String id, String pass) {

		// Obtains entity manager object
		EntityManager entityManager = EntityManagerFactoryUtil
				.createEntityManager();

		// Obtains transaction from entity manager
		EntityTransaction entr = entityManager.getTransaction();

		// -----------Begin transaction-----------

		boolean check = false;
		int updateCount = 0;

		try {
			entr.begin();

			String sqlQuery = "UPDATE "
					+ User.class.getName()
					+ " c SET c.password= :pass WHERE c.userName= :loginId";
			Query query = entityManager.createQuery(sqlQuery);
			query.setParameter("pass", pass);
			query.setParameter("loginId", id);

			updateCount = query.executeUpdate();
			if (updateCount > 0) {
				check = true;
			}
			entr.commit();
		} catch (Exception e) {
			System.out.println(" " + e.getMessage());
			entityManager.close();
		}
		// -----------End transaction-----------

		return check;

	}
}
