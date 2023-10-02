package com.example.bookstoreproject.controller;

import com.example.bookstoreproject.dto.CartDto;
import com.example.bookstoreproject.dto.CartItemDto;
import com.example.bookstoreproject.model.CartItem;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService service;

    @Autowired
    Response response;

    //insert Cart Data
//    @PostMapping("/addtocart")
//    public ResponseEntity<Response> insert(@RequestBody CartDto cartDto){
//
//        CartModel cart = service.insertData(cartDto);
//        response.setObject(cart);
//        response.setMessage("Inserted Cart Details");
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    //Get all cart data
//    @GetMapping("/getallcartdata")
//    public ResponseEntity<Response> getCartData(){
//        ArrayList<CartModel> cartModel = (ArrayList<CartModel>) service.getAllData();
//        response.setMessage("All Carts Data");
//        response.setObject(cartModel);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//    //get cart details by id
//    @GetMapping("/getcartbyusertoken/{token}")
//    public ResponseEntity<Response> getById(@PathVariable String token){
//        CartModel cart = service.getByToken(token);
//        response.setObject(cart);
//        response.setMessage(" cart details");
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//    //delete cart
//    //http://localhost:8080/cart/deletebycartid
//    @DeleteMapping("/deletecart")
//    public ResponseEntity<Response> deleteById(@RequestHeader("Authorization") String token){
//        String message = service.deleteByToken(token);
//        response.setMessage("Delete Cart Data");
//        response.setObject(message);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//    //update cart details
//    @PutMapping("/updatecart")
//    public ResponseEntity<Response> updateById(@RequestBody CartDto cartDto,@RequestHeader("Authorization") String token){
//        CartModel cartModel=service.updateByToken(cartDto,token);
//        response.setMessage("Update Cart details");
//        response.setObject(cartModel);
//        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
//    }
//
//    //update quantity
//    @PutMapping("/updatequantity")
//    public ResponseEntity<Response> updateQuantity(@RequestParam int quantity,@RequestHeader("Authorization") String token){
//        CartModel cart = service.updateQuantity(quantity,token);
//        response.setObject(cart);
//        response.setMessage("Update Quantity");
//        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
//    }
//
//    @GetMapping("/cartId/{userId}")
//    public int cartId(@PathVariable int userId){
//        int cartId;
//        return cartId=service.cartId(userId);
//    }

//    http://localhost:8080/cart/addtocart/
    @PostMapping("/addtocart/{token}")
    public ResponseEntity<Response> addCart( @PathVariable String token,@RequestBody CartItemDto cartItemDto){
        CartModel cartModel = service.addCart(token,cartItemDto);
        response.setObject(cartModel);
        response.setMessage("Item Added SuccessFully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getcartbyusertoken/{token}")
    public ResponseEntity<Response> getCart(@PathVariable String token){
        CartModel cart = service.getCart(token);
        response.setMessage("Get Cart Details");
        response.setObject(cart);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

        @PutMapping("/updatecartquantity/{quantity}/{token}")
    public ResponseEntity<Response> updateQuantity(@PathVariable int quantity, @PathVariable String token){
        CartModel cart = service.updateQuantity(quantity,token);
        response.setMessage("Update Cart Details");
        response.setObject(cart);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/deletecart/{token}")
    public ResponseEntity<Response> deleteCart(@PathVariable String token){
        String deletedMessage = service.deleteCart(token);
        response.setObject(deletedMessage);
        response.setMessage("Deleted Succssfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/removecartItem/{token}")
    public ResponseEntity<Response> removeFromCart(@RequestBody CartItemDto cartItemDto,@PathVariable String token){
        CartModel cart= service.removeFromCart(cartItemDto,token);
        response.setMessage("remove cartItem from Cart Details");
        response.setObject(cart);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
