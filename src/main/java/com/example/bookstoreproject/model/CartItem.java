package com.example.bookstoreproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties("cart_table")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartItemId;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private CartModel cart;

    public CartItem(BookModel book, int quantity) {
        this.book=book;
        this.quantity=quantity;

    }


}
