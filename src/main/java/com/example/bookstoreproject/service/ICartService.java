package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.CartDto;
import com.example.bookstoreproject.model.CartModel;

import java.util.ArrayList;
import java.util.List;

public interface ICartService {

    CartModel insertData(CartDto cartDto);

    ArrayList<CartModel> getAllData();

    CartModel getById(int id);

    CartModel updateById(CartDto cartDto, int id);

    CartModel updateQuantity(int quantity,int id);

    String deleteById(int id);
}
