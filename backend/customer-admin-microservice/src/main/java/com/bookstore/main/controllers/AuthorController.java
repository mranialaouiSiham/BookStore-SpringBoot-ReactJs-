package com.bookstore.main.controllers;

import com.bookstore.main.services.AuthorService;
import com.bookstore.main.services.BookService;
import com.bookstore.main.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    AuthorService authorService;
    @GetMapping("")
    public ResponseEntity<?> getAuthor() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }



}
