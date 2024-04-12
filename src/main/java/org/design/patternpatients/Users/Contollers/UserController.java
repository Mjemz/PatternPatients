package org.design.patternpatients.Users.Contollers;
import org.design.patternpatients.Users.Users;
import org.design.patternpatients.Users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }



    @GetMapping("/users/new")
    public String createUserForm(Model model) {
        // Create patient object to hold patient form data
        Users user = new Users();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") Users user) {

        userService.saveUser(user);
        return "redirect:/users";
    }

}
