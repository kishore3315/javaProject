package com.example.ecommerce_order_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_order_management.Customer;
import com.example.ecommerce_order_management.Order;
import com.example.ecommerce_order_management.repository.CustomerOrderRepository;

@Service
public class CustomerOrderService {

    @Autowired
    private CustomerOrderRepository orderRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // JPQL-style finder to get all orders by customer ID
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}