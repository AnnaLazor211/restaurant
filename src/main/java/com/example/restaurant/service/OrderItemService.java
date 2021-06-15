package com.example.restaurant.service;

import com.example.restaurant.persistence.entity.OrderItem;
import com.example.restaurant.persistence.entity.OrderList;
import com.example.restaurant.persistence.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    public void saveOrderItems(Iterable<OrderItem> orderItems) {
        for (OrderItem item : orderItems) {
            orderItemRepository.save(item);
        }
    }

    public Iterable<OrderItem> getOrderItems(OrderList orderList) {
        return orderItemRepository.findAllByOrderList(orderList);
    }


    public Iterable<OrderItem> findOrderItemsByDish(Integer optionId) {
        return orderItemRepository.findAllByDishCategoryCategoryId(optionId);
    }

}
