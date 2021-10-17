package com.example.coffeeshop.service;

import com.example.coffeeshop.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    OrderServiceModel addOrder(OrderServiceModel orderServiceModel);

    List<OrderServiceModel> getOrders();

    void deleteById(Long id);
}
