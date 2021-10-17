package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.repo.UserRepo;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean authenticate(String username, String password) {
        Optional<UserEntity> userEntityOpt = userRepo.findByUsername(username);
        if (userEntityOpt.isEmpty()) {
            return false;
        }
        return userEntityOpt.get().getPassword().equals(password);
    }

    @Override
    public void login(String username) {
        UserEntity loggedInUser = userRepo.findByUsername(username).orElseThrow();
        currentUser.setId(loggedInUser.getId());
        currentUser.setUsername(loggedInUser.getUsername());
    }

    @Override
    public UserServiceModel registerNewUser(UserServiceModel userServiceModel) {
        UserEntity userEntity = userRepo.save(modelMapper.map(userServiceModel, UserEntity.class));
        return modelMapper.map(userEntity, UserServiceModel.class);
    }

    @Override
    public boolean checkExistingUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepo.findById(id);
    }
}
