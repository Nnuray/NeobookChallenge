package com.example.neobookchallenge.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;
    private int order_number;
    private String phone_number;
    private String address;
    private String reference_point;
    private String comments;
    private double total_amount;
    private String created_at;
    private double delivery_cost;
}
