package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.OrderDto;
import com.example.bookstoreproject.exception.ExceptionClass;
import com.example.bookstoreproject.model.BookModel;
import com.example.bookstoreproject.model.OrderModel;
import com.example.bookstoreproject.model.UserModel;
import com.example.bookstoreproject.repository.BookRepository;
import com.example.bookstoreproject.repository.OrderRepository;
import com.example.bookstoreproject.repository.UserRepository;
import com.example.bookstoreproject.util.JMSMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public int totalPrice(int quantity,int id){
        int totalPrice =quantity*bookRepository.findById(id).get().getPrice();
        return totalPrice;
    }

    //place Order
    public OrderModel insertOrder(OrderDto orderDto){
        UserModel user = userRepository.findById(orderDto.getUserId()).get();
        BookModel book = bookRepository.findById(orderDto.getBookId()).get();
        OrderModel order = new OrderModel(user,book,orderDto);
        order.setTotalPrice(totalPrice(order.getQuantity(),order.getBook().getBookId()));
        orderRepository.save(order);
        jmsMailSender.mailSender(order.getUser().getEmail(),"Place Order ","Hello "+order.getUser().getFirstName()+"\n You are Ordered"+order.getBook().getBookName()+"\n On the Date"+order.getDate()+"\n you can see your order details by click on the below link \n http://localhost:8080/order/getbyid/"+order.getOrderId());
        return order;
    }

    //all orders
    public List<OrderModel> orderList(){
        List<OrderModel> list = orderRepository.findAll();
        return list;
    }

    //get order
    public  OrderModel getOrder(int id){
        boolean isOrder = orderRepository.existsById(id);
        if(isOrder) {
            OrderModel order = orderRepository.findById(id).get();
            return order;
        }
        else
            throw new ExceptionClass("id is Not Available to show Order");
    }

    //update order details
    public OrderModel updateOrder(OrderDto orderDto, int id){
        OrderModel order = orderRepository.findById(id).get();
        if(order!=null){
            UserModel user = userRepository.findById(orderDto.getUserId()).get();
            BookModel book = bookRepository.findById(orderDto.getBookId()).get();
            order.setUser(user);
            order.setBook(book);
            order.setTotalPrice(totalPrice(order.getQuantity(),order.getBook().getPrice()));
            order.setAddress(orderDto.getAddress());
            order.setQuantity(order.getQuantity());
            orderRepository.save(order);
            jmsMailSender.mailSender(order.getUser().getEmail(),"Updated Your Order ","Hello "+order.getUser().getFirstName()+"\n You are Ordered"+order.getBook().getBookName()+"\n On the Date"+order.getDate()+"\n you can see your order details by click on the below link \n http://localhost:8080/order/getbyid/"+id);
            return order;
        }
        else
            throw new ExceptionClass("id is Not available to update");
    }

    //cancel order
    public String updateCancel(int id){
        OrderModel order = orderRepository.findById(id).get();
        if(order!=null){
            order.setCancel(true);
            orderRepository.save(order);
            jmsMailSender.mailSender(order.getUser().getEmail(),"Cancelled Your Order ","Hello "+order.getUser().getFirstName()+"\n You are cancelled your Ordered"+order.getBook().getBookName()+"\n On the Date"+order.getDate()+"\n you can see your order details by click on the below link \n http://localhost:8080/order/getbyid/"+id);
            return "Order Cancelled";
        }
        else
            throw new ExceptionClass(id+"cart is not available");
    }


    //delete order by id
    public String deleteOrder(int id){
        OrderModel order = orderRepository.findById(id).get();
        if(order!=null){
            orderRepository.deleteById(id);
            return "Delete Order Details";
        }
        else
            throw new ExceptionClass("id not available");
    }
}
