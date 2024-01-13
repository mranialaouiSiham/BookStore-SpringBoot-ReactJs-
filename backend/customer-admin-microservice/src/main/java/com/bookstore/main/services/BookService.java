package com.bookstore.main.services;

import com.bookstore.main.models.Book;
import com.bookstore.main.models.Category;
import com.bookstore.main.repository.BookRepository;
import com.bookstore.main.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book getBook(Long id){
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")
        );
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, Book book){
        Book book1 = getBook(id);
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setCategory(book.getCategory());
        book1.setLanguage(book.getLanguage());
        book1.setPrice(book.getPrice());
        return bookRepository.save(book1);
    }

    public List<Book> getBooksByCategory(String categoryName){
        Category category = categoryRepository.findByName(categoryName).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
        return bookRepository.findByCategory(category);
    }

    public Book delete(Long id){
        Book book=bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("book id not found.")
        );
        bookRepository.delete(book);
        return book;
    }


}
