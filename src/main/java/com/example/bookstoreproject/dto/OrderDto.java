package com.example.bookstoreproject.dto;



import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private long userId;
    private List<String> address;
    private List<Integer> bookIds;
    private long totalPrice;
    private int quantity;

}
