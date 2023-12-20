package com.example.neobookchallenge.controller;

import com.example.neobookchallenge.dto.order.RequestOrderDto;
import com.example.neobookchallenge.dto.order.ResponseOrderDto;
import com.example.neobookchallenge.entity.User;
import com.example.neobookchallenge.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor  //add dependecies  DI
@RequestMapping("/api/orders")
@Tag(
        name = "Контроллер для заказа")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseOrderDto> getOderById(@PathVariable int id){ //извлекает айди из данных и передает в метод
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/create")
    @SecurityRequirement(name = "JWT")
    public ResponseOrderDto createOrder(@RequestBody RequestOrderDto orderDto,  //извлекает данные из http и объеденяет
                                        @AuthenticationPrincipal User user) {
        return orderService.createOrder(orderDto, user);
    }

    @GetMapping("/all")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<ResponseOrderDto>> getAllOrders() {
        List<ResponseOrderDto> allOrders = orderService.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order with id " + id + " deleted successfully");
    }
}
