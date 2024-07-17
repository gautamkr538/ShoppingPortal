package com.springrest.shoppingportal.Controller;

import com.springrest.shoppingportal.entities.User;
import com.springrest.shoppingportal.exceptions.UserNotFoundException;
import com.springrest.shoppingportal.exceptions.UserRegistrationException;
import com.springrest.shoppingportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public com.springrest.shoppingportal.entities.User registerUser(@RequestBody User user) {
        try {
            return userService.registerUser(user);
        } catch (UserRegistrationException e) {
            throw new UserRegistrationException("Error registering user: " + e.getMessage());
        }
    }

    @GetMapping("/find")
    public com.springrest.shoppingportal.entities.User findByEmail(@RequestParam String email) {
        try {
            return userService.findByEmail(email);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Error finding user: " + e.getMessage());
        }
    }
}

