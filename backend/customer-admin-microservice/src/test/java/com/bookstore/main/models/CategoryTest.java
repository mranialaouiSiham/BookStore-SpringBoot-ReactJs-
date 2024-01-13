package com.bookstore.main.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CategoryTest {
    @Test
    void testConstructor() {
        Category actualCategory = new Category();
        actualCategory.setId(123L);
        actualCategory.setName("Name");
        assertEquals(123L, actualCategory.getId().longValue());
        assertEquals("Name", actualCategory.getName());
    }
}

