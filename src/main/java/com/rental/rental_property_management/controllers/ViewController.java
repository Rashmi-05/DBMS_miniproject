package com.rental.rental_property_management.controllers;

import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.service.PropertyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    private final PropertyService propertyService;

    @Autowired
    public ViewController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping({"/", "/landing.html"})
    public String home() {
        return "landing";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // ✅ UPDATED Browse Mapping

    @GetMapping("/browse")
    public String showBrowsePage(Model model) {
        model.addAttribute("properties", propertyService.findAvailablePropertiesWithImages());
        return "browse";
    }


    @GetMapping("/sell")
    public String showSellPage(HttpServletRequest request, Model model) {
        Object csrfToken = request.getAttribute("_csrf");
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
        }
        return "addproperty";
    }

    @GetMapping("/user/dashboard")
    public String dashboard() {
        return "user-dashboard";
    }
}
