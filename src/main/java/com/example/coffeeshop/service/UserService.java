package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;

import java.util.Optional;

public interface UserService {

    boolean authenticate(String username, String password);

    void login(String username);

    UserServiceModel registerNewUser(UserServiceModel userServiceModel);

    boolean checkExistingUsername(String username);

    Optional<UserEntity> findById(Long id);
}
