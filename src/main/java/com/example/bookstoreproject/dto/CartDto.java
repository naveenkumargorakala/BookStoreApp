package com.example.bookstoreproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {

    private int userId;
    private List<Integer> bookIds;
    private int quantity;
}
