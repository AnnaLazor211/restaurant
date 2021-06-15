package com.example.restaurant.persistence.repository;

import com.example.restaurant.persistence.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DishRepository extends JpaRepository<Dish, Integer> {


    Iterable<Dish> findAllByOrderByNameDish();

    Iterable<Dish> findAllByCategoryCategoryId( Integer categoryId);


}
