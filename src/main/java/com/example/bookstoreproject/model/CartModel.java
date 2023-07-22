package com.example.bookstoreproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart_table")
public class CartModel {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private int cartId;
    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "user_id")
    private UserModel user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private BookModel book;
    private int quantity;


    public CartModel(UserModel user, BookModel book, int quantity) {
        this.user = user;
        this.book=book;
        this.quantity =quantity;
    }
}
