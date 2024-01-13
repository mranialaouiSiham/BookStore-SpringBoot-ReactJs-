package com.bookstore.main.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class BookTest {
    @Test
    void testConstructor() {
        Book actualBook = new Book();
        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");
        actualBook.setAuthor(author);
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        actualBook.setCategory(category);
        actualBook.setDescription("The characteristics of someone or something");
        actualBook.setId(123L);
        actualBook.setImageUrl("https://example.org/example");
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        actualBook.setLanguage(language);
        actualBook.setPrice(10.0);
        actualBook.setTitle("Dr");
        assertSame(author, actualBook.getAuthor());
        assertSame(category, actualBook.getCategory());
        assertEquals("The characteristics of someone or something", actualBook.getDescription());
        assertEquals(123L, actualBook.getId().longValue());
        assertEquals("https://example.org/example", actualBook.getImageUrl());
        assertSame(language, actualBook.getLanguage());
        assertEquals(10.0, actualBook.getPrice());
        assertEquals("Dr", actualBook.getTitle());
    }
}

