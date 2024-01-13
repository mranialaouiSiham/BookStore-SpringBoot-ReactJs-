package com.bookstore.main.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RoleTest {
    @Test
    void testConstructor() {
        Role actualRole = new Role();
        actualRole.setId(1);
        actualRole.setName(ERole.ROLE_USER);
        assertEquals(1, actualRole.getId().intValue());
        assertEquals(ERole.ROLE_USER, actualRole.getName());
    }

    @Test
    void testConstructor2() {
        Role actualRole = new Role(ERole.ROLE_USER);
        actualRole.setId(1);
        actualRole.setName(ERole.ROLE_USER);
        assertEquals(1, actualRole.getId().intValue());
        assertEquals(ERole.ROLE_USER, actualRole.getName());
    }
}

