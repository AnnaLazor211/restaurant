package com.example.restaurant.translator;


import com.example.restaurant.cache.data.BasketData;
import com.example.restaurant.persistence.entity.Dish;
import com.example.restaurant.persistence.entity.OrderItem;
import com.example.restaurant.persistence.entity.OrderList;
import com.example.restaurant.persistence.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class BasketDataTranslator {

    public OrderList userBasketDataToOrder(BasketData basketData, User user) {
        OrderList orderList = new OrderList();
        orderList.setUser(user);
        Iterator iterator = basketData.readBasketItems().entrySet().iterator();
        List<OrderItem> orderItems = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Dish, Integer> basketItem = (Map.Entry<Dish, Integer>) iterator.next();
            for (int i = 0; i < basketItem.getValue(); i++) {
               OrderItem item=new OrderItem();
                item.setDish(basketItem.getKey());
                item.setOrderList(orderList);
                orderItems.add(item);
            }
        }
        orderList.setOrderItems(orderItems);
        return orderList;
    }

}

