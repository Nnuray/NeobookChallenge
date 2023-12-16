package com.example.neobookchallenge.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_catogiries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_category_id;
    private String name;
    private String img;
}
