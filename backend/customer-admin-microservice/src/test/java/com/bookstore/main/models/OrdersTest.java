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

class OrdersTest {
    @Test
    void testConstructor() {
        Orders actualOrders = new Orders();
        actualOrders.setAddress("42 Main St");
        actualOrders.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualOrders.setOrderDate(fromResult);
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        actualOrders.setOrderItems(orderItemList);
        actualOrders.setOrderStatus(EOrderStatus.PROCESSING);
        actualOrders.setTotalPrice(10.0);
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());
        actualOrders.setUser(user);
        assertEquals("42 Main St", actualOrders.getAddress());
        assertEquals(123L, actualOrders.getId().longValue());
        assertSame(fromResult, actualOrders.getOrderDate());
        assertSame(orderItemList, actualOrders.getOrderItems());
        assertEquals(EOrderStatus.PROCESSING, actualOrders.getOrderStatus());
        assertEquals(10.0, actualOrders.getTotalPrice().doubleValue());
        assertSame(user, actualOrders.getUser());
    }
}

