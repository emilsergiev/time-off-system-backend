package com.concretepage.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.User;
@Transactional
@Repository
public class UserDAO implements IUserDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public User getUserById(int userId) {
		return entityManager.find(User.class, userId);
	}
	@Override
	public User getUserByEmail(String userEmail) {
		return entityManager.find(User.class, userEmail);
	}
	@Override
	public User/*boolean*/ login(String email, String password) {
		/*User resultingUser = null;
		System.out.println(entityManager.find(User.class, email));
		System.out.println(entityManager.find(User.class, password));
		if (entityManager.find(User.class, email) == entityManager.find(User.class, password)) {
			resultingUser = entityManager.find(User.class, email);
		}
		return entityManager.find(User.class, email);*/
		
		/*User userObj;
		for (int id = 0; id < 100; id++) {
			userObj = entityManager.find(User.class, id);
			if (userObj.getEmail().equals(email) && userObj.getPassword().equals(password)) {
				return entityManager.find(User.class, id);
			}
		}*/
		int matchingUserID = -1;
		//id = 1 is the admin
		int userCount = 20;
		for (int id = 1; id <= userCount; id++) {
			if (entityManager.find(User.class, id) != null) {
				System.out.println("ID: " + entityManager.find(User.class, id).getUserId() + 
						" Email: " + entityManager.find(User.class, id).getEmail() + 
						" Password: " + entityManager.find(User.class, id).getPassword());
				System.out.println(entityManager.find(User.class, 9).getEmail() == "lklk");
				if (entityManager.find(User.class, id).getEmail().equals(email) && entityManager.find(User.class, id).getPassword().equals(password)) {
					System.out.println("Match for user " + id);
					matchingUserID = id;
					return entityManager.find(User.class, matchingUserID);
				}
			}
		}
		//will connect to the error user with matchingUserID=-1
		return entityManager.find(User.class, matchingUserID);
		//return entityManager.find(User.class, matchingUserID);
		/*System.out.println("testing dao login");
		String hql = "FROM User as user WHERE user.email = ? and user.password = ?";
		Parameter<?> userID = entityManager.createQuery(hql).setParameter(1, email)
	              .setParameter(2, password).getParameter(1);
		System.out.println(entityManager.createQuery(hql).setParameter(1, email)
	              .setParameter(2, password).getParameter(1));
		return entityManager.find(User.class, userID);*/
		/*String hql = "FROM User as user WHERE user.email = ? and user.password = ?";
		int count = entityManager.createQuery(hql).setParameter(1, email)
		              .setParameter(2, password).getResultList().size();
		return count > 0 ? true : false;*/
		
		/*if(entityManager.find(User.class, email) != null && entityManager.find(User.class, password) != null) {
			return true;
		} else {
			return false;
		}*/
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "FROM User as user ORDER BY user.userId DESC";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void createUser(User user) {
		entityManager.persist(user);
	}
	@Override
	public void updateUser(User user) {
		User artcl = getUserById(user.getUserId());
		artcl.setEmail(user.getEmail());
		artcl.setPassword(user.getPassword());
		entityManager.flush();
	}
	@Override
	public void deleteUser(int userId) {
		entityManager.remove(getUserById(userId));
	}
	@Override
	public boolean userExists(String email, String password) {
		String hql = "FROM User as user WHERE user.email = ? and user.password = ?";
		int count = entityManager.createQuery(hql).setParameter(1, email)
		              .setParameter(2, password).getResultList().size();
		return count > 0 ? true : false;
	}
}
