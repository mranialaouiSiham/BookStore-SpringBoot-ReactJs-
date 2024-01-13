package com.bookstore.main.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TestController.class})
@ExtendWith(SpringExtension.class)
class TestControllerTest {
    @Autowired
    private TestController testController;

    @Test
    void testAdminAccess() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/test/admin");
        MockMvcBuilders.standaloneSetup(this.testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Admin Board."));
    }

    @Test
    void testAdminAccess2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/test/admin", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Admin Board."));
    }

    @Test
    void testAllAccess() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/test/all");
        MockMvcBuilders.standaloneSetup(this.testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Public Content."));
    }

    @Test
    void testAllAccess2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/test/all");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.testController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Public Content."));
    }

    @Test
    void testModeratorAccess() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/test/mod");
        MockMvcBuilders.standaloneSetup(this.testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Moderator Board."));
    }

    @Test
    void testModeratorAccess2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/test/mod");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.testController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Moderator Board."));
    }

    @Test
    void testUserAccess() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/test/user");
        MockMvcBuilders.standaloneSetup(this.testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User Content."));
    }

    @Test
    void testUserAccess2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/test/user");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.testController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User Content."));
    }
}

