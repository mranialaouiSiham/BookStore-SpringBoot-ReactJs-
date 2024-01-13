package com.bookstore.main.payload.request;

import com.bookstore.main.models.Author;
import com.bookstore.main.models.Category;
import com.bookstore.main.models.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookRequest {

    @Size(max = 100, min = 1)
    private String title;

    private String description;

    private String imageUrl;

    private Long author;

    private Long language;

    private Long category;

    private double price;

}
