package com.example.neobookchallenge.dto.order;


import com.example.neobookchallenge.dto.product.ResponseProductDto;
import com.example.neobookchallenge.dto.user.ResponseUserDto;
import com.example.neobookchallenge.entity.Order;
import lombok.*;

import java.util.List;

import static com.example.neobookchallenge.dto.product.ResponseProductDto.toResponseProductDTO;
import static com.example.neobookchallenge.dto.user.ResponseUserDto.toResponseUserDto;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrderDto {
    private int id;
    private String status;
    private ResponseProductDto product;
    private ResponseUserDto user;
    private String phone_number;
    private String address;
    private String orientation;
    private String comment;

    public static ResponseOrderDto toResponseOrderDTO(Order order){
        return ResponseOrderDto.builder()
                .id(order.getId())
                .status(order.getStatus().name())
                .user(toResponseUserDto(order.getUser()))
                .product(toResponseProductDTO(order.getProduct()))
                .phone_number(order.getPhoneNumber())
                .address(order.getAddress())
                .orientation(order.getOrientation())
                .comment(order.getComment())
                .build();
    }

    public static List<ResponseOrderDto> toResponseOrderDTOs(List<Order> orders){
        return orders.stream().map(ResponseOrderDto::toResponseOrderDTO).toList();
    }

}
