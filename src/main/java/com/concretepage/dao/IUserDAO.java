package com.concretepage.dao;
import java.util.List;
import com.concretepage.entity.User;
public interface IUserDAO {
    List<User> getAllUsers();
    User getUserById(int userId);
    User getUserByEmail(String userEmail);
    /*boolean*/User login(String email, String password);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    boolean userExists(String email, String password);
}
 