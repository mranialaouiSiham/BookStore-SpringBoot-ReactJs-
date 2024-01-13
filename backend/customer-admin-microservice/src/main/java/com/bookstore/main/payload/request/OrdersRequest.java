package com.bookstore.main.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class OrdersRequest {

    @Size(max = 100, min = 1)
    private String address;
    private List<CartItem> cartItemList;

}

