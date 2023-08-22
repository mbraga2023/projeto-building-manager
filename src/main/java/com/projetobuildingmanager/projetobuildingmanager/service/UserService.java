package com.projetobuildingmanager.projetobuildingmanager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.projetobuildingmanager.projetobuildingmanager.models.UserModel;

public interface UserService {

    List<UserModel> findAll();

    UserModel findById(long id);

    UserModel save(UserModel userModel);

    void delete(UserModel userModel);

    Page<UserModel> findPaginated(Pageable pageable);

}



    
