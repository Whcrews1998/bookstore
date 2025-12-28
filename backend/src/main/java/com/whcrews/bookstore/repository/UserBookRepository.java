package com.whcrews.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whcrews.bookstore.model.UserBook;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
}
