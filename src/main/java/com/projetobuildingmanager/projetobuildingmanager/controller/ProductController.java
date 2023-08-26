package com.projetobuildingmanager.projetobuildingmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetobuildingmanager.projetobuildingmanager.service.ProductService;

@Controller
@RequestMapping("/admin/posts")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/deletepost/{id}")
    public String deleteProduct(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id); // Use the deleteProduct method with the product ID
        redirectAttributes.addFlashAttribute("successMessage", "Product has been successfully deleted.");

        return "redirect:/admin/posts/products";
    }

}
