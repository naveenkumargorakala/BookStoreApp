package com.example.bookstoreproject.repository;

import com.example.bookstoreproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    //find user by email
    UserModel findByEmail(String email);

    //check whether user is present or not by email
    boolean existsByEmail(String email);
}
