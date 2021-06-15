package com.example.restaurant.view;//package com.example.restaurant.persistence.view;

import com.example.restaurant.persistence.entity.Dish;
import com.example.restaurant.persistence.entity.User;
import com.example.restaurant.service.BasketService;
import com.example.restaurant.service.OrderListService;
import com.example.restaurant.service.SecurityService;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderListService orderListService;

    @Autowired
    private UserService userService;

    @GetMapping("/basket/open")
    public String openBasket(Model model) {
        Map<Dish, Integer> basketItems = basketService.readBasketItems(getSessionId());
        model.addAttribute("selectedDishMap", basketItems);
        model.addAttribute("totalPrice", basketService.countTotalPrice(basketItems));
        return "basket";
    }

    private String getSessionId() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    @RequestMapping(value = "/basket/addToBasket", method = RequestMethod.POST)
    public String addToBasket(@ModelAttribute("dish") Dish dish) {//read
        basketService.addToBasket(getSessionId(), dish);
        return "redirect:/dishes";
    }

    @RequestMapping(value = "/basket/changeDishAmount", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeDishAmount(@RequestParam("dishId") Integer dishId, @RequestParam("dishAmount") Integer dishAmount) {
        Dish dish = basketService.findDishByIdFromCache(getSessionId(), dishId);
        basketService.changeDishAmount(getSessionId(), dish, dishAmount);
    }

    @RequestMapping(value = "/basket/deleteFromBasket", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteFromBasket(@RequestParam("dishId") Integer dishId) {
        Dish dish = basketService.findDishByIdFromCache(getSessionId(), dishId);
        //deleteNeedy?
        basketService.deleteDish(getSessionId(), dish);
    }


    @GetMapping("/basket/basketAmount")
    public @ResponseBody Integer getBasketItemsAmount() {
        return basketService.readBasketItems(getSessionId()).size();
    }

    @GetMapping("/basket/totalPrice")
    public @ResponseBody Double getTotalPrice() {
        return basketService.countTotalPrice(basketService.readBasketItems(getSessionId()));
    }

    @Autowired
    private SecurityService securityService;

    @Autowired
    private OrderListService orderService;
    @RequestMapping(value = "saveOrder", method = RequestMethod.GET)
    public String saveOrder() {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        orderService.saveListFromBasket(basketService.getBasketData(getSessionId()), user);
        basketService.invalidateCache();
        return "redirect:/dishes";
    }

}
