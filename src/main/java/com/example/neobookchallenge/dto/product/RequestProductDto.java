package com.example.neobookchallenge.dto.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestProductDto {
    private String productName;
    private double price;
    private float score;

}
