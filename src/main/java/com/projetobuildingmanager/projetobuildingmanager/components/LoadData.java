package com.projetobuildingmanager.projetobuildingmanager.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.projetobuildingmanager.projetobuildingmanager.models.Roles;
import com.projetobuildingmanager.projetobuildingmanager.repository.RolesRepository;

@Component
public class LoadData implements CommandLineRunner {

    @Autowired
    private RolesRepository rolesRepository; 

    @Override
    public void run(String... args) throws Exception {
        String[] roles = {"ADMIN", "USER", "CONTROLER"};

        for (String roleString : roles) {
            Roles role = rolesRepository.findByRole(roleString);
            if (role == null) {
                role = new Roles(roleString);
                rolesRepository.save(role);
            }
        }
    }
}

