package com.projetobuildingmanager.projetobuildingmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetobuildingmanager.projetobuildingmanager.models.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long>{
    Roles findByRole(String roles);
    
}
