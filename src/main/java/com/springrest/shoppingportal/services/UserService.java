package com.springrest.shoppingportal.services;


import com.springrest.shoppingportal.entities.User;

public interface UserService {

	
	    User findByEmail(String email);
		
		User registerUser(User user);

		
		
}
