package com.example.restaurant.validator;

import com.example.restaurant.error.InvalidProductOperationException;
import com.example.restaurant.persistence.entity.OrderItem;
import com.example.restaurant.service.OrderItemService;
import com.example.restaurant.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private Environment environment;

    public void validateToDelete(Integer productId) throws InvalidProductOperationException {
        Iterable<OrderItem> orderItems = orderItemService.findOrderItemsByDish(productId);
        if (IterableUtils.isNotEmpty(orderItems)) {
            throw new InvalidProductOperationException(environment.getProperty("product.is.ordered.error"));
        }
    }
}
