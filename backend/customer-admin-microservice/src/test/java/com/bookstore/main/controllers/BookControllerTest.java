package com.bookstore.main.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.bookstore.main.models.Author;
import com.bookstore.main.models.Book;
import com.bookstore.main.models.Category;
import com.bookstore.main.models.Language;
import com.bookstore.main.payload.request.BookRequest;
import com.bookstore.main.repository.AuthorRepository;
import com.bookstore.main.repository.BookRepository;
import com.bookstore.main.repository.LanguageRepository;
import com.bookstore.main.services.BookService;
import com.bookstore.main.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BookController.class})
@ExtendWith(SpringExtension.class)
class BookControllerTest {
    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private BookController bookController;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookService bookService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private LanguageRepository languageRepository;

    @Test
    void testGetBook() throws Exception {
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
        when(this.bookService.getBook((Long) any())).thenReturn(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/book/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"imageUrl\":\"https"
                                        + "://example.org/example\",\"author\":{\"id\":123,\"author\":\"JaneDoe\"},\"language\":{\"id\":123,\"language\":\"en\"}"
                                        + ",\"category\":{\"id\":123,\"name\":\"Name\"},\"price\":10.0}"));
    }

    @Test
    void testGetBooksByCategory() throws Exception {
        when(this.bookService.getBooksByCategory((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/book/category/{category}",
                "Category");
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetBooksByCategory2() throws Exception {
        when(this.bookService.getBooksByCategory((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/book/category/{category}",
                "Category");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testAddBook() throws Exception {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        when(this.languageRepository.getById((Long) any())).thenReturn(language);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        when(this.categoryService.getCategory((Long) any())).thenReturn(category);

        Language language1 = new Language();
        language1.setLanguage("en");
        language1.setId(123L);

        Category category1 = new Category();
        category1.setId(123L);
        category1.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language1);
        book.setCategory(category1);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        when(this.bookService.addBook((Book) any())).thenReturn(book);

        Author author1 = new Author();
        author1.setId(123L);
        author1.setAuthor("JaneDoe");
        when(this.authorRepository.getById((Long) any())).thenReturn(author1);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setLanguage(1L);
        bookRequest.setPrice(10.0);
        bookRequest.setCategory(1L);
        bookRequest.setImageUrl("https://example.org/example");
        bookRequest.setAuthor(1L);
        bookRequest.setTitle("Dr");
        bookRequest.setDescription("The characteristics of someone or something");
        String content = (new ObjectMapper()).writeValueAsString(bookRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"imageUrl\":\"https"
                                        + "://example.org/example\",\"author\":{\"id\":123,\"author\":\"JaneDoe\"},\"language\":{\"id\":123,\"language\":\"en\"}"
                                        + ",\"category\":{\"id\":123,\"name\":\"Name\"},\"price\":10.0}"));
    }

    @Test
    void testAddBook2() throws Exception {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        when(this.languageRepository.getById((Long) any())).thenReturn(language);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        when(this.categoryService.getCategory((Long) any())).thenReturn(category);
        when(this.bookService.addBook((com.bookstore.main.models.Book) any())).thenThrow(new RuntimeException("foo"));

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");
        when(this.authorRepository.getById((Long) any())).thenReturn(author);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setLanguage(1L);
        bookRequest.setPrice(10.0);
        bookRequest.setCategory(1L);
        bookRequest.setImageUrl("https://example.org/example");
        bookRequest.setAuthor(1L);
        bookRequest.setTitle("Dr");
        bookRequest.setDescription("The characteristics of someone or something");
        String content = (new ObjectMapper()).writeValueAsString(bookRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Errorfoo\"}"));
    }

    @Test
    void testGetBooks() throws Exception {
        when(this.bookService.getAllBooks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/book");
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetBooks2() throws Exception {
        when(this.bookService.getAllBooks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/book");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateBook() throws Exception {
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
        when(this.bookService.delete((Long) any())).thenReturn(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/book/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"imageUrl\":\"https"
                                        + "://example.org/example\",\"author\":{\"id\":123,\"author\":\"JaneDoe\"},\"language\":{\"id\":123,\"language\":\"en\"}"
                                        + ",\"category\":{\"id\":123,\"name\":\"Name\"},\"price\":10.0}"));
    }

    @Test
    void testUpdateBook2() throws Exception {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        when(this.languageRepository.getById((Long) any())).thenReturn(language);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        when(this.categoryService.getCategory((Long) any())).thenReturn(category);

        Language language1 = new Language();
        language1.setLanguage("en");
        language1.setId(123L);

        Category category1 = new Category();
        category1.setId(123L);
        category1.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language1);
        book.setCategory(category1);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        when(this.bookService.addBook((Book) any())).thenReturn(book);

        Language language2 = new Language();
        language2.setLanguage("en");
        language2.setId(123L);

        Category category2 = new Category();
        category2.setId(123L);
        category2.setName("Name");

        Author author1 = new Author();
        author1.setId(123L);
        author1.setAuthor("JaneDoe");

        Book book1 = new Book();
        book1.setLanguage(language2);
        book1.setCategory(category2);
        book1.setAuthor(author1);
        book1.setPrice(10.0);
        book1.setId(123L);
        book1.setImageUrl("https://example.org/example");
        book1.setTitle("Dr");
        book1.setDescription("The characteristics of someone or something");
        Optional<Book> ofResult = Optional.of(book1);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult);

        Author author2 = new Author();
        author2.setId(123L);
        author2.setAuthor("JaneDoe");
        when(this.authorRepository.getById((Long) any())).thenReturn(author2);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setLanguage(1L);
        bookRequest.setPrice(10.0);
        bookRequest.setCategory(1L);
        bookRequest.setImageUrl("https://example.org/example");
        bookRequest.setAuthor(1L);
        bookRequest.setTitle("Dr");
        bookRequest.setDescription("The characteristics of someone or something");
        String content = (new ObjectMapper()).writeValueAsString(bookRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/book/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"imageUrl\":\"https"
                                        + "://example.org/example\",\"author\":{\"id\":123,\"author\":\"JaneDoe\"},\"language\":{\"id\":123,\"language\":\"en\"}"
                                        + ",\"category\":{\"id\":123,\"name\":\"Name\"},\"price\":10.0}"));
    }

    @Test
    void testUpdateBook3() throws Exception {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        when(this.languageRepository.getById((Long) any())).thenReturn(language);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        when(this.categoryService.getCategory((Long) any())).thenReturn(category);
        when(this.bookService.addBook((Book) any())).thenThrow(new RuntimeException("foo"));

        Language language1 = new Language();
        language1.setLanguage("en");
        language1.setId(123L);

        Category category1 = new Category();
        category1.setId(123L);
        category1.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language1);
        book.setCategory(category1);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        Optional<Book> ofResult = Optional.of(book);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult);

        Author author1 = new Author();
        author1.setId(123L);
        author1.setAuthor("JaneDoe");
        when(this.authorRepository.getById((Long) any())).thenReturn(author1);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setLanguage(1L);
        bookRequest.setPrice(10.0);
        bookRequest.setCategory(1L);
        bookRequest.setImageUrl("https://example.org/example");
        bookRequest.setAuthor(1L);
        bookRequest.setTitle("Dr");
        bookRequest.setDescription("The characteristics of someone or something");
        String content = (new ObjectMapper()).writeValueAsString(bookRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/book/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Errorfoo\"}"));
    }

    @Test
    void testUpdateBook4() throws Exception {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        when(this.languageRepository.getById((Long) any())).thenReturn(language);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        when(this.categoryService.getCategory((Long) any())).thenReturn(category);

        Language language1 = new Language();
        language1.setLanguage("en");
        language1.setId(123L);

        Category category1 = new Category();
        category1.setId(123L);
        category1.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language1);
        book.setCategory(category1);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        when(this.bookService.addBook((Book) any())).thenReturn(book);
        when(this.bookRepository.findById((Long) any())).thenReturn(Optional.empty());

        Author author1 = new Author();
        author1.setId(123L);
        author1.setAuthor("JaneDoe");
        when(this.authorRepository.getById((Long) any())).thenReturn(author1);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setLanguage(1L);
        bookRequest.setPrice(10.0);
        bookRequest.setCategory(1L);
        bookRequest.setImageUrl("https://example.org/example");
        bookRequest.setAuthor(1L);
        bookRequest.setTitle("Dr");
        bookRequest.setDescription("The characteristics of someone or something");
        String content = (new ObjectMapper()).writeValueAsString(bookRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/book/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Errorbook not found\"}"));
    }
}

