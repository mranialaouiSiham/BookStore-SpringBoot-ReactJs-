package com.bookstore.main.security.jwt;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

class AuthTokenFilterTest {
    @Test
    void testDoFilterInternal() throws IOException, ServletException {
        AuthTokenFilter authTokenFilter = new AuthTokenFilter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
        authTokenFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
    }

    @Test
    void testDoFilterInternal2() throws IOException, ServletException {
        AuthTokenFilter authTokenFilter = new AuthTokenFilter();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
        authTokenFilter.doFilterInternal(null, response, filterChain);
        verify(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
    }
}

