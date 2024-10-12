package com.example.bookstoreproject.controller;

import com.example.bookstoreproject.dto.OrderDto;
import com.example.bookstoreproject.model.CartItem;
import com.example.bookstoreproject.model.OrderModel;
import com.example.bookstoreproject.response.Response;
import com.example.bookstoreproject.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
@RequestMapping("/order")
public class OrderController {


    @Autowired
    IOrderService service;

    @Autowired
    Response response;
//
//    //Place Order
//    @PostMapping("/placeorder")
//    public ResponseEntity<Response> insert(@RequestBody OrderDto orderDto){
//        OrderModel order = service.insertOrder(orderDto);
//        response.setMessage("Order Placed");
//        response.setObject(order);
//        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//    }
//
//    //get all orders
//    @GetMapping("/getallorders")
//    public ResponseEntity<Response> getAllOrders(){
//        List<OrderModel> orderList = service.orderList();
//        response.setObject(orderList);
//        response.setMessage("get all orders");
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//    //get order data by id
//    @GetMapping("/getbyid/{id}")
//    public ResponseEntity<Response> getOrder(@RequestHeader String token){
//        OrderModel order = service.getOrder(token);
//        response.setMessage("Order Details");
//        response.setObject(order);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//    //Update Order
//    @PutMapping("/updateorder/")
//    public ResponseEntity<Response> updateOrder(@RequestBody OrderDto orderDto,@RequestHeader String token){
//        OrderModel orderModel = service.updateOrder(orderDto,token);
//        response.setObject(orderModel);
//        response.setMessage("Update Details Of Order");
//        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
//    }
//
//    //update cancel
//    @PutMapping("/updatecancel/{id}")
//    public ResponseEntity<Response> updateCancel(@RequestHeader String token){
//        String order = service.updateCancel(token);
//        response.setMessage(order);
//        response.setObject("Cancel Order");
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//    //delete Order
//    @DeleteMapping("/deleteorder/{id}")
//    public ResponseEntity<Response> deleteOrder(@RequestHeader String token){
//        String order = service.deleteOrder(token);
//        response.setObject(order);
//        response.setMessage("Delete Order");
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }

    @PostMapping("/placeorder/{token}")
<<<<<<< Updated upstream
    public ResponseEntity<Response> placeOrder(@PathVariable String token, @RequestBody List<CartItem> cartItems, @RequestBody List<String> address){
        OrderModel order = service.placeOrder(token,cartItems,address);
        response.setObject(order);
        response.setMessage("Order Placed Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/getorder/{token}")
    public ResponseEntity<Response> getOrder(@PathVariable String token){
        OrderModel order = service.getOrder(token);
        response.setObject(order);
        response.setMessage("Your Order is"+order.getId());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

=======
    public ResponseEntity<Response> placeOrder(@PathVariable String token, @RequestBody List<CartItem> cartItems){
        OrderModel order = service.placeOrder(token,cartItems);
        response.setObject(order);
        response.setMessage("Order Placed Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/getorder/{token}")
    public ResponseEntity<Response> getOrder(@PathVariable String token){
        OrderModel order = service.getOrder(token);
        response.setObject(order);
        response.setMessage("Your Order is"+order.getId());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/updateaddress/{token}")
    public ResponseEntity<Response> updateAddress(@PathVariable String token,@RequestBody List<String> address){
        OrderModel order = service.updateAddress(token,address);
        response.setObject(order);
        response.setMessage("address updated Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
>>>>>>> Stashed changes
}
