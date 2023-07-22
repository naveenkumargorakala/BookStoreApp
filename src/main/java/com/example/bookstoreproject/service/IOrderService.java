package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.OrderDto;
import com.example.bookstoreproject.model.OrderModel;

import java.util.List;

public interface IOrderService {
    OrderModel insertOrder(OrderDto orderDto);

    List<OrderModel> orderList();

    OrderModel getOrder(int id);

    OrderModel updateOrder(OrderDto orderDto, int id);

    String updateCancel(int id);

    String deleteOrder(int id);
}
