package com.bookstore.main.controllers;

import com.bookstore.main.models.Book;
import com.bookstore.main.services.BookService;
import com.bookstore.main.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }



}
