package com.rental.rental_property_management.controllers;

import com.rental.rental_property_management.models.User;
import com.rental.rental_property_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user") // all user actions under /user
public class UserController {

    @Autowired
    private UserService userService;

    // Register page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User()); // Required for Thymeleaf binding
        return "register"; // maps to register.html
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (user.getName() == null || user.getName().isEmpty()) {
            model.addAttribute("error", "Name is required");
            return "register";
        }
        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred. Try again.");
            return "register";
        }
    }


}
