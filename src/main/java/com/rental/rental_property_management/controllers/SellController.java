package com.rental.rental_property_management.controllers;

import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.models.PropertyImage;
import com.rental.rental_property_management.models.User;
import com.rental.rental_property_management.repository.PropertyImageRepository;
import com.rental.rental_property_management.repository.PropertyRepository;
import com.rental.rental_property_management.repository.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class SellController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    @Autowired
    private UserRepository userRepository;

    private Cloudinary cloudinary;

    public SellController() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dqwo6u7wv",
                "api_key", "518535535231524",
                "api_secret", "Bhoj40BFNW80b_Pt9HME-NjvllU"
        ));
    }

    @GetMapping("/sell")
    public String showSellForm(Model model) {
        model.addAttribute("property", new Property());
        return "sell";
    }

    @PostMapping("/sell")
    public String submitSellForm(@ModelAttribute Property property,
                                 @RequestParam(value = "agentId", required = false) Long agentId,
                                 @RequestParam("propertyImages") MultipartFile[] images) throws IOException {

        // Assign a seller automatically (first user in DB)
        User defaultSeller = userRepository.findById(8L)
                .orElseThrow(() -> new RuntimeException("User with ID 8 not found in database."));
        property.setListedBy(defaultSeller);


        // Optional: set agent if provided
        if (agentId != null) {
            User agent = userRepository.findById(agentId)
                    .orElse(null);
            property.setAgent(agent);
        }

        // Save property
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

        return "redirect:/"; // Redirect after successful submission
    }
}
