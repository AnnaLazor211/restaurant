package com.example.restaurant.service;


import com.example.restaurant.cache.config.BasketCacheConfig;
import com.example.restaurant.cache.data.BasketData;
import com.example.restaurant.persistence.entity.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BasketCacheConfig basketCacheConfig;

    @Autowired
    private DishService dishService;

    public void addToBasket(String sessionId, Dish option) {
        try {
            Dish needy = dishService.finDishById(option.getDishId());
            BasketData data = basketCacheConfig.getCache().get(sessionId);
            data.addDish(needy);
        } catch (ExecutionException e) {
            logger.error("ERROR during adding option", +option.getCategoryId(), e);
        }

    }

    public void deleteDish(String sessionId, Dish option) {//deleteOption
        try {
            BasketData data = basketCacheConfig.getCache().get(sessionId);
            data.removeDish(option);

        } catch (ExecutionException e) {
            logger.error("ERROR during deleting option " + option.getDishId(), e);
        }
    }

    public void changeDishAmount(String sessionId, Dish option, Integer newAmount) {
        try {
            BasketData data = basketCacheConfig.getCache().get(sessionId);
            data.changeDishAmount(option, newAmount);
        } catch (ExecutionException e) {
            logger.error("ERROR during deleting option " + option.getDishId(), e);
        }
    }

    public Map<Dish, Integer> readBasketItems(String sessionId) {
        try {
            return basketCacheConfig.getCache().get(sessionId).readBasketItems();
        } catch (Exception e) {
            logger.error("ERROR during reading basket data: ", e);
            return null;
        }
    }

    public double countTotalPrice(Map<Dish, Integer> basketItems) {
        double result = 0d;
        for (Map.Entry<Dish, Integer> entry : basketItems.entrySet()) {
            result += (entry.getKey().getPrice() * entry.getValue());
        }
        return result;
    }

    public Dish findDishByIdFromCache(String sessionId, Integer optionId) {
        Map<Dish, Integer> basketItems = readBasketItems(sessionId);
        List<Dish> filteredOptions = basketItems.keySet().stream()
                .filter(option -> option.getDishId().equals(optionId))
                .collect(Collectors.toList());
        return CollectionUtils.isEmpty(filteredOptions) ? null : filteredOptions.get(0);
    }

    public void invalidateCache() {
        basketCacheConfig.getCache().invalidateAll();
    }

    public BasketData getBasketData(String sessionId) {
        return basketCacheConfig.getCache().getUnchecked(sessionId);
    }

}
