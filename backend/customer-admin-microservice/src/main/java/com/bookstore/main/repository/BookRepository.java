package com.bookstore.main.repository;

import com.bookstore.main.models.Book;
import com.bookstore.main.models.Category;
import com.bookstore.main.models.ERole;
import com.bookstore.main.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategory(Category category);
}
