package com.bookstore.main.security;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.cors.CorsConfigurationSource;

class WebSecurityConfigTest {
    @Test
    void testPasswordEncoder() {
        assertTrue((new WebSecurityConfig())
                .passwordEncoder() instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testCorsConfigurationSource() {
        CorsConfigurationSource actualCorsConfigurationSourceResult = (new WebSecurityConfig()).corsConfigurationSource();
        actualCorsConfigurationSourceResult.getCorsConfiguration(new MockHttpServletRequest());
        assertTrue(
                actualCorsConfigurationSourceResult instanceof org.springframework.web.cors.UrlBasedCorsConfigurationSource);
    }

    @Test
    void testCorsConfigurationSource2() {
        CorsConfigurationSource actualCorsConfigurationSourceResult = (new WebSecurityConfig()).corsConfigurationSource();
        assertNull(actualCorsConfigurationSourceResult.getCorsConfiguration(
                new MockHttpServletRequest("https://example.org/example", "https://example.org/example")));
    }
}

