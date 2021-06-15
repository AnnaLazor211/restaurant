package com.example.restaurant.service;

import com.example.restaurant.persistence.entity.Category;
import com.example.restaurant.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


    public Category findCategoryById(Integer optionId) {
        return categoryRepository.findById(optionId).orElse(null);

    }

    public Category saveCategory(Category option) {
        return categoryRepository.save(option);
    }

    public void deleteCategory(Integer optionId) {
        categoryRepository.deleteById(optionId);
    }


}
