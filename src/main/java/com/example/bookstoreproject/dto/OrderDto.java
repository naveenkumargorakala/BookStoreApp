package com.example.bookstoreproject.dto;



import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDto {

    private String address;
    private int userId;
    private int bookId;
    private int quantity;

}
