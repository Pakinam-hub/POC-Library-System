package com.example.project.sa2.springboot.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Book {

    private int id;

    private String title;

    private String author;

    private float price;

    private String category;

    private String operationType;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", operationType='" + operationType + '\'' +
                '}';
    }
}
