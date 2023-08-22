package com.projetobuildingmanager.projetobuildingmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetobuildingmanager.projetobuildingmanager.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository <UserModel, Long>{

    boolean existsByEmail(String email);

}
