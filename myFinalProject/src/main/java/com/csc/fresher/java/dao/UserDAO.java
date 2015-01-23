package com.csc.fresher.java.dao;

import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csc.fresher.java.domain.User;

/**
 * DAO class for Account entity. This class contains all methods that
 * inserts/updates/deletes account info
 * 
 * @author nvu3
 *
 */
@Repository("userDAO")
public class UserDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public String getUser(String id, String pass) {
		User a=new User();
		try{
		String check = "";
		TypedQuery<User> query = entityManager
				.createQuery(
						"SELECT c FROM "
								+ User.class.getName()
								+ " c Where c.userName= :loginId and c.password= :password",
						User.class);
		query.setParameter("loginId", id);
		query.setParameter("password", pass);
		 a = query.getSingleResult();
		System.out.println(a.toString());
		}catch(Exception e)
		{
			System.out.println("Get Error");
			return "Get error, Your account is not right";
		}
		return a.getUserName() + "_" + a.getPassword();

	}
}
