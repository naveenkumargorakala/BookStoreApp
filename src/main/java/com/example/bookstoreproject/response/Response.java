package com.example.bookstoreproject.response;

import com.example.bookstoreproject.model.BookModel;
import com.example.bookstoreproject.model.CartModel;
import com.example.bookstoreproject.model.OrderModel;
import com.example.bookstoreproject.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Response {
    private String message;
    private Object object;
    private List list;

    public Response(String user, String message) {
        this.message = message;
        this.object = user;
    }

    public Response(List<UserModel> userList, String message) {
        this.message = message;
        this.list = userList;
    }

    public Response(UserModel user, String message) {
        this.object = user;
        this.message =message;
    }

    public Response(String message, List<String> errorList) {
        this.message = message;
        this.list=errorList;
    }

    public Response(CartModel cart, String insertCartDetails) {
        this.object = cart;
        this.message = insertCartDetails;
    }

    public Response(BookModel book, String updateQuantity) {
        this.object = book;
        this.message = updateQuantity;
    }

    public Response(List<BookModel> ascendingOrderList, String orderMessage,String message2) {
        this.list=ascendingOrderList;
        this.message=orderMessage;
        this.object=message2;
    }

    public Response(String cart, ArrayList<CartModel> cartModel, String insertCartDetails) {
        this.object=cart;
        this.list =cartModel;
        this.message=insertCartDetails;
    }


    public Response(OrderModel order, String orderPlaced) {
        this.object =order;
        this.message =orderPlaced;
    }

    public Response(List<OrderModel> orderList) {
        this.list=orderList;
    }
}
