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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	public Users getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		Users user = userRepository.findByUsername(userDetails.getUsername());

		if (user == null) {
			String errorMessage = "User " + userDetails.getUsername() + " was not found!";
			System.out.println(errorMessage);
			throw new UsernameNotFoundException(errorMessage);
		}

		return user;
	}

	public Users register(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public boolean verified() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return auth.isAuthenticated();
	}

	public boolean doesUsernameExist(String username) {
		Users user = userRepository.findByUsername(username);
		return user != null;
	}
}
