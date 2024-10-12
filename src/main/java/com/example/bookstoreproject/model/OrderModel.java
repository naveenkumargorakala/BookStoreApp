package com.example.bookstoreproject.model;

import com.example.bookstoreproject.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
<<<<<<< Updated upstream
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
=======
import java.util.*;
>>>>>>> Stashed changes

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
<<<<<<< Updated upstream
    private Set<OrderItem> orderItems = new HashSet<>();
    @ElementCollection
    private List<String> Address;
=======
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();
    @ElementCollection
    private List<String> Address = new ArrayList<>();

    public OrderModel(List<String> address) {
        this.Address = address;
    }


>>>>>>> Stashed changes
    public void addUserOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }
}
