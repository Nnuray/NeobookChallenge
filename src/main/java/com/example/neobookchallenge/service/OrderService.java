package com.example.neobookchallenge.service;


import com.example.neobookchallenge.dto.order.RequestOrderDto;
import com.example.neobookchallenge.dto.order.ResponseOrderDto;
import com.example.neobookchallenge.entity.Order;
import com.example.neobookchallenge.entity.Product;
import com.example.neobookchallenge.entity.User;
import com.example.neobookchallenge.enums.Status;
import com.example.neobookchallenge.repository.OrderRepository;
import com.example.neobookchallenge.repository.ProductRepository;
import com.example.neobookchallenge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.neobookchallenge.dto.order.ResponseOrderDto.toResponseOrderDTO;


@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ResponseOrderDto createOrder(RequestOrderDto requestOrderDTO, User user) {
        Product product = productRepository.findById(requestOrderDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product wasn't found"));

        Order order = Order.builder()
                .quantity(requestOrderDTO.getQuantity())
                .product(product)
                .user(user)
                .status(Status.ACTIVE)
                .build();
        orderRepository.save(order);
        return toResponseOrderDTO(order);
    }
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    public ResponseOrderDto getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return toResponseOrderDTO(order);
    }
}
