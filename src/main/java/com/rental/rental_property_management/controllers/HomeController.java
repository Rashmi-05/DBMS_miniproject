package com.rental.rental_property_management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showLandingPage() {
        return "landing"; // Spring will load templates/landing.html
    }

    @GetMapping("/browse")
    public String browsePage() {
        return "browse"; // Thymeleaf will render templates/browse.html
    }

    @GetMapping("/sell")
    public String sellPage() {
        return "sell"; // Thymeleaf will render templates/sell.html
    }
}
