package com.concretepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IUserDAO;
import com.concretepage.entity.User;
@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDAO userDAO;
	@Override
	public User getUserById(int userId) {
		User obj = userDAO.getUserById(userId);
		return obj;
	}
	@Override
	public User/*boolean*/ login(String email, String password) {
		User obj = userDAO.login(email, password);
		return obj;
	}
	@Override
	public User getUserByEmail(String userEmail) {
		User obj = userDAO.getUserByEmail(userEmail);
		return obj;
	}
	@Override
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
	}
	@Override
	public synchronized boolean createUser(User user){
       if (userDAO.userExists(user.getEmail(), user.getPassword())) {
    	   return false;
       } else {
    	   userDAO.createUser(user);
    	   return true;
       }
	}
	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
	@Override
	public void deleteUser(int userId) {
		userDAO.deleteUser(userId);
	}
}
