package com.bookstore.main.repository;

import com.bookstore.main.models.Author;
import com.bookstore.main.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
