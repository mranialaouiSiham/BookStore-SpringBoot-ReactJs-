package com.bookstore.main.controllers;

import static org.mockito.Mockito.when;

import com.bookstore.main.services.AuthorService;
import com.bookstore.main.services.BookService;
import com.bookstore.main.services.CategoryService;
import com.bookstore.main.services.LanguageService;

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

@ContextConfiguration(classes = {LanguageController.class})
@ExtendWith(SpringExtension.class)
class LanguageControllerTest {
    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookService bookService;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private LanguageController languageController;

    @MockBean
    private LanguageService languageService;

    @Test
    void testGetLanguage() throws Exception {
        when(this.languageService.getAllLanguage()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/language");
        MockMvcBuilders.standaloneSetup(this.languageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

