package com.csc.fresher.java.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

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
	@PersistenceContext
	private EntityManager em;

	public String checkLogin(String id, String pass) {

		String check = "";
		TypedQuery<User> query = em
				.createQuery(
						"SELECT c FROM "
								+ User.class.getName()
								+ " c Where c.userName= :loginId and c.password= :password",
						User.class);
		query.setParameter("loginId", id);
		query.setParameter("password", pass);
		User a = query.getSingleResult();
		System.out.println(a.toString());

		return a.getUserName() + "_" + a.getPassword();

	}
}
