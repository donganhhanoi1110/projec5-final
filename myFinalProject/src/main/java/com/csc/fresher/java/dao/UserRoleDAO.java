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

import com.csc.fresher.java.domain.UserRole;

/**
 * DAO class for Account entity. This class contains all methods that
 * inserts/updates/deletes UserRole info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("userRoleDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class UserRoleDAO {
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	@SuppressWarnings("unchecked")
	public UserRole getUserRole(int id) {
		UserRole UserRole = null;
		try {
			UserRole = entityManager.find(UserRole.class, id);
			if (UserRole == null) {
				throw new EntityNotFoundException("Can't find UserRole for ID "
						+ id);
			}
			System.out.println(UserRole.toString() + "getUserRole-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting UserRole had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return UserRole;

	}


	public String getUserRolebyUserName(String username) {
		UserRole UserRole = null;
		String role="";
		try {
			TypedQuery<UserRole> query = entityManager.createQuery("SELECT c FROM "
					+ UserRole.class.getName() + " c where c.userName=:username",
					UserRole.class);
			query.setParameter("username", username);
			UserRole = query.getSingleResult();
			role=UserRole.getRole();
			if (UserRole == null) {
				throw new EntityNotFoundException(
						"Can't find UserRole for UserRoleName " + username);
			}
			System.out.println(UserRole.toString() + "getUserRole-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting UserRole had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return role;

	}

	public List<UserRole> getAllUserRole() {
		List<UserRole> list = new ArrayList<UserRole>();
		try {
			TypedQuery<UserRole> query = entityManager.createQuery("SELECT c FROM "
					+ UserRole.class.getName() + " c", UserRole.class);

			list = query.getResultList();
			System.out.println("Get All UserRoles");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");

		}
		return list;

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public boolean createUserRole(UserRole UserRole) {
		boolean check = false;

		try {

			// Insert a row to UserRole table
			entityManager.persist(UserRole);
			check = true;

		} catch (Exception e) {
			System.out.println("\nGet Error with Create UserRole " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Update UserRole information.
	 *
	 * @param UserRole
	 *            an UserRole to be updated.
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public boolean updateUserRole(UserRole UserRole) {

		boolean check = false;
		try {
			entityManager.merge(UserRole);
			check = true;
			System.out.println("UserRole " + UserRole.getUserName() + "updated");
		} catch (Exception e) {
			System.out.println("\nUpdate UserRole get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Delete UserRole by their Id.
	 *
	 * @param UserRoleId
	 *            the UserRole Id.
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public boolean deleteUserRoleById(int UserRoleId) {
		boolean check = false;
		try {
			UserRole UserRole = entityManager.find(UserRole.class, UserRoleId);
			if (UserRole == null) {
				check = false;
				throw new EntityNotFoundException("Can't find UserRole for ID "
						+ UserRoleId);
			}
			System.out.println(UserRole.toString());
			if (UserRole != null) {

				entityManager.remove(UserRole);
				check = true;
				System.out.println("delete UserRole by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete UserRole by ID get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Delete UserRole entity.
	 *
	 * @param UserRole
	 *            the object to be deleted.
	 */
	public boolean deleteUserRole(UserRole UserRole) {
		boolean check = false;
		try {
			entityManager.remove(UserRole);
			check = true;
			System.out.println("delete UserRole by UserRole");
		} catch (Exception e) {
			System.out.println("\nDelete UserRole get Error" + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}
}
