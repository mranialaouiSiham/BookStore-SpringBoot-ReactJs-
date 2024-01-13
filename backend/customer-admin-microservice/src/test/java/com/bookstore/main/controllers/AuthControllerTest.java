package com.bookstore.main.controllers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.bookstore.main.models.ERole;
import com.bookstore.main.models.Role;
import com.bookstore.main.models.User;
import com.bookstore.main.payload.request.LoginRequest;
import com.bookstore.main.payload.request.SignupRequest;
import com.bookstore.main.repository.RoleRepository;
import com.bookstore.main.repository.UserRepository;
import com.bookstore.main.security.jwt.JwtUtils;
import com.bookstore.main.security.services.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testAuthenticateUser() throws Exception {
        when(this.jwtUtils.generateJwtToken((org.springframework.security.core.Authentication) any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any())).thenReturn(
                new TestingAuthenticationToken(new UserDetailsImpl(123L, "jane.doe@example.org", "iloveyou", new ArrayList<>()),
                        "Credentials"));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"username\":\"jane.doe@example.org\",\"email\":\"jane.doe@example.org\",\"roles\":[],\"accessToken\":"
                                        + "\"ABC123\",\"tokenType\":\"Bearer\"}"));
    }

    @Test
    void testConstructor() {
        AuthController actualAuthController = new AuthController();
        actualAuthController.initRoleAndUser();
        assertNull(actualAuthController.authenticationManager);
        assertNull(actualAuthController.userRepository);
        assertNull(actualAuthController.roleRepository);
        assertNull(actualAuthController.jwtUtils);
        assertNull(actualAuthController.encoder);
    }

    @Test
    void testRegisterUser() throws Exception {
        when(this.userRepository.existsByEmail((String) any())).thenReturn(true);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("jane.doe@example.org");
        signupRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(signupRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.authController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Error: Username is already taken!\"}"));
    }

    @Test
    void testRegisterUser2() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.existsByEmail((String) any())).thenReturn(false);

        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_USER);
        Optional<Role> ofResult = Optional.of(role);
        when(this.roleRepository.findByName((ERole) any())).thenReturn(ofResult);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("jane.doe@example.org");
        signupRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(signupRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"User registered successfully!\"}"));
    }
}

