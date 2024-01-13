package com.bookstore.main.controllers;

import com.bookstore.main.models.EOrderStatus;
import com.bookstore.main.models.User;
import com.bookstore.main.payload.request.OrdersRequest;
import com.bookstore.main.repository.UserRepository;
import com.bookstore.main.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(ordersService.getAllOrders());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOrder(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(ordersService.getOrder(id));
    }


    @GetMapping("/customer")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCustomerOrders(
            Authentication authentication
    ) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new RuntimeException("email not found")
        );
        return ResponseEntity.ok(ordersService.getOrdersByUser(user));
    }

    @PostMapping("/checkout")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> checkout(
            @Valid @RequestBody OrdersRequest ordersRequest,
            Authentication authentication
    ) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new RuntimeException("email not found")
        );
        return ResponseEntity.ok(ordersService.checkout(user, ordersRequest));

    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateOrder(
            @PathVariable Long id,
            @RequestParam(value = "status") EOrderStatus status
    ) {

        try {
            return ResponseEntity.ok(ordersService.updateOrderStatus(id, status));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
