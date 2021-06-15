package com.example.restaurant.service;


import com.example.restaurant.persistence.entity.Dish;
import com.example.restaurant.persistence.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;
import static org.springframework.util.ResourceUtils.getFile;

@Service
public class DishService {
    /*path to storage from product*/


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository dishRepository;

    public Iterable<Dish> getAllDish() {
        return dishRepository.findAllByOrderByNameDish();
    }

    public Iterable<Dish> findByCategoryId(Integer categoryId) {
        return dishRepository.findAllByCategoryCategoryId(categoryId);
    }

    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish finDishById(Integer dishId) {
        return dishRepository.findById(dishId).orElse(null);
    }

    public void deleteDish(Integer dishId) {
        dishRepository.deleteById(dishId);
    }

    private final String STORAGE_FOLDER = "/storage/";

    private final Character SEPARATOR = '.';

    private String fullPathToStorage;

    //????????????
    public DishService() {
        this.fullPathToStorage = getPathToStorage();

    }

    public String getFullPathToStorage() {
        return this.fullPathToStorage;
    }

    public String buildUniquePhotoPath(String originalName) {
        int lastDot = originalName.lastIndexOf(SEPARATOR);
        StringBuilder result = new StringBuilder();
        result.append(STORAGE_FOLDER);
        result.append(originalName.substring(0, lastDot));
        result.append(new Date().getTime());
        result.append(originalName.substring(lastDot));
        return result.toString();
    }

    private String getPathToStorage() {
        try {
            return getFile(CLASSPATH_URL_PREFIX + "static").getPath();
        } catch (Exception e) {
            logger.error("ERROR during creation of path to storage folder", e);
            return null;
        }
    }


}
