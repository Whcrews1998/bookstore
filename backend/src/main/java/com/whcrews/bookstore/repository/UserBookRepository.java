package com.whcrews.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whcrews.bookstore.model.Book;
import com.whcrews.bookstore.model.UserBook;
import com.whcrews.bookstore.model.Users;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
	@Query("SELECT ub.book FROM UserBook ub WHERE ub.user = :user")
	List<Book> findBooksByUser(@Param("user") Users user);

	UserBook findByUserAndBook(Users user, Book book);

}
