package com.bookstore.main.payload.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BookRequestTest {
    @Test
    void testConstructor() {
        BookRequest actualBookRequest = new BookRequest();
        actualBookRequest.setAuthor(1L);
        actualBookRequest.setCategory(1L);
        actualBookRequest.setDescription("The characteristics of someone or something");
        actualBookRequest.setImageUrl("https://example.org/example");
        actualBookRequest.setLanguage(1L);
        actualBookRequest.setPrice(10.0);
        actualBookRequest.setTitle("Dr");
        assertEquals(1L, actualBookRequest.getAuthor().longValue());
        assertEquals(1L, actualBookRequest.getCategory().longValue());
        assertEquals("The characteristics of someone or something", actualBookRequest.getDescription());
        assertEquals("https://example.org/example", actualBookRequest.getImageUrl());
        assertEquals(1L, actualBookRequest.getLanguage().longValue());
        assertEquals(10.0, actualBookRequest.getPrice());
        assertEquals("Dr", actualBookRequest.getTitle());
        assertEquals(
                "BookRequest(title=Dr, description=The characteristics of someone or something, imageUrl=https://example"
                        + ".org/example, author=1, language=1, category=1, price=10.0)",
                actualBookRequest.toString());
    }
}

