package com.example.bookstoreproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;

@Data
@NoArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartModel cart;

    public CartItem(BookModel book, int quantity) {
        this.book=book;
        this.quantity=quantity;

    }


}