package com.bookstore.main.exceptions;

import java.io.IOException;
import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RestAuthenticationEntryPoint.class})
@ExtendWith(SpringExtension.class)
class RestAuthenticationEntryPointTest {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Test
    void testCommence() throws IOException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AccessDeniedException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        this.restAuthenticationEntryPoint.commence(request, response, new AccessDeniedException("Msg"));
    }

    @Test
    void testCommence2() throws IOException, ServletException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AuthenticationException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        this.restAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));
    }

    @Test
    void testCommence3() throws IOException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AccessDeniedException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        this.restAuthenticationEntryPoint.commence(request, response, new AccessDeniedException("Msg"));
    }

    @Test
    void testCommence4() throws IOException, ServletException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AuthenticationException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        this.restAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));
    }

    @Test
    void testCommence5() throws IOException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AccessDeniedException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        this.restAuthenticationEntryPoint.commence(request, response, new AccessDeniedException("Msg"));
    }

    @Test
    void testCommence6() throws IOException, ServletException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AuthenticationException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        this.restAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));
    }
}

