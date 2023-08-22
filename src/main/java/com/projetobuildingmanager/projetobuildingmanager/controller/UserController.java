package com.projetobuildingmanager.projetobuildingmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import com.projetobuildingmanager.projetobuildingmanager.models.UserModel;
import com.projetobuildingmanager.projetobuildingmanager.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;



    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new UserModel());
        return "user/register";
    }

    @PostMapping("/new")
    public String saveUser(@Valid UserModel user, BindingResult result, RedirectAttributes attributes
            ) {
        if (result.hasErrors()) {
            return "user/register";
        }
        userRepository.save(user);
        attributes.addFlashAttribute("successMessage", "User created successfully");
        return "redirect:new";

    }

}
