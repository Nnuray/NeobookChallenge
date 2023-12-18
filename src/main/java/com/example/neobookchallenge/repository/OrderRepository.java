package com.example.neobookchallenge.repository;


import com.example.neobookchallenge.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
