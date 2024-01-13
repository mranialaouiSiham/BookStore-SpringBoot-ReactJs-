package com.bookstore.main.services;

import com.bookstore.main.models.*;
import com.bookstore.main.payload.request.CartItem;
import com.bookstore.main.payload.request.OrdersRequest;
import com.bookstore.main.repository.BookRepository;
import com.bookstore.main.repository.OrderItemRepository;
import com.bookstore.main.repository.OrdersRepository;
import com.bookstore.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    BookRepository bookRepository;

    public Orders checkout(User user, OrdersRequest ordersRequest){
        Orders orders = new Orders();
        orders.setUser(user);
        double totalPrice = 0;
        Set<OrderItem> orderItems = new HashSet<>();
        for(CartItem cartItem:ordersRequest.getCartItemList()){
            totalPrice +=bookRepository.findById(cartItem.getProductId()).get().getPrice()
            *cartItem.getProductQuantity();
            OrderItem orderItem =createOrderItem(orders,cartItem.getProductId(),cartItem.getProductQuantity());
            orderItem.setOrders(orders);
            orderItems.add(orderItem);
        }

        orders.setTotalPrice(totalPrice);
        orders.setOrderStatus(EOrderStatus.PROCESSING);
        orders.setAddress(ordersRequest.getAddress());
        Orders responseOrder=ordersRepository.save(orders);
        orderItemRepository.saveAll(orderItems);
        return responseOrder;
    }

    public OrderItem createOrderItem(Orders orders, Long bookId,int quantity){
        OrderItem orderItem = new OrderItem();
        Book book = bookRepository.findById(bookId).get();
        orderItem.setTitle(book.getTitle());
        orderItem.setPurchasePrice(book.getPrice());
        orderItem.setQuantity(quantity);
        orderItem.setImageUrl(book.getImageUrl());
        orderItem.setLanguage(book.getLanguage().toString());
        orderItem.setCategory(book.getCategory().toString());
        orderItem.setOrders(orders);
        return orderItem;
    }

    public List<Orders> getOrdersByUser(User user){
        return ordersRepository.findByUser(user);
    }

    public List<Orders> getAllOrders(){
        return ordersRepository.findAll();
    }

    public Orders updateOrderStatus(Long orderId,EOrderStatus orderStatus){
        Orders orders = ordersRepository.findById(orderId).orElseThrow(
                ()->new RuntimeException("Order not found")
        );
        orders.setOrderStatus(orderStatus);
        return ordersRepository.save(orders);
    }

    public Orders getOrder(Long orderId){
        return ordersRepository.findById(orderId).orElseThrow(
                ()->new RuntimeException("Order not found")
        );
    }
}
