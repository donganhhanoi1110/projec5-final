package com.csc.fresher.java.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.csc.fresher.java.domain.User;

@Service
public class UserService {

	@PersistenceContext
	private EntityManager em;
	
	public String checkLogin(String id, String pass) {
		
			String check="";
					TypedQuery<User> query = em
							.createQuery(
									"SELECT c FROM "
											+ User.class.getName()
											+ " c Where c.userName= :loginId and c.password= :password",
											User.class);
					query.setParameter("loginId", id);
					query.setParameter("password", pass);
					User a= query.getSingleResult();
					System.out.println(a.toString());
				
				return a.getUserName()+"_"+a.getPassword();
		
			}
}
