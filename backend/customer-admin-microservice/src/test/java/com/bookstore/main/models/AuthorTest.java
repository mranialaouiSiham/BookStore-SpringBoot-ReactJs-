package com.bookstore.main.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AuthorTest {
    @Test
    void testConstructor() {
        Author actualAuthor = new Author();
        actualAuthor.setAuthor("JaneDoe");
        actualAuthor.setId(123L);
        assertEquals("JaneDoe", actualAuthor.getAuthor());
        assertEquals(123L, actualAuthor.getId().longValue());
    }
}

