package com.whcrews.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whcrews.bookstore.model.Book;
import com.whcrews.bookstore.model.UserBook;
import com.whcrews.bookstore.model.Users;
import com.whcrews.bookstore.repository.UserBookRepository;

@Service
public class UserBookService {

	@Autowired
	private UserBookRepository userBookRepository;

	public UserBook saveUserBook(UserBook userBook) {
		return userBookRepository.save(userBook);
	}

	public List<Book> getUserBooks(Users user) {
		return userBookRepository.findBooksByUser(user);
	}
}
