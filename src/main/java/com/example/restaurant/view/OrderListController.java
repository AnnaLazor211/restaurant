package com.example.restaurant.view;//package com.example.restaurant.persistence.view;
import com.example.restaurant.persistence.entity.User;
import com.example.restaurant.service.OrderListService;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderListController {


    @Autowired
    private OrderListService orderListService;

    @Autowired
    private UserService userService;
//need some registration

    @GetMapping("/orders/open")
    public String openOrders(Model model) {
        //trouble??
        User user = userService.findByUsername("testuser");
        model.addAttribute("userOrders", orderListService.findListsByUser(user));
        return "userOrders";
    }

}
