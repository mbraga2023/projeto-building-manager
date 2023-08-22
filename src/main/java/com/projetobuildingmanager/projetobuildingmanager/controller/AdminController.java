package com.projetobuildingmanager.projetobuildingmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.projetobuildingmanager.projetobuildingmanager.models.UserModel;
import com.projetobuildingmanager.projetobuildingmanager.service.UserService;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/listar", method = RequestMethod.GET)
    public ModelAndView getUsers(Pageable pageable) {
        ModelAndView mv = new ModelAndView("user");

        Page<UserModel> userPage = userService.findPaginated(pageable);

        mv.addObject("user", userPage.getContent());
        mv.addObject("page", userPage);

        return mv;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<UserModel> user = userService.findAll();
        model.addAttribute("user", user);
        return "auth/admin/admin-userlist"; 
    }
    
}
