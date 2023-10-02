package com.example.bookstoreproject.repository;

import com.example.bookstoreproject.model.CartModel;
import com.example.bookstoreproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartModel,Long> {


    Optional<CartModel> findByUser(UserModel user);
}
