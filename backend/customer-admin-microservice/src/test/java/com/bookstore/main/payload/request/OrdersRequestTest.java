package com.bookstore.main.payload.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class OrdersRequestTest {
    @Test
    void testConstructor() {
        OrdersRequest actualOrdersRequest = new OrdersRequest();
        actualOrdersRequest.setAddress("42 Main St");
        ArrayList<CartItem> cartItemList = new ArrayList<>();
        actualOrdersRequest.setCartItemList(cartItemList);
        assertEquals("42 Main St", actualOrdersRequest.getAddress());
        assertSame(cartItemList, actualOrdersRequest.getCartItemList());
    }
}

