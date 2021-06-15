package com.example.restaurant.cache.data;

import com.example.restaurant.persistence.entity.Dish;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class BasketData {

    private final Integer INITIAL_AMOUNT = 1;

    private Map<Dish, Integer> basketItems = new HashMap<>();

    public ImmutableMap<Dish, Integer> readBasketItems() {
        return ImmutableMap.copyOf(basketItems);
    }//don`t change map

    public void  changeDishAmount(Dish dish, Integer newAmount) {
        if (basketItems.containsKey(dish)) {
            Integer dishAmount = basketItems.get(dish);
            basketItems.replace(dish, dishAmount, newAmount);
        }
    }

    public void addDish(Dish dish) {
        if (basketItems.containsKey(dish)) {
            changeDishAmount(dish, basketItems.get(dish) + 1);
            return;
        }
        basketItems.put(dish, INITIAL_AMOUNT);

    }

    public void removeDish(Dish dish) {
        basketItems.remove(dish);
    }

}
