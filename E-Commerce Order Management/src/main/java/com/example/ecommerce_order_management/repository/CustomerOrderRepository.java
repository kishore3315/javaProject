package com.example.ecommerce_order_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_order_management.Order;

@Repository
public interface CustomerOrderRepository extends JpaRepository<Order, Long>{
	List<Order> findByCustomerId(long customerId);
}