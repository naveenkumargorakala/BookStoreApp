package com.example.bookstoreproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;
}
