package com.example.ecommerce_order_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_order_management.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByEmail(String email);

}
