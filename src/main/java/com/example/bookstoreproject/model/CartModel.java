package com.example.bookstoreproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.awt.print.Book;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart_table")
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;
    @OneToOne
    private UserModel user;
}

