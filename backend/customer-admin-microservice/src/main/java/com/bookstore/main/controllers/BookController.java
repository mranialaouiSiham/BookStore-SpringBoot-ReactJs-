package com.bookstore.main.controllers;

import com.bookstore.main.models.Book;
import com.bookstore.main.payload.request.BookRequest;
import com.bookstore.main.payload.response.MessageResponse;
import com.bookstore.main.repository.AuthorRepository;
import com.bookstore.main.repository.BookRepository;
import com.bookstore.main.repository.LanguageRepository;
import com.bookstore.main.services.BookService;
import com.bookstore.main.services.CategoryService;
import com.bookstore.main.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    LanguageRepository languageRepository;


    @Autowired
    BookRepository bookRepository;

    @GetMapping("")
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @GetMapping("category/{category}")
    public ResponseEntity<?> getBooksByCategory(@PathVariable String category) {
        return ResponseEntity.ok(bookService.getBooksByCategory(category));
    }
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookRequest bookRequest) {
        System.out.println("bookRequest = " + bookRequest);
//

        try{
            Book book=new Book();
            book.setTitle(bookRequest.getTitle());
            book.setDescription(bookRequest.getDescription());
            book.setImageUrl(bookRequest.getImageUrl());
            book.setAuthor( authorRepository.getById(bookRequest.getAuthor()));
            book.setLanguage(languageRepository.getById(bookRequest.getAuthor()));
            book.setCategory( categoryService.getCategory(bookRequest.getCategory()));
            book.setPrice( bookRequest.getPrice());
            return ResponseEntity.ok(bookService.addBook(book));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Error"+e.getMessage()));
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        try{
            Book book=bookRepository.findById(id).orElseThrow(
                    ()->new RuntimeException("book not found")
            );
            book.setTitle(bookRequest.getTitle());
            book.setDescription(bookRequest.getDescription());
            book.setImageUrl(bookRequest.getImageUrl());
            book.setAuthor( authorRepository.getById(bookRequest.getAuthor()));
            book.setLanguage(languageRepository.getById(bookRequest.getAuthor()));
            book.setCategory( categoryService.getCategory(bookRequest.getCategory()));
            book.setPrice( bookRequest.getPrice());
            return ResponseEntity.ok(bookService.addBook(book));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Error"+e.getMessage()));
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id){
        return ResponseEntity.ok(bookService.delete(id));
    }


}
