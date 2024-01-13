package com.bookstore.main.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LanguageTest {
    @Test
    void testConstructor() {
        Language actualLanguage = new Language();
        actualLanguage.setId(123L);
        actualLanguage.setLanguage("en");
        assertEquals(123L, actualLanguage.getId().longValue());
        assertEquals("en", actualLanguage.getLanguage());
    }
}

