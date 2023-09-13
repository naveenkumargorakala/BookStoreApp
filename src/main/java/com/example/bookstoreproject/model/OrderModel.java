package com.example.bookstoreproject.model;

import com.example.bookstoreproject.dto.OrderDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class OrderModel {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private int orderId;
    private int totalPrice;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserModel user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="book_id")
    private BookModel book;
    private int quantity;
    private LocalDate date =LocalDate.now();   
    private boolean cancel;

    public OrderModel(UserModel user, BookModel book, OrderDto orderDto) {
        this.user=user;
        this.book=book;
        this.address = orderDto.getAddress();
        this.quantity=orderDto.getQuantity();
    }
}
