package com.example.bookstoreproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {

    private int userId;
    private int bookId;
    private int quantity;
}
