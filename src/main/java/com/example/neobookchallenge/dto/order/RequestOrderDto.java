package com.example.neobookchallenge.dto.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestOrderDto {
    private int productId;
    private int quantity;

}
