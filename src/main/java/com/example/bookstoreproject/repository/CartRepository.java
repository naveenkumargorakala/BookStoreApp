package com.example.bookstoreproject.repository;

import com.example.bookstoreproject.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartModel,Integer> {

}
