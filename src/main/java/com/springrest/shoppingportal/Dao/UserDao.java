package com.springrest.shoppingportal.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.shoppingportal.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    User findByEmail(String email);
    User save(User user);
}

