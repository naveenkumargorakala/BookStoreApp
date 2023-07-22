package com.example.bookstoreproject.controller;

import com.example.bookstoreproject.dto.CartDto;
import com.example.bookstoreproject.model.CartModel;
import com.example.bookstoreproject.response.Response;
import com.example.bookstoreproject.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService service;

    //insert Cart Data
    @PostMapping("/insertcart")
    public ResponseEntity<Response> insert(@RequestBody CartDto cartDto){
        CartModel cart = service.insertData(cartDto);
        Response cartResponse = new Response(cart,"InsertCart Details");
        return new ResponseEntity<>(cartResponse, HttpStatus.CREATED);
    }

    //Get all cart data
    @GetMapping("/getallcartdata")
    public ResponseEntity<List<Response>> getCartData(){
        ArrayList<CartModel> cartModel = (ArrayList<CartModel>) service.getAllData();
        Response response = new Response("Cart",cartModel,"Insert Cart details");
        return new ResponseEntity<>(response.getList(),HttpStatus.OK);
    }

    //get cart details by id
    @GetMapping("/getcartbyid/{id}")
    public ResponseEntity<Response> getById(@PathVariable int id){
        CartModel cart = service.getById(id);
        Response response = new Response(cart,id+"id cart details");
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    //delete cart
    @DeleteMapping("/deletecart/{id}  ")
    public ResponseEntity<Response> deleteById(@PathVariable int id){
        String message = service.deleteById(id);
        Response response = new Response(message,"Delete cart");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //update cart details
    @PutMapping("/updatecart/{id}")
    public ResponseEntity<Response> updateById(@RequestBody CartDto cartDto,@PathVariable int id){
        CartModel cartModel=service.updateById(cartDto,id);
        Response response = new Response(cartModel,"Update Cart");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    //update quantity
    @PutMapping("/updatequantity/{id}")
    public ResponseEntity<Response> updateQuantity(@RequestParam int quantity,@PathVariable int id){
        CartModel cart = service.updateQuantity(quantity,id);
        Response response = new Response(cart,"update Quantity");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
