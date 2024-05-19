package com.fit.se.service;

import com.fit.se.entity.Clothing;
import com.fit.se.entity.Order;
import com.fit.se.entity.OrderItem;
import com.fit.se.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    private RestTemplate restTemplate;


    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        // Fetch the order
        ResponseEntity<Order> responseEntityOrder = restTemplate
                .getForEntity("http://localhost:8084/orders/" + orderItem.getOrder().getId(),
                        Order.class);
        Order order = responseEntityOrder.getBody();
        orderItem.setOrder(order);

        // Fetch the clothing
        ResponseEntity<Clothing> responseEntityClothing = restTemplate
                .getForEntity("http://localhost:8082/clothings/" + orderItem.getClothing().getId(),
                        Clothing.class);
        Clothing clothing = responseEntityClothing.getBody();
        orderItem.setClothing(clothing);

        // Check for duplicate clothing in the order
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        for (OrderItem tempOrderItem : orderItemList) {
            if (tempOrderItem.getClothing().getId() == orderItem.getClothing().getId()) {
                throw new IllegalArgumentException("Clothing already exists in the order.");
            }
        }

        // Calculate price
        int quantity = orderItem.getQuantity();
        double price = quantity * orderItem.getClothing().getPrice();
        orderItem.setPrice(price);

        // Save the new order item
        return orderItemRepository.save(orderItem);
    }


    @Override
    public OrderItem getOrderItemById(int id) {
        return orderItemRepository.findById(id).get();
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public void deleteOrderItemById(int id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem updateOrderItemById(int id, OrderItem newOrderItem) {
        OrderItem tempOrderItem = orderItemRepository.findById(id).get();
        tempOrderItem.setQuantity(tempOrderItem.getQuantity());
        tempOrderItem.setPrice(tempOrderItem.getPrice());
        tempOrderItem.setOrder(tempOrderItem.getOrder());
        tempOrderItem.setClothing(tempOrderItem.getClothing());
        return orderItemRepository.save(tempOrderItem);
    }
}
