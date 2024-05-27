package com.fit.se.controller;


import com.fit.se.entity.Order;
import com.fit.se.repository.OrderRedisRepository;
import com.fit.se.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;
    @Autowired
    private OrderRedisRepository orderRedisRepository;


    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        orderRedisRepository.saveOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<Order> orderList = orderRedisRepository.findAll();
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") int orderId) {
        Order order = orderRedisRepository.findById(orderId);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable("id") int orderId) {
        orderService.deleteOrderById(orderId);
        orderRedisRepository.deleteById(orderId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderById(@PathVariable("id") int orderId, @RequestBody Order newOrder) {
        Order updatedOrder = orderService.updateOrderById(orderId, newOrder);
        orderRedisRepository.update(updatedOrder);
        return ResponseEntity.ok(updatedOrder);
    }





}
