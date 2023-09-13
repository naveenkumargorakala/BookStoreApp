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

    @Autowired
    Response response;

    //insert Cart Data
    @PostMapping("/insertcart")
    public ResponseEntity<Response> insert(@RequestBody CartDto cartDto){
        CartModel cart = service.insertData(cartDto);
        response.setObject(cart);
        response.setMessage("Inserted Cart Details");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Get all cart data
    @GetMapping("/getallcartdata")
    public ResponseEntity<Response> getCartData(){
        ArrayList<CartModel> cartModel = (ArrayList<CartModel>) service.getAllData();
        response.setMessage("All Carts Data");
        response.setObject(cartModel);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //get cart details by id
    @GetMapping("/getcartbyid/{id}")
    public ResponseEntity<Response> getById(@PathVariable int id){
        CartModel cart = service.getById(id);
        response.setObject(cart);
        response.setMessage(id+" cart details");
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    //delete cart
    @DeleteMapping("/deletecart/{id}  ")
    public ResponseEntity<Response> deleteById(@PathVariable int id){
        String message = service.deleteById(id);
        response.setMessage("Delete Cart Data");
        response.setObject(message);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //update cart details
    @PutMapping("/updatecart/{id}")
    public ResponseEntity<Response> updateById(@RequestBody CartDto cartDto,@PathVariable int id){
        CartModel cartModel=service.updateById(cartDto,id);
        response.setMessage("Update Cart details");
        response.setObject(cartModel);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    //update quantity
    @PutMapping("/updatequantity/{id}")
    public ResponseEntity<Response> updateQuantity(@RequestParam int quantity,@PathVariable int id){
        CartModel cart = service.updateQuantity(quantity,id);
        response.setObject(cart);
        response.setMessage("Update Quantity");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
