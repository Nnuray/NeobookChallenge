package com.example.neobookchallenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "delivery_cost")
    private double deliveryCost;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
