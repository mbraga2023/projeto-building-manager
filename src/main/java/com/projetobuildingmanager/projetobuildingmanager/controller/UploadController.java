package com.projetobuildingmanager.projetobuildingmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetobuildingmanager.projetobuildingmanager.models.ProductModel;
import com.projetobuildingmanager.projetobuildingmanager.repository.UserRepository;
import com.projetobuildingmanager.projetobuildingmanager.service.GravarImagem;
import com.projetobuildingmanager.projetobuildingmanager.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class UploadController {

    private final GravarImagem gravarImagem;
    private final ProductService produtoService;

    @Autowired
    public UploadController(GravarImagem gravarImagem, ProductService produtoService) {
        this.gravarImagem = gravarImagem;
        this.produtoService = produtoService;

    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/new")
    public ModelAndView showNewProductForm() {
        ModelAndView modelAndView = new ModelAndView("blog/new-product");
        modelAndView.addObject("produto", new ProductModel()); // Add the product model
        return modelAndView;
    }

    @PostMapping("/user/new")
    public ModelAndView salvar(@RequestParam("file") MultipartFile file,
            @Valid ProductModel produto, BindingResult result, RedirectAttributes atributos,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (result.hasErrors()) {
            atributos.addFlashAttribute("error_message",
                    "There were errors in the form submission. Please check the fields.");

            return new ModelAndView("redirect:/user/new");
        }

        // Get the authenticated user's email and name
        String userEmail = userDetails.getUsername();
        String userName = userRepository.findByEmail(userEmail).getName();

        // Set the author and save the product
        produto.setAuthor(userName);
        produtoService.salvar(produto); // Corrected method call
        gravarImagem.gravaImagemBase64Service(file, produtoService, produto);

        atributos.addFlashAttribute("sucessMessage", "ProductModel salvo com sucesso!");

        return new ModelAndView("redirect:/user/new");
    }

    @Controller
    public class ProductController {

        private final ProductService produtoService;

        @Autowired
        public ProductController(ProductService produtoService) {
            this.produtoService = produtoService;
        }

        @GetMapping("/admin/posts/products")
        public ModelAndView listProducts(ModelAndView modelAndView) {
            List<ProductModel> produtos = produtoService.getAllProducts();
            modelAndView.addObject("produtos", produtos);
            modelAndView.setViewName("blog/products-list");
            return modelAndView;
        }
    }

    

}
