package com.example.bookstoreproject.repository;

import com.example.bookstoreproject.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel,Integer> {

}
