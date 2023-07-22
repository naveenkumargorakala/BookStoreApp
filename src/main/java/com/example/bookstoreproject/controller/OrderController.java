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

    //Place Order
    @PostMapping("/placeorder")
    public ResponseEntity<Response> insert(@RequestBody OrderDto orderDto){
        OrderModel order = service.insertOrder(orderDto);
        Response response = new Response(order,"Order Placed");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    //get all orders
    @GetMapping("/getallorders")
    public ResponseEntity<List<Response>> getAllOrders(){
        List<OrderModel> orderList = service.orderList();
        Response response = new Response(orderList);
        return new ResponseEntity<>(response.getList(),HttpStatus.OK);
    }

    //get order data by id
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Response> getOrder(@PathVariable int id){
        OrderModel order = service.getOrder(id);
        Response response = new Response(order,id+" oreder details");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Update Order
    @PutMapping("/updateorder/{id}")
    public ResponseEntity<Response> updateOrder(@RequestBody OrderDto orderDto,@PathVariable int id){
        OrderModel orderModel = service.updateOrder(orderDto,id);
        Response response = new Response(orderModel,"Update Order");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    //update cancel
    @PutMapping("/updatecancel/{id}")
    public ResponseEntity<Response> updateCancel(@PathVariable int id){
        String order = service.updateCancel(id);
        Response response = new Response(order,"Cancel Order");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //delete Order
    @DeleteMapping("/deleteorder/{id}")
    public ResponseEntity<Response> deleteOrder(@PathVariable int id){
        String order = service.deleteOrder(id);
        Response response = new Response(order,"Delete Order");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
