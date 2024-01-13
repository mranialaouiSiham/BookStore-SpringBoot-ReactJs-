package com.bookstore.main.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private double purchasePrice;
    private int quantity;

    private String imageUrl;

    private String language;
    private String category;

    @JsonBackReference
    @OneToOne
    private Orders orders;
}
