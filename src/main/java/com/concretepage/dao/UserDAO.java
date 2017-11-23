package com.concretepage.dao;
import java.util.List;

import javax.persistence.EntityManager;
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
