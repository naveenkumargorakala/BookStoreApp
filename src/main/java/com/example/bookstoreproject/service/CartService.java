package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.CartDto;
import com.example.bookstoreproject.dto.CartItemDto;
import com.example.bookstoreproject.exception.ExceptionClass;
import com.example.bookstoreproject.model.BookModel;
import com.example.bookstoreproject.model.CartItem;
import com.example.bookstoreproject.model.CartModel;
import com.example.bookstoreproject.model.UserModel;
import com.example.bookstoreproject.repository.BookRepository;
import com.example.bookstoreproject.repository.CartItemRepository;
import com.example.bookstoreproject.repository.CartRepository;
import com.example.bookstoreproject.repository.UserRepository;
import com.example.bookstoreproject.util.TokenUtil;
import jakarta.transaction.TransactionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    TokenUtil tokenUtil;

    //inserting Cart data
//    public CartModel insertData(CartDto cartDto,String token){
//
////        UserModel user = userRepository.findById(cartDto.getUserId()).get();
////        List<BookModel> books =new ArrayList<>();
////        for(int i=0;i<cartDto.getBookIds().size();i++) {
////            BookModel book = bookRepository.findById(cartDto.getBookIds().get(i)).get();
////            // Check if there are enough available copies
////            int requestedQuantity = cartDto.getQuantity();
////            if (book.getQuantity() < requestedQuantity) {
////                throw new ExceptionClass("Not enough available copies of the book");
////            }
////            // Reduce the available quantity and save the book
////            book.setQuantity(book.getQuantity() - requestedQuantity);
////            bookRepository.save(book);
////            books.add(book);
////        }
////        CartModel cart = new CartModel(user, books, cartDto.getQuantity());
////        cartRepository.save(cart);
////        return cart;
//    }
//
//    //All carts Data
//    public ArrayList<CartModel> getAllData(){
//        ArrayList<CartModel> cartList = (ArrayList<CartModel>) cartRepository.findAll();
//        return cartList;
//    }
//
//    //get cart data by id
//    public CartModel getByToken(String token){
//        int userId=tokenUtil.decodeToken(token);
//        UserModel user= userRepository.findById(userId).get();
//        CartModel cart = cartRepository.findByUser(user).get();
//        return cart;
//    }
//
//    //delete id
//    public String deleteByToken(String token){
//        int userId= tokenUtil.decodeToken(token);
//        UserModel user=userRepository.findById(userId).get();
//        CartModel cart = cartRepository.findByUser(user).get();
//        if(cart!=null){
////            cartRepository.deleteById(id);
//            cartRepository.deleteById(cart.getCartId());
//            return "delete the cart"+cart.getCartId();
//        }
//        throw new ExceptionClass("Invalid token to Delete Cart");
//    }
//
//    //update cart
//    public CartModel updateByToken(CartDto cartDto,String token){
////        int userId= tokenUtil.decodeToken(token);
////        UserModel user=userRepository.findById(userId).get();
////        CartModel cart = cartRepository.findByUser(user).get();
////        if(cart!=null){
////            cart.setUser(userRepository.findById(cartDto.getUserId()).get());
////            List<BookModel> books = new ArrayList<>();
////            for(int i=0;i<cartDto.getBookIds().size();i++) {
////                books.add(bookRepository.findById(cartDto.getBookIds().get(i)).get());
////            }
////            cart.setBooks(books);
////            cart.setQuantity(cartDto.getQuantity());
////            cartRepository.save(cart);
////            return cart;
////        }
////        else
////            throw new ExceptionClass("Invalid to token");
//    }
//
//    //update quantity
//    public CartModel updateQuantity(int quantity,String token){
//        int userId= tokenUtil.decodeToken(token);
//        UserModel user=userRepository.findById(userId).get();
//        CartModel cart = cartRepository.findByUser(user).get();
//        if(cart!=null){
//            cart.setQuantity(quantity);
//            cartRepository.save(cart);
//            return cart;
//        }
//        throw new ExceptionClass("Invalid token to update Quantity");
//    }
//
//
//    //get cartId
//    public int cartId(int userId){
//        UserModel user = userRepository.findById(userId).get();
//        CartModel cart = cartRepository.findByUser(user).get();
//        return cart.getCartId();
//    }

    //add cartitem to cart
    @Transactional
    public CartModel addCart(String token, CartItemDto cartItemDto) {
        long user_id = tokenUtil.decodeToken(token);
        UserModel user = userRepository.findById(user_id)
                .orElseThrow(() -> new ExceptionClass("User not found with ID: " + user_id));
        BookModel book = bookRepository.findById(cartItemDto.getBookId())
                .orElseThrow(() -> new ExceptionClass("Book not found with ID: " + cartItemDto.getBookId()));
        CartModel cart = cartRepository.findByUser(user).orElse(null);
        if (cart == null) {
            cart = new CartModel();
            cart.setUser(user);
            cart.setCartItems(new ArrayList<>());
        }
        CartItem cartItem = new CartItem(book, cartItemDto.getQuantity());
        for(CartItem cartItem1:cart.getCartItems()){
            if(cartItem1.getBook().equals(cartItem.getBook())){
                cartItem1.setQuantity(cartItem1.getQuantity()+cartItem.getQuantity());
            }
        }
        cartItem.setCart(cart); // Set the CartModel in the CartItem
        // Add the new cart item to the cart
        cart.getCartItems().add(cartItem);
        // Update book quantity
        book.setQuantity(book.getQuantity() - cartItem.getQuantity());
        bookRepository.save(book);
        // Save the CartModel and associated CartItem
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        return cart;
    }




    //remove cartItem from cart
    @Transactional
    public CartModel removeFromCart(CartItemDto cartItemDto, String token) {
        long user_id = tokenUtil.decodeToken(token);
        UserModel user = userRepository.findById(user_id)
                .orElseThrow(() -> new ExceptionClass("User not found with ID: " + user_id));
        BookModel book = bookRepository.findById(cartItemDto.getBookId())
                .orElseThrow(() -> new ExceptionClass("Book not found with ID: " + cartItemDto.getBookId()));
        CartModel cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ExceptionClass("Cart not found for the user: " + user.getUserID()));
        CartItem cartItemToRemove = null;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getBook().getBookId() == cartItemDto.getBookId()) {
                cartItemToRemove = cartItem;
                break;
            }
        }
        if (cartItemToRemove != null) {
            book.setQuantity(book.getQuantity() + cartItemToRemove.getQuantity());
            bookRepository.save(book);
            cart.getCartItems().remove(cartItemToRemove);
            cartRepository.save(cart);
        } else {
            throw new ExceptionClass("CartItem not found in the user's cart.");
        }

        return cart;
    }


    //delete cart
    public String deleteCart(String token){
        long user_id = tokenUtil.decodeToken(token);
        UserModel user = userRepository.findById(user_id).get();
        CartModel cart = null;
        if(cart!=null){
            cartRepository.deleteById(cart.getCartId()
            );
        }
        throw new ExceptionClass("Cart is NOt Available to delete");
    }

    //update quantity
    @Transactional
    public CartModel updateQuantity(int quantity, String token) {
        long user_id = tokenUtil.decodeToken(token);
        UserModel user = userRepository.findById(user_id)
                .orElseThrow(() -> new ExceptionClass("User not found with ID: " + user_id));

        CartModel cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ExceptionClass("Cart not found for the user: " + user_id));

        CartItem cartItem = cartItemRepository.findByCart(cart);
        if (cartItem.getBook().getQuantity() >= quantity) {
            int newQuantity = quantity - cartItem.getQuantity();
            cartItem.setQuantity(quantity);
            BookModel book = cartItem.getBook();
            book.setQuantity(book.getQuantity() - newQuantity);
            bookRepository.save(book);
            cartItem.setBook(book);
            cartItemRepository.save(cartItem);

            return cartRepository.save(cart);
        } else {
            throw new ExceptionClass("Requested quantity is more than the available stock.");
        }
    }


    //get cart by user
    public CartModel getCart(String token){
        long user_id = tokenUtil.decodeToken(token);
        UserModel user = userRepository.findById(user_id).get();
        CartModel cart = cartRepository.findByUser(user).orElseThrow(()-> new ExceptionClass("Not available"));
        if(cart!=null){
            return cart;
        }
        else
            throw new ExceptionClass("Cart is Not Available");
    }

}
