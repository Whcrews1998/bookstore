package com.whcrews.bookstore.service;

import com.whcrews.bookstore.model.Book;
import com.whcrews.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public Book find(String author, String title) {
		return bookRepository.findByTitleAndAuthor(title, author);
	}
}
