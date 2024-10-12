package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.OrderDto;
import com.example.bookstoreproject.dto.OrderItemDto;
import com.example.bookstoreproject.exception.ExceptionClass;
import com.example.bookstoreproject.model.*;
import com.example.bookstoreproject.repository.BookRepository;
import com.example.bookstoreproject.repository.OrderRepository;
import com.example.bookstoreproject.repository.UserRepository;
import com.example.bookstoreproject.util.JMSMailSender;
import com.example.bookstoreproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService{


    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    JMSMailSender jmsMailSender;
    @Autowired
    TokenUtil tokenUtil;




//    //place Order
//    public OrderModel insertOrder(OrderDto orderDto){
//        UserModel user = userRepository.findById(orderDto.getUserId()).get();
//        List<BookModel> books =new ArrayList<>();
//        for(int i=0;i<orderDto.getBookIds().size();i++) {
//            BookModel book = bookRepository.findById(orderDto.getBookIds().get(i)).get();
//            // Check if there are enough available copies
//            int requestedQuantity = orderDto.getQuantity();
//            if (book.getQuantity() < requestedQuantity) {
//                throw new ExceptionClass("Not enough available copies of the book");
//            }
//            // Reduce the available quantity and save the book
//            book.setQuantity(book.getQuantity() - requestedQuantity);
//            bookRepository.save(book);
//            books.add(book);
//        }
//        OrderModel order = new OrderModel(user,books,orderDto);
//        order.setTotalPrice(orderDto.getTotalPrice());
//        orderRepository.save(order);
//        jmsMailSender.mailSender(order.getUser().getEmail(),"Place Order ","Hello "+order.getUser().getFirstName()+"\n You are Ordered"+order.getBooks().size()+"\n On the Date"+order.getDate());
//        return order;
//    }
//
//    //all orders
//    public List<OrderModel> orderList(){
//        List<OrderModel> list = orderRepository.findAll();
//        return list;
//    }
//
//    //get order
//    public  OrderModel getOrder(String token){
//        long id=tokenUtil.decodeToken(token);
//        UserModel user= userRepository.findById(id).get();
//        OrderModel order = orderRepository.findByuser(user);
//        if(order!=null) {
//            return order;
//        }
//        else
//            throw new ExceptionClass("id is Not Available to show Order");
//    }
//
//    //update order details
//    public OrderModel updateOrder(OrderDto orderDto, String token){
//        int id=tokenUtil.decodeToken(token);
//        UserModel user1= userRepository.findById(id).get();
//        OrderModel order = orderRepository.findByuser(user1);
//        if(order!=null){
//            UserModel user = userRepository.findById(orderDto.getUserId()).get();
//            List<BookModel> books =new ArrayList<>();
//            for(int i=0;i<orderDto.getBookIds().size();i++) {
//                BookModel book = bookRepository.findById(orderDto.getBookIds().get(i)).get();
//                // Check if there are enough available copies
//                int requestedQuantity = orderDto.getQuantity();
//                if (book.getQuantity() < requestedQuantity) {
//                    throw new ExceptionClass("Not enough available copies of the book");
//                }
//                // Reduce the available quantity and save the book
//                book.setQuantity(book.getQuantity() - requestedQuantity);
//                bookRepository.save(book);
//                books.add(book);
//            }            order.setUser(user);
//            order.setBooks(books);
//            order.setTotalPrice(orderDto.getTotalPrice());
//            order.setAddress(orderDto.getAddress());
//            order.setQuantity(order.getQuantity());
//            orderRepository.save(order);
//            jmsMailSender.mailSender(order.getUser().getEmail(),"Updated Your Order ","Hello "+order.getUser().getFirstName()+"\n You are Ordered"+order.getBooks().size());
//            return order;
//        }
//        else
//            throw new ExceptionClass("id is Not available to update");
//    }
//
//    //cancel order
//    public String updateCancel(String token){
//        int id=tokenUtil.decodeToken(token);
//        UserModel user1= userRepository.findById(id).get();
//        OrderModel order = orderRepository.findByuser(user1);
//        if(order!=null){
//            order.setCancel(true);
//            orderRepository.save(order);
//            jmsMailSender.mailSender(order.getUser().getEmail(),"Cancelled Your Order ","Hello "+order.getUser().getFirstName()+"\n You are cancelled your Ordered"+order.getBooks().size());
//            return "Order Cancelled";
//        }
//        else
//            throw new ExceptionClass("token is not available to update order");
//    }
//
//
//    //delete order by id
//    public String deleteOrder(String token){
//        int id=tokenUtil.decodeToken(token);
//        UserModel user1= userRepository.findById(id).get();
//        OrderModel order = orderRepository.findByuser(user1);
//        if(order!=null){
//            orderRepository.deleteById(order.getOrderId());
//            return "Delete Order Details";
//        }
//        else
//            throw new ExceptionClass("id not available");
//    }
////    public int totalPrice(int quantity,int id){
////        int totalPrice =quantity*bookRepository.findById(id).get().getPrice();
////        return totalPrice;
////    }


<<<<<<< Updated upstream
    public OrderModel placeOrder(String token, List<CartItem> cartItems, List<String> address){
=======
    public OrderModel placeOrder(String token, List<CartItem> cartItems){
>>>>>>> Stashed changes
        long useId = tokenUtil.decodeToken(token);
        UserModel user = userRepository.findById(useId).get();
        OrderModel order = new OrderModel();
        order.setUser(user);
        order.setOrderDate(new Date());
<<<<<<< Updated upstream
        order.setAddress(address);
=======
//        order.setAddress(address);
>>>>>>> Stashed changes
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order); // Associate the order item with the order
            order.addUserOrderItem(orderItem); // Add the order item to the order
        }
        orderRepository.save(order);
        jmsMailSender.mailSender(user.getEmail(),"Order Placed Successfully on "+order.getOrderDate()+"\n","Delivered to: "+order.getAddress());
        return order;
    }

    public OrderModel getOrder(String token){
        long userId= tokenUtil.decodeToken(token);
        UserModel user = userRepository.findById(userId).get();
<<<<<<< Updated upstream
        OrderModel order = orderRepository.findByuser(user);
        if(order!=null){
            return order;
        }
        else{
            throw new ExceptionClass("Order is not available for the user"+user.getFirstName());
        }
=======
        OrderModel order = orderRepository.findFirstByUser(user);
        if(order!=null){
            return order;
        }
        else{
            throw new ExceptionClass("Order is not available for the user"+user.getFirstName());
        }
    }

    public OrderModel updateAddress(String token,List<String> address){
        long userId= tokenUtil.decodeToken(token);
        UserModel user = userRepository.findById(userId).get();
        OrderModel order = orderRepository.findFirstByUser(user);
        if(order!=null) {
            order.setAddress(address);
            orderRepository.save(order);
            return order;
        }
        else
            throw new ExceptionClass("Order is not found");
>>>>>>> Stashed changes
    }

}
