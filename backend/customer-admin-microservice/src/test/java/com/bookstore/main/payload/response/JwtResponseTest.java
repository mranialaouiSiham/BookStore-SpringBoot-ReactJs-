package com.bookstore.main.payload.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class JwtResponseTest {
    @Test
    void testConstructor() {
        ArrayList<String> stringList = new ArrayList<>();
        JwtResponse actualJwtResponse = new JwtResponse("ABC123", 123L, "janedoe", "jane.doe@example.org", stringList);
        actualJwtResponse.setAccessToken("ABC123");
        actualJwtResponse.setEmail("jane.doe@example.org");
        actualJwtResponse.setId(123L);
        actualJwtResponse.setTokenType("ABC123");
        actualJwtResponse.setUsername("janedoe");
        assertEquals("ABC123", actualJwtResponse.getAccessToken());
        assertEquals("jane.doe@example.org", actualJwtResponse.getEmail());
        assertEquals(123L, actualJwtResponse.getId().longValue());
        assertSame(stringList, actualJwtResponse.getRoles());
        assertEquals("ABC123", actualJwtResponse.getTokenType());
        assertEquals("janedoe", actualJwtResponse.getUsername());
    }
}

