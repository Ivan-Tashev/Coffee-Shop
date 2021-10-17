package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.OrderEntity;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.repo.OrderRepo;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final CurrentUser currentUser;
    private final UserService userService;
    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(CurrentUser currentUser, UserService userService, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.userService = userService;
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) {
        OrderServiceModel orderServiceModelWithEmployee = orderServiceModel.setEmployee(
                userService.findById(currentUser.getId())
                        .orElseThrow());
        OrderEntity savedOrder = orderRepo.save(modelMapper.map(orderServiceModelWithEmployee, OrderEntity.class));
        return modelMapper.map(savedOrder, OrderServiceModel.class);
    }

    @Override
    public List<OrderServiceModel> getOrders() {
        return orderRepo.findAll().stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        orderRepo.deleteById(id);
    }
}
