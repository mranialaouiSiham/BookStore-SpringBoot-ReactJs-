package com.bookstore.main.repository;

import com.bookstore.main.models.Author;
import com.bookstore.main.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
