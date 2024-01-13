package com.bookstore.main.payload.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SignupRequestTest {
    @Test
    void testConstructor() {
        SignupRequest actualSignupRequest = new SignupRequest();
        actualSignupRequest.setEmail("jane.doe@example.org");
        actualSignupRequest.setPassword("iloveyou");
        assertEquals("jane.doe@example.org", actualSignupRequest.getEmail());
        assertEquals("iloveyou", actualSignupRequest.getPassword());
    }
}

