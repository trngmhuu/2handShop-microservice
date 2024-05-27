package com.fit.se.service;

import com.fit.se.entity.Clothing;
import com.fit.se.entity.Customer;
import com.fit.se.entity.Order;
import com.fit.se.entity.OrderItem;
import com.fit.se.repository.ClothingRepository;
import com.fit.se.repository.CustomerRepository;
import com.fit.se.repository.OrderItemRepository;
import com.fit.se.repository.OrderRepository;
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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ClothingRepository clothingRepository;
    @Autowired
    private OrderRepository orderRepository;

    private RestTemplate restTemplate;


    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {

        //Fetch order
        ResponseEntity<Order> responseEntityOrder = restTemplate
                .getForEntity("http://localhost:8084/orders/" + orderItem.getOrder().getId(),
                        Order.class);
        Order order = responseEntityOrder.getBody();

        //Fetch customer
        ResponseEntity<Customer> responseEntityCustomer = restTemplate
                .getForEntity("http://localhost:8083/customers/" + order.getCustomer().getId(),
                        Customer.class);
        Customer customer = responseEntityCustomer.getBody();
        customerRepository.save(customer);
        orderRepository.save(order);
        orderItem.setOrder(order);

        // Fetch clothing
        ResponseEntity<Clothing> responseEntityClothing = restTemplate
                .getForEntity("http://localhost:8082/clothings/" + orderItem.getClothing().getId(),
                        Clothing.class);
        Clothing clothing = responseEntityClothing.getBody();
        clothingRepository.save(clothing);
        orderItem.setClothing(clothing);

        // Kiểm tra trùng mặt hàng
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        for (OrderItem tempOrderItem : orderItemList) {
            if (tempOrderItem.getClothing().getId() == orderItem.getClothing().getId()) {
                throw new IllegalArgumentException("Mặt hàng này đã được lưu vào hóa đơn, hãy chỉnh sửa lại số lượng");
            }
        }

        // Tính toán tổng tiền của một mặt hàng
        int quantity = orderItem.getQuantity();
        double price = quantity * orderItem.getClothing().getPrice();
        orderItem.setPrice(price);

        // Lưu OrderItem
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
