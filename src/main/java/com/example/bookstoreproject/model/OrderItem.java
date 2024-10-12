package com.example.bookstoreproject.model;

<<<<<<< Updated upstream
=======
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
>>>>>>> Stashed changes
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
<<<<<<< Updated upstream
=======
@JsonIgnoreProperties("order_model")
>>>>>>> Stashed changes
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
<<<<<<< Updated upstream
=======
    @JsonBackReference
>>>>>>> Stashed changes
    private OrderModel order;
}
