package com.example.restaurant.persistence.repository;

import com.example.restaurant.persistence.entity.OrderList;
import com.example.restaurant.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

    Set<OrderList> findAllByUserOrderByCreatedDate(User user);

}
