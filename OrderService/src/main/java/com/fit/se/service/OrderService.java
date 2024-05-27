package com.fit.se.service;

import com.fit.se.entity.Order;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    Order getOrderById(int id);

    List<Order> getAllOrder();

    void deleteOrderById(int id);

    Order updateOrderById(int id, Order newOrder);
}
