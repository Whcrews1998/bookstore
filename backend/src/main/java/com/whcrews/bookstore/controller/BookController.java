package com.whcrews.bookstore.controller;

import com.whcrews.bookstore.model.Book;
import com.whcrews.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    private String uploadDirectory = "/app/images/";

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestParam("author") String author, @RequestParam("title") String title, @RequestParam("file") MultipartFile file) {

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
