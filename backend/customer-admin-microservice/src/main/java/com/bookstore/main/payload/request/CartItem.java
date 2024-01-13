package com.bookstore.main.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private int productQuantity;

}
