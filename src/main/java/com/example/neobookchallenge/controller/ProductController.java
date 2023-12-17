package com.example.neobookchallenge.controller;

import com.example.week4.dto.product.RequestProductDto;
import com.example.week4.dto.product.ResponseProductDto;
import com.example.week4.entity.Product;
import com.example.week4.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Tag(
        name = "Контроллер для продукта",
        description = "В этом контроллере есть возможности создать, удалить продукт"
)
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/createProduct")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseProductDto> createProduct(@RequestBody RequestProductDto requestProductDto) {
        Product createdProduct = productService.createProduct(requestProductDto);
        ResponseProductDto responseProductDto = ResponseProductDto.toResponseProductDTO(createdProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseProductDto);
    }

    @DeleteMapping("/{productId}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
