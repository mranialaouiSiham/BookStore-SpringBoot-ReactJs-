package com.bookstore.main.controllers;

import com.bookstore.main.services.AuthorService;
import com.bookstore.main.services.BookService;
import com.bookstore.main.services.CategoryService;
import com.bookstore.main.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/language")
public class LanguageController {

    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    AuthorService authorService;

    @Autowired
    LanguageService languageService;
    @GetMapping("")
    public ResponseEntity<?> getLanguage() {
        return ResponseEntity.ok(languageService.getAllLanguage());
    }



}
