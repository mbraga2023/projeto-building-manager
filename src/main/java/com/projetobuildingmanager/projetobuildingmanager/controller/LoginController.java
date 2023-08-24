package com.projetobuildingmanager.projetobuildingmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.projetobuildingmanager.projetobuildingmanager.repository.UserRepository;


   @Controller
public class LoginController {


	@Autowired
	private UserRepository usuarioRepository;
	
    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            // Get the user's name from UserDetails
            String username = userDetails.getUsername();
            
            // Determine the role and redirect accordingly
            if (userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin";
            } else if (userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                return "redirect:/user";
            }
        }
        
        // Redirect to the login page if user details are not available or roles are not matched
        return "redirect:/login";
    }
	
    @RequestMapping("/login") 
	public String login() {
		return "home/login";
	}


    @RequestMapping("/register") 
	public String register() {
		return "home/register";
	}

	@GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout"; // Redirect to login page after logout
    }

}