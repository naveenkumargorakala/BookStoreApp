package com.example.bookstoreproject.controller;

import com.example.bookstoreproject.dto.OrderDto;
import com.example.bookstoreproject.model.OrderModel;
import com.example.bookstoreproject.response.Response;
import com.example.bookstoreproject.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    IOrderService service;

    @Autowired
    Response response;

    //Place Order
    @PostMapping("/placeorder")
    public ResponseEntity<Response> insert(@RequestBody OrderDto orderDto){
        OrderModel order = service.insertOrder(orderDto);
        response.setMessage("Order Placed");
        response.setObject(order);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    //get all orders
    @GetMapping("/getallorders")
    public ResponseEntity<Response> getAllOrders(){
        List<OrderModel> orderList = service.orderList();
        response.setObject(orderList);
        response.setMessage("get all orders");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //get order data by id
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Response> getOrder(@PathVariable int id){
        OrderModel order = service.getOrder(id);
        response.setMessage("Order Details");
        response.setObject(order);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Update Order
    @PutMapping("/updateorder/{id}")
    public ResponseEntity<Response> updateOrder(@RequestBody OrderDto orderDto,@PathVariable int id){
        OrderModel orderModel = service.updateOrder(orderDto,id);
        response.setObject(orderModel);
        response.setMessage("Update Details Of Order");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    //update cancel
    @PutMapping("/updatecancel/{id}")
    public ResponseEntity<Response> updateCancel(@PathVariable int id){
        String order = service.updateCancel(id);
        response.setMessage(order);
        response.setObject("Cancel Order");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //delete Order
    @DeleteMapping("/deleteorder/{id}")
    public ResponseEntity<Response> deleteOrder(@PathVariable int id){
        String order = service.deleteOrder(id);
        response.setObject(order);
        response.setMessage("Delete Order");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
