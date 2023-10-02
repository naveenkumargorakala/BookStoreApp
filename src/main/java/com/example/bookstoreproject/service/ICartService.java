package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.CartDto;
import com.example.bookstoreproject.dto.CartItemDto;
import com.example.bookstoreproject.model.CartItem;
import com.example.bookstoreproject.model.CartModel;

import java.util.ArrayList;
import java.util.List;

public interface ICartService {

//    CartModel insertData(CartDto cartDto);
//
//    ArrayList<CartModel> getAllData();
//
//    CartModel getByToken(String token);
//
//    CartModel updateByToken(CartDto cartDto, String token);
//
//    CartModel updateQuantity(int quantity,String token);
//
//    String deleteByToken(String token);
//    int cartId(int userId);

    CartModel addCart( String token,CartItemDto cartItemDto);
    CartModel getCart(String token);
    CartModel updateQuantity(int quantity, String token);
    String deleteCart(String token);
    CartModel removeFromCart(CartItemDto cartItemDto,String token);
}
