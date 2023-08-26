package com.projetobuildingmanager.projetobuildingmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetobuildingmanager.projetobuildingmanager.models.ProductModel;

public interface ProducRepository extends JpaRepository<ProductModel, Long> {
    public Page<ProductModel> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
