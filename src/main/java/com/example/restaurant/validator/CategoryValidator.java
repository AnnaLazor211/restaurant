package com.example.restaurant.validator;

import com.example.restaurant.error.InvalidCategoryOperationException;
import com.example.restaurant.persistence.entity.Category;
import com.example.restaurant.persistence.entity.Dish;
import com.example.restaurant.service.CategoryService;
import com.example.restaurant.service.DishService;
import com.example.restaurant.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @Autowired
    private Environment environment;

    public void validateToDelete(Integer categoryId) throws InvalidCategoryOperationException {

        Iterable<Dish> products = dishService.findByCategoryId(categoryId);
        if (IterableUtils.isNotEmpty(products)) {
            throw new InvalidCategoryOperationException(environment.getProperty("category.has.products.error"));
        }
    }
}
