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
public class UserController { 

      @GetMapping("/user")
    public String index() {
       
        return "auth/user/index";
    }


}
