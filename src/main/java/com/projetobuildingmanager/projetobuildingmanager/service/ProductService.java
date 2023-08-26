package com.projetobuildingmanager.projetobuildingmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import com.projetobuildingmanager.projetobuildingmanager.models.ProductModel;
import com.projetobuildingmanager.projetobuildingmanager.repository.ProducRepository;
import com.projetobuildingmanager.projetobuildingmanager.repository.UserRepository;

@Service
public class ProductService implements AbstractService<ProductModel> {

    private final ProducRepository produtoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ProductService(ProducRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void salvar(ProductModel produto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        String userName = userRepository.findByEmail(userEmail).getName();

        produto.setAuthor(userName);

        this.produtoRepository.save(produto);
    }

    public void salvar(ProductModel produto, String imagem) {
        produto.setImg(imagem);
        salvar(produto);
    }

    public List<ProductModel> getAllProducts() {
        return produtoRepository.findAll();
    }

    public ProductModel getProductById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    
    public void deleteProduct(Long id) {
        produtoRepository.deleteById(id);
    }
    public Page<ProductModel> findPaginated(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    
}