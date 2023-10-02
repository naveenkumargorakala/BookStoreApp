package com.example.bookstoreproject.model;

import com.example.bookstoreproject.dto.BookDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book_table")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private int price;
    private int quantity;


    public BookModel(BookDto bookDto) {
        this.bookName = bookDto.getBookName();
        this.authorName = bookDto.getAuthorName();
        this.bookDescription = bookDto.getBookDescription();
        this.bookImg =bookDto.getBookImg();
        this.price = bookDto.getPrice();
        this.quantity = bookDto.getQuantity();
    }

}
