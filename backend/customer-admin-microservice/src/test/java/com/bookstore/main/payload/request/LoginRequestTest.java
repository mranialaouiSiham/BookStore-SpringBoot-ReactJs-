package com.bookstore.main.payload.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoginRequestTest {
    @Test
    void testConstructor() {
        LoginRequest actualLoginRequest = new LoginRequest();
        actualLoginRequest.setEmail("jane.doe@example.org");
        actualLoginRequest.setPassword("iloveyou");
        assertEquals("jane.doe@example.org", actualLoginRequest.getEmail());
        assertEquals("iloveyou", actualLoginRequest.getPassword());
    }
}

