package org.example;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getById(int id);
    User getByEmail(String email);

    boolean delete(int id);
    boolean create(User user);
    boolean update(User user);

}
