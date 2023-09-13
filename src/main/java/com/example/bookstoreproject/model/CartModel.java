package com.example.bookstoreproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<BookModel> book;
    private int quantity;


    public CartModel(UserModel user,List<BookModel> book, int quantity) {
        this.user = user;
        this.book=book;
        this.quantity =quantity;
    }
}
