package com.example.bookstoreproject.repository;

import com.example.bookstoreproject.model.OrderModel;
import com.example.bookstoreproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel,Long> {

    OrderModel findByuser(UserModel user);
}
