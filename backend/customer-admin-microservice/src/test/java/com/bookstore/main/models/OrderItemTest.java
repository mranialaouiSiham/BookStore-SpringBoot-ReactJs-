package com.bookstore.main.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class OrderItemTest {
    @Test
    void testConstructor() {
        OrderItem actualOrderItem = new OrderItem();
        actualOrderItem.setCategory("Category");
        actualOrderItem.setId(123L);
        actualOrderItem.setImageUrl("https://example.org/example");
        actualOrderItem.setLanguage("en");
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
        actualOrderItem.setOrders(orders);
        actualOrderItem.setPurchasePrice(10.0);
        actualOrderItem.setQuantity(1);
        actualOrderItem.setTitle("Dr");
        assertEquals("Category", actualOrderItem.getCategory());
        assertEquals(123L, actualOrderItem.getId().longValue());
        assertEquals("https://example.org/example", actualOrderItem.getImageUrl());
        assertEquals("en", actualOrderItem.getLanguage());
        assertSame(orders, actualOrderItem.getOrders());
        assertEquals(10.0, actualOrderItem.getPurchasePrice());
        assertEquals(1, actualOrderItem.getQuantity());
        assertEquals("Dr", actualOrderItem.getTitle());
    }
}

