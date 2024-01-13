package com.bookstore.main.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100, min = 1)
    private String title;

    private String description;

    private String imageUrl;

    @OneToOne
    private Author author;

    @OneToOne
    private Language language;

    @OneToOne
    private Category category;

    private double price;



}
