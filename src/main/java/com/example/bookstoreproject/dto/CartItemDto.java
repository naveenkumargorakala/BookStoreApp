package com.example.bookstoreproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {
    private long bookId;
    private int quantity;
}
