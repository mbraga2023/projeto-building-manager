package com.projetobuildingmanager.projetobuildingmanager.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetobuildingmanager.projetobuildingmanager.models.Roles;
import com.projetobuildingmanager.projetobuildingmanager.models.UserModel;
import com.projetobuildingmanager.projetobuildingmanager.repository.RolesRepository;
import com.projetobuildingmanager.projetobuildingmanager.repository.UserRepository;
import com.projetobuildingmanager.projetobuildingmanager.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository roleRepository;

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
        return "auth/admin/index";

    }
    @GetMapping("/admin/users")
    public String adminUsers(Model model) {
        List<UserModel> user = userService.findAll();
        model.addAttribute("user", user);
        return "auth/admin/admin-userlist";

    }

    @GetMapping("/admin/newuser")
    public ModelAndView addNewUser() {
        ModelAndView mv = new ModelAndView("auth/admin/userForm");
        mv.addObject("user", new UserModel()); // Add the user object to the model
        return mv;
    }

    @PostMapping("/admin/saveuser")
    public String saveUser(@Valid UserModel user, BindingResult result, Model model, RedirectAttributes attributes) {
        // Check if an existing user with the same email exists
        boolean emailExists = userRepository.existsByEmail(user.getEmail());
        if (emailExists) {
            attributes.addFlashAttribute("emailExists", "User already exists. Profile not saved.");
            return "redirect:/admin/newuser";
        }

        if (result.hasErrors()) {
            attributes.addFlashAttribute("erro", "Problem saving");
            return "redirect:/admin/newuser";
        }
        // Busca o papel básico de usuário
        Roles role = roleRepository.findByRole("USER");
        List<Roles> roleList = new ArrayList<Roles>();
        roleList.add(role);
        user.setRoles(roleList); // associa o papel de USER ao usuário

        userRepository.save(user);
        attributes.addFlashAttribute("message", "User created successfully");
        return "redirect:/admin/newuser";
    }

    @ControllerAdvice
    class CustomErrorController {

        @ExceptionHandler(Exception.class)
        public String handleException() {
            return "error";
        }
    }

    // Controller method for editing a user
    @GetMapping("/editrole/{id}")
    public String selecionarPapel(@PathVariable("id") long id, Model model) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User: " + id));
    
        model.addAttribute("user", user);
        model.addAttribute("listRoles", roleRepository.findAll());
    
        return "auth/admin/edit-roles";


    }

    @PostMapping("/update-roles")
public String updateRoles(@ModelAttribute("user") UserModel user, @RequestParam("selectedRoles") List<Long> selectedRoleIds, RedirectAttributes redirectAttributes) {
    // Retrieve the user from the database
    UserModel existingUser = userService.findById(user.getId());

    if (existingUser == null) {
        throw new IllegalArgumentException("User not found with ID: " + user.getId());
    }

    // Update user details
    existingUser.setName(user.getName());
    existingUser.setEmail(user.getEmail());
    existingUser.setPassword(user.getPassword());
    existingUser.setUnitNumber(user.getUnitNumber());
    existingUser.setGarageSpot(user.getGarageSpot());
    existingUser.setPhone(user.getPhone());
    existingUser.setObservations(user.getObservations());

    // Update user's roles based on selectedRoleIds
    List<Roles> selectedRoles = roleRepository.findAllById(selectedRoleIds);
    existingUser.setRoles(selectedRoles);

    // Save the updated user
    userService.save(existingUser); // Add a flash message to indicate success
    redirectAttributes.addFlashAttribute("successMessage", "User profile updated successfully!");

    return "redirect:/admin"; //
}


}
