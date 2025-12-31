package com.whcrews.bookstore.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.whcrews.bookstore.model.Roles;
import com.whcrews.bookstore.model.Users;
import com.whcrews.bookstore.service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (!userService.doesUsernameExist("user")) {
            Users user = new Users();
            user.setUsername("user");
            user.setPassword("pass");
            user.getUserRoles().add(Roles.ROLE_USER);
            userService.register(user);
        }

        if (!userService.doesUsernameExist("admin")) {
            Users user = new Users();
            user.setUsername("admin");
            user.setPassword("pass");
            user.getUserRoles().add(Roles.ROLE_ADMIN);
            userService.register(user);
        }

    }

}
