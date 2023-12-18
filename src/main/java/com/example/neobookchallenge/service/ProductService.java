package com.example.neobookchallenge.service;

import com.example.neobookchallenge.dto.product.RequestProductDto;
import com.example.neobookchallenge.entity.Product;
import com.example.neobookchallenge.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public Product createProduct(RequestProductDto requestProductDto) {
        Product product = new Product();
        product.setProductName(requestProductDto.getProductName());
        product.setPrice(requestProductDto.getPrice());
        product.setScore(requestProductDto.getScore());
        return productRepository.save(product);
    }

    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }
}