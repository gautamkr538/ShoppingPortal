package com.springrest.shoppingportal.services;

import com.springrest.shoppingportal.Dao.UserDao;
import com.springrest.shoppingportal.entities.User;
import com.springrest.shoppingportal.exceptions.UserNotFoundException;
import com.springrest.shoppingportal.exceptions.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User registerUser(User user) {
        try {
            return userDao.save(user);
        } catch (Exception e) {
            throw new UserRegistrationException("Failed to register user: " + e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email) {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        return user;
    }

	
}
