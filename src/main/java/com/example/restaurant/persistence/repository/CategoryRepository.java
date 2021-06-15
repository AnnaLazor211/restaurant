package com.example.restaurant.persistence.repository;

import com.example.restaurant.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


//МОЖЕТ ОН НЕ НУЖЕН??
public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
