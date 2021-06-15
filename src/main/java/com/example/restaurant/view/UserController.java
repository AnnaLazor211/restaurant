package com.example.restaurant.view;//package com.example.restaurant.persistence.view;
import com.example.restaurant.persistence.entity.User;
import com.example.restaurant.service.SecurityService;
import com.example.restaurant.service.UserService;
import com.example.restaurant.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/registration/{order_user}", method = RequestMethod.POST)
    public String addToUser(@ModelAttribute("order_user") User user) {
        userService.save(user);
        return "redirect:/dishes";
    }


    @Autowired
    private UserValidator userValidator;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/registration")
    public String registrationPageOpen(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "registration";
        }
        userService.save(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    @ResponseBody
    public String getCurrentUser() {
        return securityService.findLoggedInUsername();
    }



//    @GetMapping("/getCurrentUser/{user}")
//    public @ResponseBody String getCurrentUser(@PathVariable("user") User user) {
//        //chto-to ne to        save(new User)
//        if(userService.findByUsername(user.getUsername())==null) {
//            User user2=userService.save(user);
//        } else {
//            User user1= userService.findByUsername(user.getUsername());
//        }
//        return "users";
//
//
//
//    }


}
