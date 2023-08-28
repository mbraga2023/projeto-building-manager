package com.projetobuildingmanager.projetobuildingmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.projetobuildingmanager.projetobuildingmanager.models.ProductModel;
import com.projetobuildingmanager.projetobuildingmanager.service.ProductService;

@Controller
public class UserController {
    @Autowired
    ProductService productService;

    @GetMapping("/user")
    public ModelAndView indexView(ModelAndView modelAndView) {
        List<ProductModel> produtos = productService.getAllProducts();
        modelAndView.addObject("produtos", produtos);
        modelAndView.setViewName("auth/user/index");
        return modelAndView;
    }

    @GetMapping("/user/posts")
    public ModelAndView listProducts(ModelAndView modelAndView) {
        List<ProductModel> produtos = productService.getAllProducts();
        modelAndView.addObject("produtos", produtos);
        modelAndView.setViewName("blog/products-postview");
        return modelAndView;
    }

    @RequestMapping(value = "/user/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("blog/product-post-details");
        ProductModel post = productService.getProductById(id);
        mv.addObject("post", post);
        return mv;
    }

}
