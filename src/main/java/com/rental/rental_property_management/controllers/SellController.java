package com.rental.rental_property_management.controllers;

import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.models.PropertyImage;
import com.rental.rental_property_management.models.User;
import com.rental.rental_property_management.repository.PropertyImageRepository;
import com.rental.rental_property_management.repository.PropertyRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class SellController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    private Cloudinary cloudinary;

    public SellController() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "YOUR_CLOUD_NAME",
                "api_key", "YOUR_API_KEY",
                "api_secret", "YOUR_API_SECRET"));
    }

    @GetMapping("/sell")
    public String showSellForm(Model model) {
        model.addAttribute("property", new Property());
        return "sell";
    }

    @PostMapping("/sell")
    public String submitSellForm(@ModelAttribute Property property,
                                 MultipartFile[] images) throws IOException {

        // Save property first
        propertyRepository.save(property);

        // Upload images to Cloudinary
        if (images != null) {
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                    String imageUrl = (String) uploadResult.get("secure_url");

                    PropertyImage image = new PropertyImage();
                    image.setProperty(property);
                    image.setImageUrl(imageUrl);
                    propertyImageRepository.save(image);
                }
            }
        }

        return "redirect:/sell-success"; // create a simple success page
    }
}
