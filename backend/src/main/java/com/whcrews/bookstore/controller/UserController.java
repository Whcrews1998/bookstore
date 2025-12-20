package com.whcrews.bookstore.controller;

import com.whcrews.bookstore.model.Users;
import com.whcrews.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/whoami")
    public String whoami() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
       if (userService.doesUsernameExist(user.getUsername())) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists!");
       }

       return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(user));
    }

    @GetMapping("/verify")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> verify() {
        return ResponseEntity.ok("Success");
    }

}
