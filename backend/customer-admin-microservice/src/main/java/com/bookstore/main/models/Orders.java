package com.bookstore.main.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date orderDate;


    private Double totalPrice;

    @Size(max = 100, min = 1)
    private String address;

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private EOrderStatus orderStatus;

    @JsonManagedReference
    @OneToMany(mappedBy = "orders")
    private java.util.List<OrderItem> orderItems;
}
