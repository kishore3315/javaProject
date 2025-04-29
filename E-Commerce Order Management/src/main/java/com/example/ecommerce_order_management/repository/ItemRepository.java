package com.example.ecommerce_order_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_order_management.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
