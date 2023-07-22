package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.CartDto;
import com.example.bookstoreproject.exception.ExceptionClass;
import com.example.bookstoreproject.model.BookModel;
import com.example.bookstoreproject.model.CartModel;
import com.example.bookstoreproject.model.UserModel;
import com.example.bookstoreproject.repository.BookRepository;
import com.example.bookstoreproject.repository.CartRepository;
import com.example.bookstoreproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService implements ICartService{

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    //inserting Cart data
    public CartModel insertData(CartDto cartDto){
        UserModel user = userRepository.findById(cartDto.getUserId()).get();
        BookModel book = bookRepository.findById(cartDto.getBookId()).get();
        CartModel cart = new CartModel(user,book,cartDto.getQuantity());
        cartRepository.save(cart);
        return cart;
    }

    //All carts Data
    public ArrayList<CartModel> getAllData(){
        ArrayList<CartModel> cartList = (ArrayList<CartModel>) cartRepository.findAll();
        return cartList;
    }

    //get cart data by id
    public CartModel getById(int id){
        CartModel cart = cartRepository.findById(id).get();
        return cart;
    }

    //delete id
    public String deleteById(int id){
        CartModel cart = cartRepository.findById(id).get();
        if(cart!=null){
            cartRepository.deleteById(id);
            return "delete the cart"+id;
        }
        throw new ExceptionClass(id+" id is Invalid to Delete");
    }

    //update cart
    public CartModel updateById(CartDto cartDto, int id){
        CartModel cart = cartRepository.findById(id).get();
        if(cart!=null){
            cart.setUser(userRepository.findById(cartDto.getUserId()).get());
            cart.setBook(bookRepository.findById(cartDto.getBookId()).get());
            cart.setQuantity(cartDto.getQuantity());
            cartRepository.save(cart);
            return cart;
        }
        else
            throw new ExceptionClass(id+" id is Invalid to update");
    }

    //update quantity
    public CartModel updateQuantity(int quantity,int id){
        CartModel cart = cartRepository.findById(id).get();
        if(cart!=null){
            cart.setQuantity(quantity);
            cartRepository.save(cart);
            return cart;
        }
        throw new ExceptionClass(id+" is not available to update Quantity");
    }
}
