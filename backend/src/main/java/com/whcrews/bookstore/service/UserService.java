package com.whcrews.bookstore.service;

import com.whcrews.bookstore.model.Users;
import com.whcrews.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);

        if (user == null) {
            String errorMessage = "User " + username + " was not found!";
            System.out.println(errorMessage);
            throw new UsernameNotFoundException(errorMessage);
        }

        return user;
    }


    public boolean verified() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

       return auth.isAuthenticated();
    }
}
