package com.whcrews.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import com.whcrews.bookstore.model.Book;
import com.whcrews.bookstore.model.UserBook;
import com.whcrews.bookstore.model.Users;
import com.whcrews.bookstore.repository.UserBookRepository;

@Service
@Transactional
public class UserBookService {

	@Autowired
	private UserBookRepository userBookRepository;

	@Autowired
	private UserService userService;

	public UserBook saveUserBook(UserBook userBook) {
		return userBookRepository.save(userBook);
	}

	public List<Book> getUserBookList(Users user) {
		return userBookRepository.findBooksByUser(user);
	}

	public UserBook findByUserAndBook(Users user, Book book) {
		return userBookRepository.findByUserAndBook(user, book);
	}

	public void returnUserBook(Book book) {
		Users user = userService.getCurrentUser();
		UserBook userBook = userBookRepository.findByUserAndBook(user, book);

		if (userBook == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book is not checked out by user.");
		}

		user.getUserBookSet().remove(userBook);
	}
}
