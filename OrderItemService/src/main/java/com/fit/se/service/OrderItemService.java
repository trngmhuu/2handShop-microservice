package com.fit.se.service;

import com.fit.se.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem saveOrderItem(OrderItem orderItem);

    OrderItem getOrderItemById(int id);

    List<OrderItem> getAllOrderItems();

    void deleteOrderItemById(int id);

    OrderItem updateOrderItemById(int id, OrderItem newOrderItem);
}
