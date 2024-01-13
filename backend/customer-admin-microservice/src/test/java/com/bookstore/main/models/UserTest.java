package com.bookstore.main.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setId(123L);
        actualUser.setPassword("iloveyou");
        HashSet<Role> roleSet = new HashSet<>();
        actualUser.setRoles(roleSet);
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals(123L, actualUser.getId().longValue());
        assertEquals("iloveyou", actualUser.getPassword());
        assertSame(roleSet, actualUser.getRoles());
    }

    @Test
    void testConstructor2() {
        User actualUser = new User("jane.doe@example.org", "iloveyou");
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setId(123L);
        actualUser.setPassword("iloveyou");
        HashSet<Role> roleSet = new HashSet<>();
        actualUser.setRoles(roleSet);
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals(123L, actualUser.getId().longValue());
        assertEquals("iloveyou", actualUser.getPassword());
        assertSame(roleSet, actualUser.getRoles());
    }
}

