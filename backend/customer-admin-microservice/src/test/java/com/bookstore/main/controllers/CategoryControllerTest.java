package com.bookstore.main.controllers;

import static org.mockito.Mockito.when;

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

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @MockBean
    private BookService bookService;

    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    @Test
    void testGetBooks() throws Exception {
        when(this.categoryService.getAllCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/category");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

