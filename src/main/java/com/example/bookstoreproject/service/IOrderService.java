package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.OrderDto;
import com.example.bookstoreproject.model.CartItem;
import com.example.bookstoreproject.model.OrderModel;

import java.util.List;

public interface IOrderService {
//    OrderModel insertOrder(OrderDto orderDto);
//
//    List<OrderModel> orderList();
//
//    OrderModel getOrder(String token);
//
//    OrderModel updateOrder(OrderDto orderDto,String token);
//
//    String updateCancel(String token);
//
//    String deleteOrder(String token);

    OrderModel placeOrder(String token, List<CartItem> cartItems, List<String> address);
    OrderModel getOrder(String token);
}
