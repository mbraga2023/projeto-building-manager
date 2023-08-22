package com.projetobuildingmanager.projetobuildingmanager.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


import com.projetobuildingmanager.projetobuildingmanager.models.UserModel;
import com.projetobuildingmanager.projetobuildingmanager.repository.UserRepository;
import com.projetobuildingmanager.projetobuildingmanager.service.UserService;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }

    @Override
    public Page<UserModel> findPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
