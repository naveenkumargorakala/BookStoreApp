package com.example.bookstoreproject.repository;

import com.example.bookstoreproject.model.CartItem;
import com.example.bookstoreproject.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCart(CartModel cart);
}
