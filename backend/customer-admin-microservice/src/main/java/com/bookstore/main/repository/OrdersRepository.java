package com.bookstore.main.repository;

import com.bookstore.main.models.Author;
import com.bookstore.main.models.Orders;
import com.bookstore.main.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser(User user);

}

