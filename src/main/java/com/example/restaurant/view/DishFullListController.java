package com.example.restaurant.view;//package com.example.restaurant.persistence.view;
import com.example.restaurant.persistence.entity.Dish;
import com.example.restaurant.service.CategoryService;
import com.example.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DishFullListController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/dishes")
    public String catalogueOpen(Model model) {
        model.addAttribute("dishes", dishService.getAllDish());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "dishes";
    }

    @GetMapping("/getDishDetails/{dishId}")
    public String getDishDetails(Model model, @PathVariable("dishId") Integer dishId) {
        model.addAttribute("dish", dishService.finDishById(dishId));
        return "dishDetails";
    }

    @RequestMapping(value = "/getAllDishes", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Dish> getAllDishes() {
        return dishService.getAllDish();
    }

    @RequestMapping(value = "/filterByCategory/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Dish> filterByCategory(@PathVariable("categoryId") Integer categoryId) {
        return dishService.findByCategoryId(categoryId);
    }

}

