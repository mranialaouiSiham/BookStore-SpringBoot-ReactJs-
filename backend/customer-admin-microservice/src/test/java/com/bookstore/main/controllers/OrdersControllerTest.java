package com.bookstore.main.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.bookstore.main.models.EOrderStatus;
import com.bookstore.main.models.Orders;
import com.bookstore.main.models.User;
import com.bookstore.main.repository.UserRepository;
import com.bookstore.main.services.OrdersService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrdersController.class})
@ExtendWith(SpringExtension.class)
class OrdersControllerTest {
    @Autowired
    private OrdersController ordersController;

    @MockBean
    private OrdersService ordersService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetAllOrders() throws Exception {
        when(this.ordersService.getAllOrders()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/orders");
        MockMvcBuilders.standaloneSetup(this.ordersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetOrder() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());

        Orders orders = new Orders();
        orders.setOrderStatus(EOrderStatus.PROCESSING);
        orders.setOrderItems(new ArrayList<>());
        orders.setId(123L);
        orders.setAddress("42 Main St");
        orders.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        orders.setOrderDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        orders.setUser(user);
        when(this.ordersService.getOrder((Long) any())).thenReturn(orders);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/orders/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.ordersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"orderDate\":0,\"totalPrice\":10.0,\"address\":\"42 Main St\",\"user\":{\"id\":123,\"email\":\"jane.doe"
                                        + "@example.org\",\"password\":\"iloveyou\",\"roles\":[]},\"orderStatus\":\"PROCESSING\",\"orderItems\":[]}"));
    }

    @Test
    void testGetCustomerOrders() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.ordersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testUpdateOrder() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());

        Orders orders = new Orders();
        orders.setOrderStatus(EOrderStatus.PROCESSING);
        orders.setOrderItems(new ArrayList<>());
        orders.setId(123L);
        orders.setAddress("42 Main St");
        orders.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        orders.setOrderDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        orders.setUser(user);
        when(this.ordersService.updateOrderStatus((Long) any(), (EOrderStatus) any())).thenReturn(orders);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/v1/orders/update/{id}", 123L);
        MockHttpServletRequestBuilder requestBuilder = postResult.param("status", String.valueOf(EOrderStatus.PROCESSING));
        MockMvcBuilders.standaloneSetup(this.ordersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"orderDate\":0,\"totalPrice\":10.0,\"address\":\"42 Main St\",\"user\":{\"id\":123,\"email\":\"jane.doe"
                                        + "@example.org\",\"password\":\"iloveyou\",\"roles\":[]},\"orderStatus\":\"PROCESSING\",\"orderItems\":[]}"));
    }

    @Test
    void testUpdateOrder2() throws Exception {
        when(this.ordersService.updateOrderStatus((Long) any(), (EOrderStatus) any()))
                .thenThrow(new RuntimeException("foo"));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/v1/orders/update/{id}", 123L);
        MockHttpServletRequestBuilder requestBuilder = postResult.param("status", String.valueOf(EOrderStatus.PROCESSING));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.ordersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("foo"));
    }
}

