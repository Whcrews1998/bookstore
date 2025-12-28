package com.whcrews.bookstore.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.whcrews.bookstore.model.Book;
import com.whcrews.bookstore.model.UserBook;
import com.whcrews.bookstore.model.Users;
import com.whcrews.bookstore.service.BookService;
import com.whcrews.bookstore.service.UserBookService;
import com.whcrews.bookstore.service.UserService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserBookService userBookService;

	private String uploadDirectory = "/app/images/";

	@GetMapping()
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@PostMapping("/return")
	public ResponseEntity<?> returnBook(@RequestBody Book book) {
		book = bookService.find(book.getAuthor(), book.getTitle());

		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User does not have book assigned to them.");
		}

		userBookService.returnUserBook(book);

		return ResponseEntity.ok("Book has been returned.");
	}

	@PostMapping("/checkout")
	public ResponseEntity<?> checkout(@RequestBody Book book) {
		book = bookService.find(book.getAuthor(), book.getTitle());

		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
		}

		Users user = userService.getCurrentUser();
		UserBook userBook = new UserBook(user, book);

		user.getUserBookSet().add(userBook);

		userBookService.saveUserBook(userBook);
		return ResponseEntity.ok("Book successfully checked out.");
	}

	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestParam("author") String author, @RequestParam("title") String title,
			@RequestParam("file") MultipartFile file) {

		try {
			String filename = file.getOriginalFilename();
			Path path = Paths.get(uploadDirectory + filename);
			Files.createDirectories(path.getParent());
			Files.write(path, file.getBytes());

			Book book = new Book();
			book.setAuthor(author);
			book.setTitle(title);
			book.setCoverImageName(filename);

			bookService.save(book);

			return ResponseEntity.ok("Book uploaded successfully");

		} catch (IOException ex) {

			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
		}
	}
}
