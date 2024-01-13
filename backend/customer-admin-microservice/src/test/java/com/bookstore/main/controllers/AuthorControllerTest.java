package com.bookstore.main.controllers;

import static org.mockito.Mockito.when;

import com.bookstore.main.services.AuthorService;
import com.bookstore.main.services.BookService;
import com.bookstore.main.services.CategoryService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthorController.class})
@ExtendWith(SpringExtension.class)
class AuthorControllerTest {
    @Autowired
    private AuthorController authorController;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookService bookService;

    @MockBean
    private CategoryService categoryService;

    @Test
    void testGetAuthor() throws Exception {
        when(this.authorService.getAllAuthors()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/author");
        MockMvcBuilders.standaloneSetup(this.authorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

