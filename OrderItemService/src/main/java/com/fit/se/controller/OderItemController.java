package com.fit.se.controller;

import com.fit.se.entity.OrderItem;
import com.fit.se.repository.OrderItemRedisRepository;
import com.fit.se.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
@AllArgsConstructor
public class OderItemController {

    private OrderItemService orderItemService;
    @Autowired
    private OrderItemRedisRepository orderItemRedisRepository;

    @PostMapping
    public ResponseEntity<?> saveOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemService.saveOrderItem(orderItem);
        orderItemRedisRepository.saveOrderItem(orderItem);
        return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrderItems() {
        List<OrderItem> orderItemList = orderItemRedisRepository.findAll();
        return ResponseEntity.ok(orderItemList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable("id") int orderItemId) {
        OrderItem orderItem = orderItemRedisRepository.findById(orderItemId);
        return ResponseEntity.ok(orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItemById(@PathVariable("id") int orderItemId) {
        orderItemService.deleteOrderItemById(orderItemId);
        orderItemRedisRepository.deleteById(orderItemId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderItemById(@PathVariable("id") int orderItemId, @RequestBody OrderItem orderItem) {
       OrderItem updatedOrderItem = orderItemService.updateOrderItemById(orderItemId, orderItem);
       orderItemRedisRepository.update(updatedOrderItem);
       return ResponseEntity.ok(updatedOrderItem);
    }
}
