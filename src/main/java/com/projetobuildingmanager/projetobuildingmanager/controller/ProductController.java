package com.projetobuildingmanager.projetobuildingmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;


import com.projetobuildingmanager.projetobuildingmanager.models.ProductModel;
import com.projetobuildingmanager.projetobuildingmanager.service.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/user/posts/deletepost/{id}")
    public String deleteProduct(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id); // Use the deleteProduct method with the product ID
        redirectAttributes.addFlashAttribute("successMessage", "Product has been successfully deleted.");

        return "redirect:/admin/posts/products";
    }


    @GetMapping("/user/posts/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        
        if (product == null) {
            throw new IllegalArgumentException("Invalid Product ID: " + id);
        }
        model.addAttribute("produto", product);
        return "blog/edit-product"; 
    }

    @PostMapping("/user/posts/edit/{id}")
public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("produto") ProductModel updatedProduct, Model model, RedirectAttributes redirectAttributes) {

    ProductModel existingProduct = productService.getProductById(id);
    
    if (existingProduct == null) {
        throw new IllegalArgumentException("Invalid Product ID: " + id);
    }
    
    // Update the fields that can be changed by the user
    existingProduct.setTitle(updatedProduct.getTitle());
    existingProduct.setContact(updatedProduct.getContact());
    existingProduct.setDescription(updatedProduct.getDescription());
    existingProduct.setPrice(updatedProduct.getPrice());

    // Get the authenticated user's name and set it as the person making changes
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUser = authentication.getName();
    existingProduct.setLastModifiedBy(currentUser);
    
    productService.salvar(existingProduct); // Update the product

    // Add a success message to the model
        redirectAttributes.addFlashAttribute("successMessage", "Product has been successfully updated.");

    return "redirect:/admin/posts/products";
}

}
