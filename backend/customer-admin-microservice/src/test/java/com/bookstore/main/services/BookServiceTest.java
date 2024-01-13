package com.bookstore.main.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookstore.main.models.Author;
import com.bookstore.main.models.Book;
import com.bookstore.main.models.Category;
import com.bookstore.main.models.Language;
import com.bookstore.main.repository.BookRepository;
import com.bookstore.main.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookService.class})
@ExtendWith(SpringExtension.class)
class BookServiceTest {
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void testAddBook() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language);
        book.setCategory(category);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        when(this.bookRepository.save((Book) any())).thenReturn(book);

        Language language1 = new Language();
        language1.setLanguage("en");
        language1.setId(123L);

        Category category1 = new Category();
        category1.setId(123L);
        category1.setName("Name");

        Author author1 = new Author();
        author1.setId(123L);
        author1.setAuthor("JaneDoe");

        Book book1 = new Book();
        book1.setLanguage(language1);
        book1.setCategory(category1);
        book1.setAuthor(author1);
        book1.setPrice(10.0);
        book1.setId(123L);
        book1.setImageUrl("https://example.org/example");
        book1.setTitle("Dr");
        book1.setDescription("The characteristics of someone or something");
        assertSame(book, this.bookService.addBook(book1));
        verify(this.bookRepository).save((Book) any());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }

    @Test
    void testGetBook() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language);
        book.setCategory(category);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        Optional<Book> ofResult = Optional.of(book);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(book, this.bookService.getBook(123L));
        verify(this.bookRepository).findById((Long) any());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }

    @Test
    void testGetBook2() {
        when(this.bookRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.bookService.getBook(123L));
        verify(this.bookRepository).findById((Long) any());
    }

    @Test
    void testGetAllBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        when(this.bookRepository.findAll()).thenReturn(bookList);
        List<Book> actualAllBooks = this.bookService.getAllBooks();
        assertSame(bookList, actualAllBooks);
        assertTrue(actualAllBooks.isEmpty());
        verify(this.bookRepository).findAll();
    }

    @Test
    void testUpdateBook() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language);
        book.setCategory(category);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        Optional<Book> ofResult = Optional.of(book);

        Language language1 = new Language();
        language1.setLanguage("en");
        language1.setId(123L);

        Category category1 = new Category();
        category1.setId(123L);
        category1.setName("Name");

        Author author1 = new Author();
        author1.setId(123L);
        author1.setAuthor("JaneDoe");

        Book book1 = new Book();
        book1.setLanguage(language1);
        book1.setCategory(category1);
        book1.setAuthor(author1);
        book1.setPrice(10.0);
        book1.setId(123L);
        book1.setImageUrl("https://example.org/example");
        book1.setTitle("Dr");
        book1.setDescription("The characteristics of someone or something");
        when(this.bookRepository.save((Book) any())).thenReturn(book1);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult);

        Language language2 = new Language();
        language2.setLanguage("en");
        language2.setId(123L);

        Category category2 = new Category();
        category2.setId(123L);
        category2.setName("Name");

        Author author2 = new Author();
        author2.setId(123L);
        author2.setAuthor("JaneDoe");

        Book book2 = new Book();
        book2.setLanguage(language2);
        book2.setCategory(category2);
        book2.setAuthor(author2);
        book2.setPrice(10.0);
        book2.setId(123L);
        book2.setImageUrl("https://example.org/example");
        book2.setTitle("Dr");
        book2.setDescription("The characteristics of someone or something");
        assertSame(book1, this.bookService.updateBook(123L, book2));
        verify(this.bookRepository).findById((Long) any());
        verify(this.bookRepository).save((Book) any());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }

    @Test
    void testUpdateBook2() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language);
        book.setCategory(category);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        when(this.bookRepository.save((Book) any())).thenReturn(book);
        when(this.bookRepository.findById((Long) any())).thenReturn(Optional.empty());

        Language language1 = new Language();
        language1.setLanguage("en");
        language1.setId(123L);

        Category category1 = new Category();
        category1.setId(123L);
        category1.setName("Name");

        Author author1 = new Author();
        author1.setId(123L);
        author1.setAuthor("JaneDoe");

        Book book1 = new Book();
        book1.setLanguage(language1);
        book1.setCategory(category1);
        book1.setAuthor(author1);
        book1.setPrice(10.0);
        book1.setId(123L);
        book1.setImageUrl("https://example.org/example");
        book1.setTitle("Dr");
        book1.setDescription("The characteristics of someone or something");
        assertThrows(RuntimeException.class, () -> this.bookService.updateBook(123L, book1));
        verify(this.bookRepository).findById((Long) any());
    }

    @Test
    void testGetBooksByCategory() {
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(this.categoryRepository.findByName((String) any())).thenReturn(ofResult);
        ArrayList<Book> bookList = new ArrayList<>();
        when(this.bookRepository.findByCategory((Category) any())).thenReturn(bookList);
        List<Book> actualBooksByCategory = this.bookService.getBooksByCategory("Category Name");
        assertSame(bookList, actualBooksByCategory);
        assertTrue(actualBooksByCategory.isEmpty());
        verify(this.categoryRepository).findByName((String) any());
        verify(this.bookRepository).findByCategory((Category) any());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }

    @Test
    void testGetBooksByCategory2() {
        when(this.categoryRepository.findByName((String) any())).thenReturn(Optional.empty());
        when(this.bookRepository.findByCategory((Category) any())).thenReturn(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> this.bookService.getBooksByCategory("Category Name"));
        verify(this.categoryRepository).findByName((String) any());
    }

    @Test
    void testDelete() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language);
        book.setCategory(category);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        Optional<Book> ofResult = Optional.of(book);
        doNothing().when(this.bookRepository).delete((Book) any());
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(book, this.bookService.delete(123L));
        verify(this.bookRepository).delete((Book) any());
        verify(this.bookRepository).findById((Long) any());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.bookRepository).delete((Book) any());
        when(this.bookRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.bookService.delete(123L));
        verify(this.bookRepository).findById((Long) any());
    }
}

