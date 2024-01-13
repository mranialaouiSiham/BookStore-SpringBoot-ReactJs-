package com.bookstore.main.security.jwt;

import java.io.IOException;
import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthEntryPointJwt.class})
@ExtendWith(SpringExtension.class)
class AuthEntryPointJwtTest {
    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

    @Test
    void testCommence() throws IOException, ServletException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AuthenticationException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        this.authEntryPointJwt.commence(request, response, new AccountExpiredException("Msg"));
    }

    @Test
    void testCommence2() throws IOException, ServletException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AuthenticationException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest("https://example.org/example",
                "https://example.org/example");

        MockHttpServletResponse response = new MockHttpServletResponse();
        this.authEntryPointJwt.commence(request, response, new AccountExpiredException("Msg"));
    }

    @Test
    void testCommence3() throws IOException, ServletException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AuthenticationException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        this.authEntryPointJwt.commence(request, response, new AccountExpiredException("Msg"));
    }

    @Test
    void testCommence4() throws IOException, ServletException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AuthenticationException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MockHttpServletRequest request = new MockHttpServletRequest("https://example.org/example",
                "https://example.org/example");

        MockHttpServletResponse response = new MockHttpServletResponse();
        this.authEntryPointJwt.commence(request, response, new AccountExpiredException("Msg"));
    }
}

