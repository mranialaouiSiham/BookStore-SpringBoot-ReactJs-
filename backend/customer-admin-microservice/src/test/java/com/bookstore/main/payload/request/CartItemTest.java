package com.bookstore.main.payload.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CartItemTest {
    @Test
    void testConstructor() {
        CartItem actualCartItem = new CartItem(123L, 1);
        actualCartItem.setProductId(123L);
        actualCartItem.setProductQuantity(1);
        assertEquals(123L, actualCartItem.getProductId().longValue());
        assertEquals(1, actualCartItem.getProductQuantity());
        assertEquals("CartItem(productId=123, productQuantity=1)", actualCartItem.toString());
    }
}

