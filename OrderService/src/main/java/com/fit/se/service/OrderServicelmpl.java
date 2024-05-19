package com.fit.se.service;

import com.fit.se.entity.Clothing;
import com.fit.se.entity.Customer;
import com.fit.se.entity.Order;
import com.fit.se.repository.OrderRepository; // Chỉnh sửa tên repository
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServicelmpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    private RestTemplate restTemplate;

    @Override
    public Order saveOrder(Order order) {
        ResponseEntity<Customer> responseEntity = restTemplate
                .getForEntity("http://localhost:8083/customers/" + order.getCustomer().getId(),
                        Customer.class);
        Customer customer = responseEntity.getBody();
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrderById(int id, Order newOrder) {
        Order tempOrder = orderRepository.findById(id).get();
        tempOrder.setOrderDate(newOrder.getOrderDate());
        tempOrder.setCustomer(newOrder.getCustomer());
        return orderRepository.save(tempOrder);
    }
}