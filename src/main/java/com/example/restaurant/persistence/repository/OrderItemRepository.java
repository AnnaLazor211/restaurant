package com.example.restaurant.persistence.repository;


import com.example.restaurant.persistence.entity.OrderItem;
import com.example.restaurant.persistence.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    Iterable<OrderItem> findAllByOrderList(OrderList orderList);

    Iterable<OrderItem> findAllByDishCategoryCategoryId(Integer dishCategoryId);

}

